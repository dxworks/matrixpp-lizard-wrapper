package org.dxworks.metrixppLizardWrapper.Config;

import org.dxworks.metrixppLizardWrapper.Config.Entity.Config;
import org.dxworks.metrixppLizardWrapper.Config.Entity.ConfigBody;
import org.dxworks.metrixppLizardWrapper.Lib.ConsoleArgumentsList;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class ConfigStorage {

    private static ConfigStorage instance;

    private Config config;

    private ConfigStorage() {
        ConsoleArgumentsList.getInstance().getConfigFile().ifPresent(configFile -> {
            try {
                this.config = ConfigReader.getInstance().readConfigFile(configFile);;
            } catch (IOException ignored) {}
        });
    }

    public static ConfigStorage getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ConfigStorage();
        }
        return instance;
    }

    public Config getConfig() {
        return config;
    }

}
