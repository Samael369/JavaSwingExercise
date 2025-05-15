package part2.app;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class ShowPanel extends JPanel {
    private Image image;

    public ShowPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        loadImage();
    }

    public void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("./images/img.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void scaleImage(int width, int height) {
        if (image != null) {
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            image = scaledImage;
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int x = (this.getWidth() - image.getWidth(null)) / 2;
            int y = (this.getHeight() - image.getHeight(null)) / 2;
            g.drawImage(image, x, y, this);
        }
    }

}