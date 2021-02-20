package org.dxworks.metrixppWrapper.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutputWithMetaInfo {

    private MetaInfo metaInfo;

    private List<UnifiedOutput> results;

}
