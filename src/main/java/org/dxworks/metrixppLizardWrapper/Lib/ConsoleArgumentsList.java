package org.dxworks.metrixppLizardWrapper.Lib;

import lombok.Data;
import lombok.ToString;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@Data
@ToString
public class ConsoleArgumentsList {

    private static ConsoleArgumentsList instance = null;

    private Path inputDir;
    private Path outputDir;
    private String lizardImageId;
    private String metrixppImageID;
    private Optional<File> configFile;

    private ConsoleArgumentsList() {}

    public static ConsoleArgumentsList getInstance() {

        if (Objects.isNull(instance)) {
            instance = new ConsoleArgumentsList();

            String input = System.getProperty("inputDir", null);

            String output = System.getProperty("outputDir", null);

            String lizard = System.getProperty("lizardImageID", null);

            String metrixpp = System.getProperty("metrixppImageID", null);

            String config = System.getProperty("config", null);

            if (!Objects.isNull(input)) {
                instance.setInputDir(Paths.get(input));
            } else {
                throw new RuntimeException("No input path provided for property -DinputDir");
            }

            if (!Objects.isNull(output)) {
                instance.setOutputDir(Paths.get(output));
            }

            instance.setLizardImageId(lizard);

            instance.setMetrixppImageID(metrixpp);

            if (!Objects.isNull(config)) {
                instance.setConfigFile(Optional.of(Paths.get(config).toFile()));
            }
        }

        return instance;
    }
}
