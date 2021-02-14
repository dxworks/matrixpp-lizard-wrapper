package org.dxworks.metrixppWrapper.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileReader <A> {

    List<A> readFileCSV(File path) throws FileNotFoundException;
}
