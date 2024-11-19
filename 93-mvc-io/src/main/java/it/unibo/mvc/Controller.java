package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
     * Set the next string to print.
     * @param nextString 
     */
    public void setNextString(String nextString);

    /**
     * @return the next string to print.
     */
    public String getNextString();

    /**
     * @return the history of the printed strings.
     */
    public List<String> getHistory();

    /**
     * Print the current string.
     * @exception IllegalStateException if the current string is unset
     */
    public void printCurrentString();
}
