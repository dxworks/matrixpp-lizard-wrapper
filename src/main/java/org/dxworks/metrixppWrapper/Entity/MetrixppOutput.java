package org.dxworks.metrixppWrapper.Entity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MetrixppOutput {

    @CsvBindByName(column = "file")
    private String file;

    @CsvBindByName(column = "region")
    private String region;

    @CsvBindByName(column = "type")
    private String type;

    @CsvBindByName(column = "modified")
    private String modified;

    @CsvBindByName(column = "line start")
    private Integer lineStart;

    @CsvBindByName(column = "line end")
    private Integer lineEnd;

    @CsvBindByName(column = "std.code.complexity:cyclomatic")
    private Integer cyclomaticComplexity;

    @CsvBindByName(column = "std.code.complexity:maxindent")
    private Integer maxindentComplexity;

    @CsvBindByName(column = "std.code.lines:code")
    private Integer codeLines;

    @CsvBindByName(column = "std.code.lines:preprocessor")
    private Integer preprocessorLines;

    @CsvBindByName(column = "std.code.lines:comments")
    private Integer commentsLines;

    @CsvBindByName(column = "std.code.mi:simple")
    private Integer simpleMi;

    @CsvBindByName(column = "std.code.member:globals")
    private Integer globalsMember;

}
