package org.dxworks.metrixppLizardWrapper.Config;

import org.dxworks.metrixppLizardWrapper.Config.Entity.Config;
import org.dxworks.metrixppLizardWrapper.Config.Entity.ConfigBody;
import org.dxworks.metrixppLizardWrapper.Lib.ConsoleArgumentsList;

import java.io.File;
import java.util.Objects;

public class ConfigStorage {

    private static ConfigStorage instance;

    private Config config;

    private ConfigStorage() {}

    public static ConfigStorage getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ConfigStorage();
            instance.config = new Config();
            try {
                File configFile = ConsoleArgumentsList.getInstance().getConfigFile();
                if (!Objects.isNull(configFile)) {
                    instance.config = ConfigReader.getInstance().readConfigFile(configFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Config getConfig() {
        return config;
    }

}
