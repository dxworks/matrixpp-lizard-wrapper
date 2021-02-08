package org.dxworks.metrixppWrapper.dockerRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DockerRunner {

    static public void execute() {
        String line;
        try {
            Process proc = Runtime.getRuntime().exec("docker-compose run metrixpp");
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
//            oStream .write("process where name='explorer.exe'");
//            oStream .flush();
//            oStream .close();
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Done with the execution");
            input.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
