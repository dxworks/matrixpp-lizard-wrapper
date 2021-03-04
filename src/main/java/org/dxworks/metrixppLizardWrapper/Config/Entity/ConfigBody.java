package org.dxworks.metrixppLizardWrapper.Config.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigBody {
    private List<ConfigPair> lizard;
    private List<ConfigPair> metrixpp;
}
