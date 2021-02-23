package org.dxworks.metrixppWrapper;

import org.dxworks.metrixppWrapper.Entity.UnifiedOutput;
import org.dxworks.metrixppWrapper.DockerRunner.DockerLizardRunner;

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
