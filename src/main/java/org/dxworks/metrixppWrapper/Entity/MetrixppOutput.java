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
    private String lineStart;

    @CsvBindByName(column = "line end")
    private String lineEnd;

    @CsvBindByName(column = "std.code.complexity:cyclomatic")
    private String cyclomaticComplexity;

    @CsvBindByName(column = "std.code.complexity:maxindent")
    private String maxindentComplexity;

    @CsvBindByName(column = "std.code.lines:code")
    private String codeLines;

    @CsvBindByName(column = "std.code.lines:preprocessor")
    private String preprocessorLines;

    @CsvBindByName(column = "std.code.lines:comments")
    private String commentsLines;

    @CsvBindByName(column = "std.code.mi:simple")
    private String simpleMi;

    @CsvBindByName(column = "std.code.member:globals")
    private String globalsMember;

}
