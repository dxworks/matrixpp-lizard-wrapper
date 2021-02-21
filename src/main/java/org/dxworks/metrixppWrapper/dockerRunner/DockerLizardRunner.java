package org.dxworks.metrixppWrapper.dockerRunner;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.core.DockerClientBuilder;
import org.dxworks.metrixppWrapper.Entity.LizardOutput;
import org.dxworks.metrixppWrapper.Entity.UnifiedOutput;
import org.dxworks.metrixppWrapper.Reader.FileReader;
import org.dxworks.metrixppWrapper.Reader.LizardCSVFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DockerLizardRunner {

    public static List<UnifiedOutput> runLizard(Path projectPath, Path outputPath, String lizardImage) throws FileNotFoundException {
        DockerClient client = DockerClientBuilder.getInstance().build();

        CreateContainerResponse response = client.createContainerCmd(lizardImage)
                .withBinds(
                        Bind.parse(outputPath.toString() + ":/app/dx-results-lizard"),
                        Bind.parse(projectPath.toString() + ":/app/project"))
                .exec();

        String container = response.getId();

        client.startContainerCmd(container).exec();

        client.stopContainerCmd(container).exec();

        client.removeContainerCmd(container).exec();

        FileReader<LizardOutput> lizardReader = LizardCSVFileReader.getInstance();

        File file = Paths.get(outputPath.toString() + "/lizard_output.csv").toFile();

        return lizardReader.readFileCSV(file).stream().map(LizardOutput::unify).collect(Collectors.toList());

    }

}
