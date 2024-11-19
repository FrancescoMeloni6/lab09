package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private SimpleGUI mainView;
    private SimpleGUIWithFileChooser fileChooserView;
    
    private final static String OUTPUT_FILE = System.getProperty("user.home") +
        System.getProperty("file.separator") + "output.txt";

    private File file;

    public Controller() {
        this(new File(OUTPUT_FILE));
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
        PrintStream ps = new PrintStream(file);
        ps.println(string);
        ps.close();
    }

    public void setViews(final SimpleGUI mainView, final SimpleGUIWithFileChooser fileChooserView) {
        this.mainView = mainView;
        mainView.setController(this);
        mainView.start();
        this.fileChooserView = fileChooserView;
        fileChooserView.setController(this);
        fileChooserView.start();
    }

    public static void main(String[] args) {
        new Controller().setViews(new SimpleGUI(), new SimpleGUIWithFileChooser());
    }
}
