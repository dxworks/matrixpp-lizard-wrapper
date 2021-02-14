package org.dxworks.metrixppWrapper.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UnifiedOutput {

    private LizardOutput lizardOutput;

    private MetrixppOutput metrixppOutput;

}
