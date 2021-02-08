package org.dxworks.metrixppWrapper.Entity;

import com.opencsv.bean.CsvBindByName;

// Don't use lombok in a csv entity. OpenCSV can't work with a on the fly generated getter or setter
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getLineStart() {
        return lineStart;
    }

    public void setLineStart(String lineStart) {
        this.lineStart = lineStart;
    }

    public String getLineEnd() {
        return lineEnd;
    }

    public void setLineEnd(String lineEnd) {
        this.lineEnd = lineEnd;
    }

    public String getCyclomaticComplexity() {
        return cyclomaticComplexity;
    }

    public void setCyclomaticComplexity(String cyclomaticComplexity) {
        this.cyclomaticComplexity = cyclomaticComplexity;
    }

    public String getMaxindentComplexity() {
        return maxindentComplexity;
    }

    public void setMaxindentComplexity(String maxindentComplexity) {
        this.maxindentComplexity = maxindentComplexity;
    }

    public String getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(String codeLines) {
        this.codeLines = codeLines;
    }

    public String getPreprocessorLines() {
        return preprocessorLines;
    }

    public void setPreprocessorLines(String preprocessorLines) {
        this.preprocessorLines = preprocessorLines;
    }

    public String getCommentsLines() {
        return commentsLines;
    }

    public void setCommentsLines(String commentsLines) {
        this.commentsLines = commentsLines;
    }

    public String getSimpleMi() {
        return simpleMi;
    }

    public void setSimpleMi(String simpleMi) {
        this.simpleMi = simpleMi;
    }

    public String getGlobalsMember() {
        return globalsMember;
    }

    public void setGlobalsMember(String globalsMember) {
        this.globalsMember = globalsMember;
    }

    @Override
    public String toString() {
        return "MetrixppOutput{" +
                "file='" + file + '\'' +
                ", region='" + region + '\'' +
                ", type='" + type + '\'' +
                ", modified='" + modified + '\'' +
                ", lineStart='" + lineStart + '\'' +
                ", lineEnd='" + lineEnd + '\'' +
                ", cyclomaticComplexity='" + cyclomaticComplexity + '\'' +
                ", maxindentComplexity='" + maxindentComplexity + '\'' +
                ", codeLines='" + codeLines + '\'' +
                ", preprocessorLines='" + preprocessorLines + '\'' +
                ", commentsLines='" + commentsLines + '\'' +
                ", simpleMi='" + simpleMi + '\'' +
                ", globalsMember='" + globalsMember + '\'' +
                '}';
    }
}
