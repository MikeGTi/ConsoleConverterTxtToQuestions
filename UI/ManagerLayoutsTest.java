package UI;

import javax.swing.*;
import java.awt.*;

public class ManagerLayoutsTest extends JFrame {
    public ManagerLayoutsTest() {
        super("ManagerLayoutsTest");
        setSize(320, 240);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // �������� ������ � ��������� �������������
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));
        // ���������� ������ � ������
        grid.add(new JButton("OK"));
        grid.add(new JButton("������"));
        // �������� ������ � ���������������� �������������
        // ����������� � ������������� �� ������� ����
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(grid);
        // ��������� ������ �����������
        Container container = getContentPane();
        // ���������� ������ � �������� ����� ������
        container.add(flow, BorderLayout.SOUTH);
        // �������� ����
        setVisible(true);
    }

    public static void main(String[] args) {
        new ManagerLayoutsTest();
    }
}