package org.dxworks.metrixppLizardWrapper.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.NonNull;
import org.dxworks.metrixppLizardWrapper.Config.Entity.Config;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ConfigReader {
    private static ConfigReader instance;

    private ConfigReader() {}

    public static ConfigReader getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public Config readConfigFile(@NonNull File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory()).findAndRegisterModules();
        return mapper.readValue(file, Config.class);
    }

}
