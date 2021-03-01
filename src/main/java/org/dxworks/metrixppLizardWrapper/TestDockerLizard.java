package org.dxworks.metrixppLizardWrapper;

import org.dxworks.metrixppLizardWrapper.Entity.UnifiedOutput;
import org.dxworks.metrixppLizardWrapper.DockerRunner.DockerLizardRunner;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestDockerLizard {
    public static void main(String[] args) throws FileNotFoundException {

        Path input = Paths.get("/Users/denisfeier/Documents/node-cu-george");

        Path output = Paths.get("/Users/denisfeier/Documents/metrixPP-wrapper/res");

        List<UnifiedOutput> outputs = DockerLizardRunner.runLizard(input, output, "93db6ce88c38");

        outputs.forEach(System.out::println);
    }
}
