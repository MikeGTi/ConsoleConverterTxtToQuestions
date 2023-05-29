package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JButton jButton = new JButton("path");
    private JTextField jTextField = new JTextField("c:\\",5);
    private JLabel jLabel = new JLabel("Input: ");
    private JRadioButton jRadioButton1 = new JRadioButton("Select this");
    private JRadioButton jRadioButton2 = new JRadioButton("Select that");
    private JCheckBox jCheckBox = new JCheckBox("Check",false);

    public GUI(){
        super("Select folder");
        this.setBounds(100,100,400,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
        container.add(jLabel);
        container.add(jTextField);

        //setup radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        jRadioButton1.setSelected(true);

        container.add(jRadioButton1);
        container.add(jRadioButton2);

        container.add(jCheckBox);

        //setup button
        jButton.addActionListener(new ButtonEventListener());

        container.add(jButton);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e){
            String message = "";
            message += "Button pressed\n" +
                       "Text is: " + jTextField.getText() + "\n" +
                       (jRadioButton1.isSelected() ? "Radio #1" : "Radio #2") + " is selected\n" +
                       "CheckBox is " + (jCheckBox.isSelected() ? "checked" : "unchecked") + "\n";
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }

}
