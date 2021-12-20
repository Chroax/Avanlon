package entity;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Entity
{
    protected int worldX;
    protected int worldY;
    protected int speed;

    // Sprites Image
    protected BufferedImage up1, up2, up3;
    protected BufferedImage down1, down2, down3;
    protected BufferedImage left1, left2, left3;
    protected BufferedImage right1, right2, right3;
    protected String direction;
    public int spriteCounter;
    public int spriteNum;
    protected int imgWidth;
    protected int imgHeight;

    // Dialog Chat
    protected String[][] dialogues;
    protected int dialogueIndex;

    // Collision
    protected boolean collisionOn;
    public Rectangle solidArea;
    protected int solidAreaDefaultX;
    protected int solidAreaDefaultY;

    public GamePanel gp;

    public Entity(GamePanel gp) { this.gp = gp; }

    public void update() {}

    public void setAction(){}

    public void speak(){}

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

    public BufferedImage setup(String imagePath, double scaleWidth, double scaleHeight)
    {
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            scaledImage = gp.uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize, (int) (scaledImage.getWidth() * scaleWidth), (int) (scaledImage.getHeight() * scaleHeight));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void draw(Graphics2D g2){}
}
