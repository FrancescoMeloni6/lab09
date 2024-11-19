package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser{

    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame();
    private Controller controller = new Controller();

    public SimpleGUIWithFileChooser() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("File Chooser");
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        final JPanel panel = new JPanel();
        canvas.add(panel, BorderLayout.NORTH);
        final JTextField textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse...");
        panel.add(browse, BorderLayout.LINE_END);
        textField.setEditable(false);
        textField.setText(controller.getPath());
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.readAndSave(textArea.getText());
                    textArea.setText("");
                    JOptionPane.showMessageDialog(frame, "String saved successfully!", "String saved", 
                        JOptionPane.DEFAULT_OPTION);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });
        browse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(browse) == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(Objects.requireNonNull(fileChooser.getSelectedFile()));
                    textField.setText(controller.getPath());
                } else if (fileChooser.showSaveDialog(browse) != JFileChooser.CANCEL_OPTION) { 
                    JOptionPane.showMessageDialog(browse, "An Error has occurred", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
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

    public void start() {
        display();
    }

}
