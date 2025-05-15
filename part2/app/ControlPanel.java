package part2.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private JTextField widthField, heightField;
    private JButton scaleButton;

    public ControlPanel(ShowPanel showPanel) {
        setLayout(new FlowLayout());
        widthField = new JTextField(5);
        heightField = new JTextField(5);
        scaleButton = new JButton("Scale Image");

        add(new JLabel("Width:"));
        add(widthField);
        add(new JLabel("Height:"));
        add(heightField);
        add(scaleButton);

        scaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int width = Integer.parseInt(widthField.getText());
                    int height = Integer.parseInt(heightField.getText());

                    int maxWidth = showPanel.getWidth();
                    int maxHeight = showPanel.getHeight();

                    if (width > maxWidth || height > maxHeight) {
                        JOptionPane.showMessageDialog(null, "Entered size cannot be larger than the panel size!");
                    } else {
                        showPanel.loadImage();
                        showPanel.scaleImage(width, height);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for width and height");
                }
            }
        });
    }
}