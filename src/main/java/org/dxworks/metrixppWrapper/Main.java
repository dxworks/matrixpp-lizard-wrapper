package org.dxworks.metrixppWrapper;

import com.opencsv.bean.CsvToBeanBuilder;
import org.dxworks.metrixppWrapper.Entity.MetrixppOutput;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("metrix++ wrapper");

        String fileName = "/Users/denisfeier/Documents/metrixPP-wrapper/src/main/resources/text.csv";

        List beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(MetrixppOutput.class)
                .build()
                .parse();

        beans.forEach(System.out::println);

    }
}
