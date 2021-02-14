package org.dxworks.metrixppWrapper.Reader;

import org.dxworks.metrixppWrapper.Entity.MetrixppOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class MetrixppCSVFileReader implements FileReader<MetrixppOutput>{

    private MetrixppCSVFileReader() {}

    private static final FileReader<MetrixppOutput> shared = new MetrixppCSVFileReader();

    public static FileReader<MetrixppOutput> getInstance() {
        return shared;
    }

    public List<MetrixppOutput> readFileCSV(File path) throws FileNotFoundException {

        return CSVFileReader.readCSV(path, MetrixppOutput.class);
    }
}
