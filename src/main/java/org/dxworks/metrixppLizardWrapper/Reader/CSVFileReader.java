package org.dxworks.metrixppLizardWrapper.Reader;

import com.opencsv.bean.CsvToBeanBuilder;
import org.dxworks.metrixppLizardWrapper.Entity.UnifiableOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

class CSVFileReader {

    private CSVFileReader() {}

    public static <A extends UnifiableOutput> List<A> readCSV(File file, Class<A> aClass) throws FileNotFoundException {

        List beans = new CsvToBeanBuilder(new FileReader(file))
                .withType(aClass)
                .build()
                .parse();

        return beans;
    }

}
