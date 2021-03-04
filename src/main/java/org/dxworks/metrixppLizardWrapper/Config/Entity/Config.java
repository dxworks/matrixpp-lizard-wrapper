package org.dxworks.metrixppLizardWrapper.Config.Entity;

import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Config {
    private ConfigBody config;

    public static List<String> getEnvStrings(@NonNull List<ConfigPair> pairs) {
        return pairs.stream()
                .map((pair)-> pair.getKey() + "=" + pair.getValue())
                .collect(Collectors.toList());
    }

    public ConfigBody getConfig() {

        if (Objects.isNull(this.config)) {
            return new ConfigBody(new LinkedList<>(), new LinkedList<>());
        }

        return config;
    }
}
