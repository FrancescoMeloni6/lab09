package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    
    private final static String OUTPUT_FILE = System.getProperty("user.home") +
        System.getProperty("file.separator") + "output.txt";

    private File file;

    public Controller() {
        file = new File(OUTPUT_FILE);
    }

    public Controller(final File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPath() {
        return this.file.getAbsolutePath();
    }

    public void readAndSave(final String string) throws IOException{
        Objects.requireNonNull(string);
        PrintStream ps = new PrintStream(file);
        ps.println(string);
        ps.close();
    }
}
