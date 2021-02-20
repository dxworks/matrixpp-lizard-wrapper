package org.dxworks.metrixppWrapper;

import com.google.gson.Gson;
import org.dxworks.metrixppWrapper.Entity.*;
import org.dxworks.metrixppWrapper.Reader.FileReader;
import org.dxworks.metrixppWrapper.Reader.LizardCSVFileReader;
import org.dxworks.metrixppWrapper.Reader.MetrixppCSVFileReader;
import org.dxworks.metrixppWrapper.dockerRunner.DockerLizardRunner;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileReader<LizardOutput> lizardReader = LizardCSVFileReader.getInstance();

        FileReader<MetrixppOutput> metrixppReader = MetrixppCSVFileReader.getInstance();

        Path pathLizard = Paths.get("/Users/denisfeier/Documents/metrixPP-wrapper/src/main/resources/lizardOutput.csv");

        Path pathMetrix = Paths.get("/Users/denisfeier/Documents/metrixPP-wrapper/src/main/resources/metrixppOuput.csv");

        Gson gson = new Gson();

        List<UnifiedOutput> outputLizard = lizardReader.readFileCSV(pathLizard.toFile()).stream().map(LizardOutput::unify).collect(Collectors.toList());

        List<UnifiedOutput> outputMetrixpp = metrixppReader.readFileCSV(pathMetrix.toFile()).stream().map(MetrixppOutput::unify).collect(Collectors.toList());

        List<UnifiedOutput> output = Stream.concat(outputLizard.stream(), outputMetrixpp.stream()).sorted((e1, e2) -> e1.getFile().compareToIgnoreCase(e2.getFile())).collect(Collectors.toList());

        OutputWithMetaInfo outputWithMetaInfo = new OutputWithMetaInfo();

        MetaInfo metaInfo = new MetaInfo();

        metaInfo.setTimestamp(System.currentTimeMillis());

        outputWithMetaInfo.setResults(output);
        outputWithMetaInfo.setMetaInfo(metaInfo);

        System.out.println(gson.toJson(outputWithMetaInfo));
    }
}
