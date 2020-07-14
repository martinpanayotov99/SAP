package screens;

import javax.swing.*;
import java.awt.*;

public class Frame {
    private JLabel imgLabel;
    private ImageIcon imageIcon;
    private JFrame frame;
    private Dimension screen;
    private JPanel panel;
    protected String username;

    public Frame() {
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        panel = new JPanel();
        imageIcon = new ImageIcon("D:\\ProjectSAP\\img\\logo.jpg");
        imgLabel = new JLabel(imageIcon);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void createFrame() {
        int width = (int) screen.getWidth();
        int height = (int) screen.getHeight();

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        panel.setBackground(Color.white);
        frame.add(panel);
        panel.setLayout(null);

        imgLabel.setBounds(500, 150, 500, 500);
        getPanel().add(imgLabel);
        frame.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }
}
