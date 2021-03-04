package org.dxworks.metrixppLizardWrapper;

import org.dxworks.metrixppLizardWrapper.Config.ConfigReader;
import org.dxworks.metrixppLizardWrapper.Config.Entity.Config;

import java.io.IOException;
import java.nio.file.Paths;

public class TestConfig {
    public static void main(String[] args) throws IOException {
        Config config = ConfigReader.getInstance().readConfigFile(Paths.get("/Users/denisfeier/Documents/metrixPP-wrapper/src/main/resources/config.yaml").toFile());

        System.out.println(config);
    }
}
