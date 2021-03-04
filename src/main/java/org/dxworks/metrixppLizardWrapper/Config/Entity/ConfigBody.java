package org.dxworks.metrixppLizardWrapper.Config.Entity;

import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigBody {
    private List<ConfigPair> lizard;
    private List<ConfigPair> metrixpp;

    public List<ConfigPair> getLizard() {

        if (Objects.isNull(this.lizard)) {
            return new LinkedList<>();
        }

        return lizard;
    }

    public List<ConfigPair> getMetrixpp() {

        if (Objects.isNull(this.metrixpp)) {
            return new LinkedList<>();
        }

        return metrixpp;
    }
}
