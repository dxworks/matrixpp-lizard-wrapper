package org.dxworks.metrixppLizardWrapper.Lib;

import com.google.gson.Gson;
import org.dxworks.metrixppLizardWrapper.Config.ConfigStorage;
import org.dxworks.metrixppLizardWrapper.Config.Entity.ConfigPair;
import org.dxworks.metrixppLizardWrapper.DockerRunner.DockerLizardRunner;
import org.dxworks.metrixppLizardWrapper.DockerRunner.DockerMetrixppRunner;
import org.dxworks.metrixppLizardWrapper.DockerRunner.DockerRunner;
import org.dxworks.metrixppLizardWrapper.Entity.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class AppRunner {

    public static void bootstrap() {
        OutputWithMetaInfo outputWithMetaInfo = init();

        String lizardImage = ConsoleArgumentsList.getInstance().getLizardImageId();

        String metrixppImage = ConsoleArgumentsList.getInstance().getMetrixppImageID();

        List<UnifiedOutput> outputs = outputWithMetaInfo.getResults();

        if (!Objects.isNull(lizardImage)) {
            try {

                List<UnifiedOutput> outputsLizard = runLizard();

                outputs.addAll(outputsLizard);

                if (outputsLizard.size() != 0) {
                    outputWithMetaInfo.getMetaInfo().setLizardStatus(ToolStatus.SUCCESS);
                }

            } catch (Exception e) {
                e.printStackTrace();
                outputWithMetaInfo.getMetaInfo().setLizardStatus(ToolStatus.FAILURE);
            }
        } else {
            outputWithMetaInfo.getMetaInfo().setLizardStatus(ToolStatus.NOT_RUN);
        }

        if (!Objects.isNull(metrixppImage)) {
            try {

                List<UnifiedOutput> outputsMetrixpp = runMetrixpp();

                outputs.addAll(outputsMetrixpp);

                if (outputsMetrixpp.size() != 0) {
                    outputWithMetaInfo.getMetaInfo().setMetrixppStatus(ToolStatus.SUCCESS);
                }

            } catch (Exception e) {
                e.printStackTrace();
                outputWithMetaInfo.getMetaInfo().setMetrixppStatus(ToolStatus.FAILURE);
            }
        } else {
            outputWithMetaInfo.getMetaInfo().setMetrixppStatus(ToolStatus.NOT_RUN);
        }

        Gson gson = new Gson();

        long endRun = System.currentTimeMillis();

        outputWithMetaInfo.getMetaInfo().setDuration(endRun - outputWithMetaInfo.getMetaInfo().getTimestamp());

        String jsonOutput = gson.toJson(outputWithMetaInfo);

//        System.out.println(jsonOutput);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(getOutputPath()))) {
            writer.write(jsonOutput);
        } catch(IOException e){
            throw new RuntimeException("Couldn't write to output file");
        }
    }

    private static File getOutputPath() {
        Path output = ConsoleArgumentsList.getInstance().getOutputDir();

        String outputFilePath = output.toString() + "/results.json";

        return Paths.get(outputFilePath).toFile();
    }

    private static OutputWithMetaInfo init() {
        MetaInfo metaInfo = new MetaInfo();

        OutputWithMetaInfo finalOutput = new OutputWithMetaInfo();

        finalOutput.setMetaInfo(metaInfo);

        long beginTime = System.currentTimeMillis();

        metaInfo.setTimestamp(beginTime);

        metaInfo.setLizardStatus(ToolStatus.NOT_RUN);

        metaInfo.setMetrixppStatus(ToolStatus.NOT_RUN);

        List<UnifiedOutput> results = new LinkedList<>();

        finalOutput.setResults(results);

        return finalOutput;
    }

    private static List<UnifiedOutput> runLizard() throws FileNotFoundException {

        String image = ConsoleArgumentsList.getInstance().getLizardImageId();

        List<ConfigPair> config = ConfigStorage.getInstance().getConfig().getConfig().getLizard();

        return runTool(image, config, DockerLizardRunner.getInstance());

    }

    private static List<UnifiedOutput> runMetrixpp() throws FileNotFoundException {

        String image = ConsoleArgumentsList.getInstance().getMetrixppImageID();

        List<ConfigPair> config = ConfigStorage.getInstance().getConfig().getConfig().getMetrixpp();

        return runTool(image, config, DockerMetrixppRunner.getInstance());

    }

    private static <A extends UnifiableOutput> List<UnifiedOutput> runTool(String image, List<ConfigPair> configPairs, DockerRunner<A> runner) throws FileNotFoundException {
        Path input = ConsoleArgumentsList.getInstance().getInputDir();

        Path output = ConsoleArgumentsList.getInstance().getOutputDir();

        return runner.getUnified(input, output, image, configPairs);
    }

}
