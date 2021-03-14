package org.dxworks.metrixppLizardWrapper.DockerRunner;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DockerClientBuilder;
import org.dxworks.metrixppLizardWrapper.Config.Entity.ConfigPair;
import org.dxworks.metrixppLizardWrapper.Entity.MetrixppOutput;
import org.dxworks.metrixppLizardWrapper.Reader.FileReader;
import org.dxworks.metrixppLizardWrapper.Reader.MetrixppCSVFileReader;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DockerMetrixppRunner extends DockerRunner<MetrixppOutput> {

    private static DockerMetrixppRunner shared;

    public static DockerMetrixppRunner getInstance() {
        if (Objects.isNull(shared)) {
            shared = new DockerMetrixppRunner();
        }
        return shared;
    }

    private DockerMetrixppRunner() {}

    @Override
    protected FileReader<MetrixppOutput> getFileReader() {
        return MetrixppCSVFileReader.getInstance();
    }

    @Override
    protected File runTool(Path projectPath, Path outputPath, String imageID, List<ConfigPair> configs) throws InterruptedException {

        DockerClient client = DockerClientBuilder.getInstance().build();

        List<Image> images = client.listImagesCmd().exec();

        long imagesWithId = images.stream().filter(image -> {
            return Arrays.asList(image.getRepoTags()).contains(imageID);
        }).count();

        if (imagesWithId == 0) {
            System.out.println("We don't have Metrixpp");
            client.pullImageCmd(imageID).exec(new PullImageResultCallback()).awaitCompletion();
        } else {
            System.out.println("We have Metrixpp");
        }

        CreateContainerCmd response = client.createContainerCmd(imageID);

        HostConfig hostConfig = new HostConfig().withBinds(
                        Bind.parse(outputPath.toString() + ":/usr/analysis/result"),
                        Bind.parse(projectPath.toString() + ":/usr/analysis/sources"));


        response.withHostConfig(hostConfig).withEnv("PROJECT_ID=metrixpp");

        String container = response.exec().getId();

        client.startContainerCmd(container).exec();

        client.stopContainerCmd(container).exec();

        client.removeContainerCmd(container).exec();

        File file = Paths.get(outputPath.toString() + "/metrixpp.csv").toFile();

        return file;
    }

}
