package part1.app;

import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
    private final String[] buttons = {
            "1", "2", "3", "/",
            "4", "5", "6", "*",
            "7", "8", "9", "-",
            ".", "0", "=", "+",
            "C", "(", ")", "b"
    };

    public ButtonPanel(DisplayPanel display) {

    }
}
