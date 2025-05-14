package part1.app;

import javax.swing.JFrame;

import java.awt.BorderLayout;

public class CalculatorApp extends JFrame {
    public CalculatorApp() {
        setTitle("Primitive Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        DisplayPanel display = new DisplayPanel();
        ButtonPanel btns = new ButtonPanel(display);

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(btns, BorderLayout.CENTER);

        setVisible(true);
    }
}
