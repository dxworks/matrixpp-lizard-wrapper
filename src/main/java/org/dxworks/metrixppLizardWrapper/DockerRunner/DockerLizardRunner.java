package org.dxworks.metrixppLizardWrapper.DockerRunner;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.core.DockerClientBuilder;
import org.dxworks.metrixppLizardWrapper.Entity.LizardOutput;
import org.dxworks.metrixppLizardWrapper.Reader.FileReader;
import org.dxworks.metrixppLizardWrapper.Reader.LizardCSVFileReader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    protected File runTool(Path projectPath, Path outputPath, String imageID) {

        DockerClient client = DockerClientBuilder.getInstance().build();

        CreateContainerResponse response = client.createContainerCmd(imageID)
                .withBinds(
                        Bind.parse(outputPath.toString() + ":/app/dx-results-lizard"),
                        Bind.parse(projectPath.toString() + ":/app/project"))
                .exec();

        String container = response.getId();

        client.startContainerCmd(container).exec();

        client.stopContainerCmd(container).exec();

        client.removeContainerCmd(container).exec();

        File file = Paths.get(outputPath.toString() + "/lizard_output.csv").toFile();

        return file;
    }



}
