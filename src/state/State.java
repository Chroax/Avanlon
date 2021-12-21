package state;

import ui.GamePanel;
import ui.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class State
{
    protected String text;
    GamePanel gp;
    Graphics2D g2;
    BufferedImage[] imageScreen;
    protected String[] optionMenu;
    public int commandNum = 0;

    public State(GamePanel gp) {this.gp = gp;}

    public void draw(Graphics2D g2){}

    public int getXCenteredText(String text)
    {
        return ((gp.screenWidth / 2) - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2));
    }

    public BufferedImage setup(String imagePath)
    {
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            scaledImage = gp.uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public BufferedImage setup(String imagePath, int scaleWidth, int scaleHeight)
    {
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            scaledImage = gp.uTool.scaleImage(scaledImage, scaledImage.getWidth(), scaledImage.getWidth(), scaleWidth, scaleHeight);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public BufferedImage setup(String imagePath, double scale, boolean width)
    {
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            if(width)
                scaledImage = gp.uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize, (int) (scaledImage.getWidth() * scale), gp.tileSize);
            else
                scaledImage = gp.uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize, gp.tileSize, (int) (scaledImage.getHeight() * scale));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }
}
