package part1.app;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
    private final String[][] buttons = {
            { "1", "2", "3", "/" },
            { "4", "5", "6", "*" },
            { "7", "8", "9", "-" },
            { ".", "0", "=", "+" },
            { "C", "(", ")", "b" }
    };
    private Calc calc;

    public ButtonPanel(DisplayPanel display) {
        setLayout(new GridLayout(5, 4, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        calc = new Calc();

        ActionListener eventListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command == "C") {
                    display.setExpression("");
                } else if (command == "b") {
                    String temp = display.getExpression();
                    if (temp.length() > 0) {
                        temp = temp.substring(0, temp.length() - 1);
                        display.setExpression(temp);
                    }
                } else if (command == "=") {
                    String expression = display.getExpression();
                    calc.setExpression(expression);
                    display.setExpression(calc.calc());
                } else {
                    display.setExpression(display.getExpression() + command);
                }
            };
        };

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                JButton button = new JButton(buttons[row][col]);
                button.setFont(new Font("Arial", Font.PLAIN, 18));
                button.addActionListener(eventListener);
                add(button);
            }
        }
    }
}
