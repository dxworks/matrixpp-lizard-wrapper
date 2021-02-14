package org.dxworks.metrixppWrapper.Entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LizardOutput {

    @CsvBindByPosition(position = 0)
    private Integer NLOC;

    @CsvBindByPosition(position = 1)
    private Integer CCN;

    @CsvBindByPosition(position = 2)
    private Integer token;

    @CsvBindByPosition(position = 3)
    private Integer param;

    @CsvBindByPosition(position = 4)
    private Integer length;

    @CsvBindByPosition(position = 5)
    private String location;

    @CsvBindByPosition(position = 6)
    private String path;

    @CsvBindByPosition(position = 7)
    private String methodeNameAndContext;

    @CsvBindByPosition(position = 8)
    private String methodSignature;

    @CsvBindByPosition(position = 9)
    private Integer lineStart;

    @CsvBindByPosition(position = 10)
    private Integer lineEnd;
}
