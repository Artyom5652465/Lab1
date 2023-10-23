import javax.swing.*;
import java.awt.*;
import java.awt.color.*;

 class ColorConverter extends JFrame {

    private JColorChooser colorChooser;

    private JLabel rgbLabel;
    private JLabel cmykLabel;
    private JLabel hsvLabel;

    public ColorConverter() {
        super("Color Model Converter");

        colorChooser = new JColorChooser();
        add(colorChooser, BorderLayout.CENTER);

        JPanel labelsPanel = new JPanel();
        rgbLabel = new JLabel("RGB: ");
        cmykLabel = new JLabel("CMYK: ");
        hsvLabel = new JLabel("HSV: ");
        labelsPanel.add(rgbLabel);
        labelsPanel.add(cmykLabel);
        labelsPanel.add(hsvLabel);
        add(labelsPanel, BorderLayout.SOUTH);

        colorChooser.getSelectionModel().addChangeListener(e -> updateColors());

        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void updateColors() {
        Color color = colorChooser.getColor();

        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        rgbLabel.setText("RGB: " + r + ", " + g + ", " + b);

        float rf = (float)r / 255;
        float gf = (float)g / 255;
        float bf = (float)b / 255;
        float k = 1 - Math.max(Math.max(rf, gf), bf);
        cmykLabel.setText("CMYK: " + (int)((1-rf-k)/(1-k)*255) + ", " + (int)((1-gf-k)/(1-k)*255) + ", " + (int)((1-bf-k)/(1-k)*255) + ", " + (int)(k*255));

        float[] hsv = Color.RGBtoHSB(r, g, b, null);
        hsvLabel.setText("HSV: " + (int)(hsv[0]*360) + ", " + (int)(hsv[1]*100) + ", " + (int)(hsv[2]*100));
    }

    public static void main(String[] args) {
        new ColorConverter();
    }
}