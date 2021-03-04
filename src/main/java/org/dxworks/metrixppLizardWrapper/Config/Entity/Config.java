package org.dxworks.metrixppLizardWrapper.Config.Entity;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
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
}
