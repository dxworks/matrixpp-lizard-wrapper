package org.dxworks.metrixppWrapper;

import com.opencsv.bean.CsvToBeanBuilder;
import org.dxworks.metrixppWrapper.Entity.MetrixppOutput;
import org.dxworks.metrixppWrapper.dockerRunner.DockerRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("metrix++ wrapper");

        String fileName = "/Users/denisfeier/Documents/metrixPP-wrapper/results/test.csv";

        DockerRunner.execute();

        List beans = new CsvToBeanBuilder(new FileReader(fileName))
                        .withType(MetrixppOutput.class)
                        .build()
                        .parse();

        beans.forEach(System.out::println);

    }
}
