import ui.GamePanel;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        // Make JFrame window
        JFrame frame = new JFrame();

        ImageIcon icon = new ImageIcon("D:\\User\\Cahyadi\\Documents\\Project\\Java Project\\AVANLON\\res\\screen\\logo.png");
        frame.setIconImage(icon.getImage());

        frame.setTitle("AVANLON");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Add JPanel to frame
        GamePanel panel = new GamePanel();
        frame.add(panel);

        // The frame adjusts to the size of the component
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
