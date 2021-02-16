package org.dxworks.metrixppWrapper.Entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LizardOutput implements UnifiableOutput {

    @CsvBindByPosition(position = 0)
    private Integer NLOC; // lines of code without comments

    @CsvBindByPosition(position = 1)
    private Integer CCN; // cyclomatic complexity number

    @CsvBindByPosition(position = 2)
    private Integer token;

    @CsvBindByPosition(position = 3)
    private Integer param;

    @CsvBindByPosition(position = 4)
    private Integer length;

    @CsvBindByPosition(position = 5)
    private String location;

    @CsvBindByPosition(position = 6)
    private String file;

    @CsvBindByPosition(position = 7)
    private String methodeNameAndContext;

    @CsvBindByPosition(position = 8)
    private String methodSignature;

    @CsvBindByPosition(position = 9)
    private Integer lineStart;

    @CsvBindByPosition(position = 10)
    private Integer lineEnd;

    @Override
    public UnifiedOutput unify() {
        return UnifiedOutput.builder()
                .source(SourceAnalyzer.Lizard)
                .dateOfAnalysis(LocalDate.now())
                .file(this.file)
                .methodeNameAndContext(this.methodeNameAndContext)
                .methodSignature(this.methodSignature)
                .lineEnd(this.lineEnd)
                .lineStart(this.lineStart)
                .location(this.location)
                .length(this.length)
                .param(this.param)
                .token(this.token)
                .cyclomaticComplexity(this.CCN)
                .codeLines(this.NLOC)
                .commentsLines(this.length - this.NLOC)
                .build();
    }
}
