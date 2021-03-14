package org.dxworks.metrixppLizardWrapper.DockerRunner;

import lombok.NonNull;
import org.dxworks.metrixppLizardWrapper.Config.Entity.ConfigPair;
import org.dxworks.metrixppLizardWrapper.Entity.UnifiableOutput;
import org.dxworks.metrixppLizardWrapper.Entity.UnifiedOutput;
import org.dxworks.metrixppLizardWrapper.Reader.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DockerRunner <A extends UnifiableOutput> {

    public List<UnifiedOutput> getUnified(@NonNull Path projectPath, @NonNull Path outputPath, @NonNull String imageID, List<ConfigPair> configs) throws FileNotFoundException, InterruptedException {
        File results = runTool(projectPath, outputPath, imageID, configs);

        return unifyResults(results);
    }

    protected abstract FileReader<A> getFileReader();

    protected abstract File runTool(Path projectPath, Path outputPath, String imageID, List<ConfigPair> configs) throws InterruptedException;

    protected List<UnifiedOutput> unifyResults(File file) throws FileNotFoundException {
        return getFileReader().readFileCSV(file)
                .stream()
                .map(UnifiableOutput::unify)
                .collect(Collectors.toList());
    }

}
