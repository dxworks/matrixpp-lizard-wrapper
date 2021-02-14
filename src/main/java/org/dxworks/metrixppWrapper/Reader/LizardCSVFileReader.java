package org.dxworks.metrixppWrapper.Reader;

import org.dxworks.metrixppWrapper.Entity.LizardOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class LizardCSVFileReader implements FileReader<LizardOutput> {

    private LizardCSVFileReader() {}

    private static final FileReader<LizardOutput> shared = new LizardCSVFileReader();

    public static FileReader<LizardOutput> getInstance() {
        return shared;
    }

    public List<LizardOutput> readFileCSV(File path) throws FileNotFoundException {

        return CSVFileReader.readCSV(path, LizardOutput.class);
    }
}
