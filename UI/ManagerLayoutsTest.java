package UI;

import javax.swing.*;
import java.awt.*;

public class ManagerLayoutsTest extends JFrame {
    public ManagerLayoutsTest() {
        super("ManagerLayoutsTest");
        setSize(320, 240);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание панели с табличным расположением
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));
        // Добавление кнопок в панель
        grid.add(new JButton("OK"));
        grid.add(new JButton("Отмена"));
        // Создание панели с последовательным расположением
        // компонентов и выравниванием по правому краю
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(grid);
        // Получение панели содержимого
        Container container = getContentPane();
        // Размещение панели с кнопками внизу справа
        container.add(flow, BorderLayout.SOUTH);
        // Открытие окна
        setVisible(true);
    }

    public static void main(String[] args) {
        new ManagerLayoutsTest();
    }
}