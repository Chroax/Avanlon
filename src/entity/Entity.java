package entity;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Entity
{
    private String name;

    protected int worldX;
    protected int worldY;
    private int speed;

    // Sprites Image
    protected BufferedImage up1, up2, up3;
    protected BufferedImage down1, down2, down3;
    protected BufferedImage left1, left2, left3;
    protected BufferedImage right1, right2, right3;
    private String direction;
    public int spriteCounter;
    public int spriteNum;
    protected int standCounter = 0;
    protected int actionLockCounter = 0;

    protected int imgWidth;
    protected int imgHeight;

    // Dialog Chat
    protected String[][] dialogues;
    protected int dialogueIndex;

    // Collision
    private boolean collisionOn;
    public Rectangle solidArea;
    private int solidAreaDefaultX;
    private int solidAreaDefaultY;

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

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.getScreenX();
        int screenY = worldY - gp.player.worldY + gp.player.getScreenY();

        // STOP MOVING CAMERA
        if(gp.player.worldX < gp.player.getScreenX())
            screenX = worldX;
        if(gp.player.worldY < gp.player.getScreenY())
            screenY = worldY;

        int rightOffset = gp.screenWidth - gp.player.getScreenX();
        if(rightOffset > gp.getWorldWidth() - gp.player.worldX)
            screenX = gp.screenWidth - (gp.getWorldWidth() - worldX);

        int bottomOffset = gp.screenHeight - gp.player.getScreenY();
        if(bottomOffset > gp.getWorldHeight() - gp.player.worldY)
            screenY = gp.screenHeight - (gp.getWorldHeight() - worldY);

        switch (direction)
        {
            case "up" -> {
                if (spriteNum == 1)
                    image = up1;
                else if (spriteNum == 2)
                    image = up2;
                else if (spriteNum == 3)
                    image = up3;
            }
            case "down" -> {
                if (spriteNum == 1)
                    image = down1;
                else if (spriteNum == 2)
                    image = down2;
                else if (spriteNum == 3)
                    image = down3;
            }
            case "left" -> {
                if (spriteNum == 1)
                    image = left1;
                else if (spriteNum == 2)
                    image = left2;
                else if (spriteNum == 3)
                    image = left3;
            }
            case "right" -> {
                if (spriteNum == 1)
                    image = right1;
                else if (spriteNum == 2)
                    image = right2;
                else if (spriteNum == 3)
                    image = right3;
            }
        }

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.getScreenX() &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.getScreenX() &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.getScreenY() &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.getScreenY())
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            // If player is around the edge, draw everything
        else if(gp.player.worldX < gp.player.getScreenX() ||
                gp.player.worldY < gp.player.getScreenY() ||
                rightOffset > gp.getWorldWidth() - gp.player.worldX ||
                bottomOffset > gp.getWorldHeight() - gp.player.worldY)
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        g2.setColor(Color.red);
        g2.drawRect(solidArea.x + screenX, solidArea.y + screenY, solidArea.width, solidArea.height);
    }

    // Getter & Setter
    public int getWorldX() { return worldX; }
    public void setWorldX(int worldX) { this.worldX = worldX; }
    public int getWorldY() { return worldY;}
    public void setWorldY(int worldY) { this.worldY = worldY; }
    public boolean isCollisionOn() { return collisionOn; }
    public void setCollisionOn(boolean collisionOn) { this.collisionOn = collisionOn; }
    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getSolidAreaDefaultX() {return solidAreaDefaultX;}
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {this.solidAreaDefaultX = solidAreaDefaultX;}
    public int getSolidAreaDefaultY() {return solidAreaDefaultY;}
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {this.solidAreaDefaultY = solidAreaDefaultY;}
}
