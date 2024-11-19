package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private List<String> history;
    private String string;


    public SimpleController() {
        this.history = new LinkedList<>();
        string = null;
    }

    @Override
    public void setNextString(String nextString) {
        this.string = nextString;
    }

    @Override
    public String getNextString() {
        return string;
    }

    @Override
    public List<String> getHistory() {
        return this.history;
    }

    @Override
    public void printCurrentString() {
        if (string == null) {
            throw new IllegalStateException("The current string does not exist");
        }
        history.add(getNextString());
        System.out.println(getNextString());
        setNextString(null);
    }

}
