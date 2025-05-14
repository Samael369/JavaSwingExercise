package part1.app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                gbc.gridx = row;
                gbc.gridy = col;
                add(new JButton(buttons[row][col]), gbc);
            }
        }
    }
}
