package part1.app;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

    public ButtonPanel(DisplayPanel display) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

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
                    Calc calc = new Calc(expression);
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
                gbc.gridx = col;
                gbc.gridy = row;
                button.addActionListener(eventListener);
                add(button, gbc);
            }
        }
    }
}
