package org.dxworks.metrixppWrapper.Reader;

import org.dxworks.metrixppWrapper.Entity.UnifiableOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileReader <A extends UnifiableOutput> {

    List<A> readFileCSV(File path) throws FileNotFoundException;
}
