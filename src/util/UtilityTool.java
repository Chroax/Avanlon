package util;

import ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool
{
    GamePanel gp;
    public UtilityTool (GamePanel gp){ this.gp = gp; }

    public BufferedImage scaleImage(BufferedImage original, int width, int height)
    {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.dispose();

        return scaledImage;
    }

    public BufferedImage scaleImage(BufferedImage original, int oriWidth, int oriHeight, int width, int height)
    {
        BufferedImage scaledImage = new BufferedImage(oriWidth, oriHeight, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
