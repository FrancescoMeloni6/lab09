package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame();

    public SimpleGUI(final Controller controller){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel(new BorderLayout());
        frame.setContentPane(panel); 
        final JTextField textField = new JTextField();
        panel.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(textArea, BorderLayout.CENTER);
        final JPanel buttonsPanel = new JPanel(new BorderLayout());
        panel.add(buttonsPanel, BorderLayout.SOUTH);
        final JButton print = new JButton("Print");
        buttonsPanel.add(print, BorderLayout.WEST);
        final JButton history = new JButton("Show history");
        buttonsPanel.add(history, BorderLayout.EAST);
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.setNextString(textField.getText());
                textField.setText(null);
                controller.printCurrentString();
            }
        });
        history.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String out = "";
                for (String string : controller.getHistory()) {
                    out += string + "\n";
                }
                textArea.setText(out);
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        this.frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        this.frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
