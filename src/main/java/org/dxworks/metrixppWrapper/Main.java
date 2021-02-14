package org.dxworks.metrixppWrapper;

import org.dxworks.metrixppWrapper.Reader.FileReader;
import org.dxworks.metrixppWrapper.Reader.LizardCSVFileReader;
import org.dxworks.metrixppWrapper.Reader.MetrixppCSVFileReader;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileReader lizardReader = LizardCSVFileReader.getInstance();

        FileReader metrixppReader = MetrixppCSVFileReader.getInstance();

        Path pathLizard = Paths.get("/Users/denisfeier/Documents/metrixPP-wrapper/src/main/resources/lizardOutput.csv");

        Path pathMetrix = Paths.get("/Users/denisfeier/Documents/metrixPP-wrapper/src/main/resources/metrixppOuput.csv");

        lizardReader.readFileCSV(pathLizard.toFile()).forEach(System.out::println);

        System.out.println("\n\n\n\n\n");

        metrixppReader.readFileCSV(pathMetrix.toFile()).forEach(System.out::println);
    }
}
