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
import org.dxworks.metrixppLizardWrapper.Entity.LizardOutput;
import org.dxworks.metrixppLizardWrapper.Reader.FileReader;
import org.dxworks.metrixppLizardWrapper.Reader.LizardCSVFileReader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DockerLizardRunner extends DockerRunner<LizardOutput> {

    private static DockerLizardRunner shared;

    public static DockerLizardRunner getInstance() {
        if (Objects.isNull(shared)) {
            shared = new DockerLizardRunner();
        }
        return shared;
    }

    private DockerLizardRunner() {}

    @Override
    protected FileReader<LizardOutput> getFileReader() {
        return LizardCSVFileReader.getInstance();
    }

    @Override
    protected File runTool(Path projectPath, Path outputPath, String imageID, List<ConfigPair> configs) throws InterruptedException {

        DockerClient client = DockerClientBuilder.getInstance().build();

        List<Image> images = client.listImagesCmd().exec();

        long imagesWithId = images.stream().filter(image -> {
            return Arrays.asList(image.getRepoTags()).contains(imageID);
        }).count();

        if (imagesWithId == 0) {
            System.out.println("We don't have lizard");
            client.pullImageCmd(imageID).exec(new PullImageResultCallback()).awaitCompletion();
        } else {
            System.out.println("We have lizard");
        }

        CreateContainerCmd response = client.createContainerCmd(imageID);

        HostConfig hostConfig = new HostConfig().withBinds(
                Bind.parse(outputPath.toString() + ":/app/dx-results-lizard"),
                Bind.parse(projectPath.toString() + ":/app/project"));

        response.withHostConfig(hostConfig);

        String container = response.exec().getId();

        client.startContainerCmd(container).exec();

        client.stopContainerCmd(container).exec();

        client.removeContainerCmd(container).exec();

        File file = Paths.get(outputPath.toString() + "/lizard_output.csv").toFile();

        return file;
    }



}
