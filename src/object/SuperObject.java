package object;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class SuperObject
{
    private String name;
    private String type;
    private String description;
    private int sellPrice;
    private int buyPrice;
    private boolean sellAble;
    
    public BufferedImage image;
    public GamePanel gp;

    private boolean collision = false;
    private int worldX;
    private int worldY;

    public Rectangle solidArea;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    
    public SuperObject(GamePanel gp) {this.gp = gp;}
    
    public void draw(Graphics2D g2)
    {
        int screenX = getWorldX() - gp.player.getWorldX() + gp.player.getScreenX();
        int screenY = getWorldY() - gp.player.getWorldY() + gp.player.getScreenY();

        if(gp.player.getWorldX() < gp.player.getScreenX())
            screenX = getWorldX();

        if(gp.player.getWorldY() < gp.player.getScreenY())
            screenY = getWorldY();

        int rightOffset = gp.screenWidth - gp.player.getScreenX();
        if(rightOffset > gp.getWorldWidth() - gp.player.getWorldX())
            screenX = gp.screenWidth - (gp.getWorldWidth() - getWorldX());

        int bottomOffset = gp.screenHeight - gp.player.getScreenY();
        if(bottomOffset > gp.getWorldHeight() - gp.player.getWorldY())
            screenY = gp.screenHeight - (gp.getWorldHeight() - getWorldY());

        if(getWorldX() + gp.tileSize > gp.player.getWorldX() - gp.player.getScreenX() &&
                getWorldX() - gp.tileSize < gp.player.getWorldX() + gp.player.getScreenX() &&
                getWorldY() + gp.tileSize > gp.player.getWorldY() - gp.player.getScreenY() &&
                getWorldY() - gp.tileSize < gp.player.getWorldY() + gp.player.getScreenY())
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // If player is around the edge, draw everything
        else if(gp.player.getWorldX() < gp.player.getScreenX() ||
                gp.player.getWorldY() < gp.player.getScreenY() ||
                rightOffset > gp.getWorldWidth() - gp.player.getWorldX() ||
                bottomOffset > gp.getWorldHeight() - gp.player.getWorldY())
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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

    public boolean equals(SuperObject obj)
    {
        return Objects.equals(this.name, obj.getName());
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getSellPrice() {return sellPrice;}
    public void setSellPrice(int sellPrice) {this.sellPrice = sellPrice;}
    public int getBuyPrice() {return buyPrice;}
    public void setBuyPrice(int buyPrice) {this.buyPrice = buyPrice;}
    public boolean isSellAble() {return sellAble;}
    public void setSellAble(boolean sellAble) {this.sellAble = sellAble;}
    public boolean isCollision() {return collision;}
    public void setCollision(boolean collision) {this.collision = collision;}
    public int getWorldX() {return worldX;}
    public void setWorldX(int worldX) {this.worldX = worldX;}
    public int getWorldY() {return worldY;}
    public void setWorldY(int worldY) {this.worldY = worldY;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
}
