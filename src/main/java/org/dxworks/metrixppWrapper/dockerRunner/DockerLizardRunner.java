package org.dxworks.metrixppWrapper.dockerRunner;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.core.DockerClientBuilder;

public class DockerLizardRunner {

    public void runLizard() {
        DockerClient client = DockerClientBuilder.getInstance().build();

        CreateContainerResponse response = client.createContainerCmd("a8db6b20e9a7")
                .withBinds(
                        Bind.parse("/Users/denisfeier/Documents/metrixPP-wrapper/ceva:/app/dx-results-lizard"),
                        Bind.parse("/Users/denisfeier/Documents/metrixPP-wrapper:/app/project"))
                .exec();

        String container = response.getId();

        client.startContainerCmd(container).exec();
    }

}
