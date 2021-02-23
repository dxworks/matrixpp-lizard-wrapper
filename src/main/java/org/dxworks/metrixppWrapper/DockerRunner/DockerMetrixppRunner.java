package org.dxworks.metrixppWrapper.DockerRunner;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.core.DockerClientBuilder;
import org.dxworks.metrixppWrapper.Entity.LizardOutput;
import org.dxworks.metrixppWrapper.Entity.MetrixppOutput;
import org.dxworks.metrixppWrapper.Entity.UnifiedOutput;
import org.dxworks.metrixppWrapper.Reader.FileReader;
import org.dxworks.metrixppWrapper.Reader.LizardCSVFileReader;
import org.dxworks.metrixppWrapper.Reader.MetrixppCSVFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DockerMetrixppRunner {

    public static List<UnifiedOutput> runMetrixpp(Path projectPath, Path outputPath, String metrixppImage) throws FileNotFoundException {

        DockerClient client = DockerClientBuilder.getInstance().build();

        CreateContainerResponse response = client.createContainerCmd(metrixppImage)
                .withBinds(
                        Bind.parse(outputPath.toString() + ":/usr/analysis/result"),
                        Bind.parse(projectPath.toString() + ":/usr/analysis/sources"))
                .withEnv("PROJECT_ID=test")
                .exec();

        String container = response.getId();

        client.startContainerCmd(container).exec();

        client.stopContainerCmd(container).exec();

        client.removeContainerCmd(container).exec();

        FileReader<MetrixppOutput> metrixppOutput = MetrixppCSVFileReader.getInstance();

        File file = Paths.get(outputPath.toString() + "/test.csv").toFile();

        return metrixppOutput.readFileCSV(file).stream().map(MetrixppOutput::unify).collect(Collectors.toList());

    }
}
