package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI2 extends JFrame {
    private static final int HEIGHT = 240;
    private static final int WIDTH = 320;
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");
    private JTextField textFieldPath = new JTextField("c:\\",5);
    private JLabel labelPath = new JLabel("Get data from ");
    private JRadioButton jRadioButton1 = new JRadioButton("Get All");
    private JRadioButton jRadioButton2 = new JRadioButton("Only bin");

    private JCheckBox jCheckBox = new JCheckBox("Check",false);

    public GUI2(){
        super("Questions");

        this.setSize(WIDTH, HEIGHT);
        this.setLocation(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(122,122,WIDTH,HEIGHT);
        this.setMaximizedBounds(new Rectangle(WIDTH, HEIGHT));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        jRadioButton1.setSelected(true);
        btnOk.addActionListener(new ButtonEventListener());

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
        //container.add(new JLabel(""));
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
        GUI2 gui2 = new GUI2();
        gui2.setVisible(true);

    }
}
