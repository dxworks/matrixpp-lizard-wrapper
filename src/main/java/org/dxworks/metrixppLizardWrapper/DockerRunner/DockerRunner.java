package org.dxworks.metrixppLizardWrapper.DockerRunner;

import org.dxworks.metrixppLizardWrapper.Entity.UnifiableOutput;
import org.dxworks.metrixppLizardWrapper.Entity.UnifiedOutput;
import org.dxworks.metrixppLizardWrapper.Reader.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DockerRunner <A extends UnifiableOutput> {

    public List<UnifiedOutput> getUnified(Path projectPath, Path outputPath, String imageID) throws FileNotFoundException {
        File results = runTool(projectPath, outputPath, imageID);

        return unifyResults(results);
    }

    protected abstract FileReader<A> getFileReader();

    protected abstract File runTool(Path projectPath, Path outputPath, String imageID);

    protected List<UnifiedOutput> unifyResults(File file) throws FileNotFoundException {
        return getFileReader().readFileCSV(file)
                .stream()
                .map(UnifiableOutput::unify)
                .collect(Collectors.toList());
    }

}
