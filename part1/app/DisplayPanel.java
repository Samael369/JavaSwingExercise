package part1.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayPanel extends JPanel {
    public JTextField textField = new JTextField();

    public DisplayPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(280, 70));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        textField.setFont(new Font("Arial", Font.PLAIN, 32));
        textField.setHorizontalAlignment(JTextField.LEFT);
    }

    public String getExpression() {
        return textField.getText();
    }

    public void setExpression(String expression) {
        textField.setText(expression);
    }
}
