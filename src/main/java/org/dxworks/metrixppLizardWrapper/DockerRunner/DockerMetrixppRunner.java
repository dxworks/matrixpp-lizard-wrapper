package org.dxworks.metrixppLizardWrapper.DockerRunner;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.core.DockerClientBuilder;
import org.dxworks.metrixppLizardWrapper.Entity.MetrixppOutput;
import org.dxworks.metrixppLizardWrapper.Reader.FileReader;
import org.dxworks.metrixppLizardWrapper.Reader.MetrixppCSVFileReader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    protected File runTool(Path projectPath, Path outputPath, String imageID) {

        DockerClient client = DockerClientBuilder.getInstance().build();

        CreateContainerResponse response = client.createContainerCmd(imageID)
                .withBinds(
                        Bind.parse(outputPath.toString() + ":/usr/analysis/result"),
                        Bind.parse(projectPath.toString() + ":/usr/analysis/sources"))
                .withEnv("PROJECT_ID=test")
                .exec();

        String container = response.getId();

        client.startContainerCmd(container).exec();

        client.stopContainerCmd(container).exec();

        client.removeContainerCmd(container).exec();

        File file = Paths.get(outputPath.toString() + "/test.csv").toFile();

        return file;
    }

}
