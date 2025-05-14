package part1.app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

        ActionListener eventListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command == "C") {
                    display.setExpression("");
                } else if (command == "b") {
                    String temp = display.getExpression();
                    if (temp.length() > 0) {
                        temp = temp.substring(0, temp.length() - 2);
                        display.setExpression(temp);
                    }
                }
            };
        };

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                JButton button = new JButton(buttons[row][col]);
                button.addActionListener(eventListener);
            }
        }
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
