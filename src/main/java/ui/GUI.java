package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private static final int WIDTH = 240;
    private static final int HEIGHT = 120;
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");
    private JTextField textFieldPath = new JTextField("c:\\",5);
    private JLabel labelPath = new JLabel("Source: ");
    private JRadioButton jRadioButton1 = new JRadioButton("Get All");
    private JRadioButton jRadioButton2 = new JRadioButton("Only bin");

    private JCheckBox jCheckBox = new JCheckBox("Check",false);

    public GUI(){

        this.setTitle("Questions");
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setBounds(122,122,WIDTH,HEIGHT);
        //this.setMaximizedBounds(new Rectangle(WIDTH, HEIGHT));

        /*Border border = new AbstractBorder() {
        };
        jRadioButton1.setBorder(border);*/

        ButtonGroup buttonGroup = new ButtonGroup();
                    buttonGroup.add(jRadioButton1);
                    buttonGroup.add(jRadioButton2);
        jRadioButton1.setSelected(true);
        btnOk.addActionListener(new ButtonEventListener());

        Container container = this.getContentPane();
                  container.setLayout(new GridLayout(3,2,2,2));
                  container.add(labelPath);
                  container.add(textFieldPath);
                  container.add(jRadioButton1);
                  container.add(jRadioButton2);
                  container.add(jCheckBox);
                  container.add(btnOk);
                //container.add(new JLabel(""));
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e){
            String message = "";
            message += "Button pressed\n" +
                       "Text is: " + textFieldPath.getText() + "\n" +
                       (jRadioButton1.isSelected() ? "Radio #1" : "Radio #2") + " is selected\n" +
                       "CheckBox is " + (jCheckBox.isSelected() ? "checked" : "unchecked") + "\n";
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }


    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);

    }
}
