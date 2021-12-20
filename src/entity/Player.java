package entity;

import ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity
{
    private final int screenX;
    private final int screenY;
    private int standCounter = 0;

    public Player(GamePanel gp)
    {
        super(gp);

        imgHeight = 64;
        imgWidth = 64;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 64, 64);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    private void setDefaultValues()
    {
        worldX = 100;
        worldY = 100;
        speed = 4;
        direction = "down";
        spriteNum = 2;
        spriteCounter = 0;
        dialogueIndex = 0;
    }

    private void getPlayerImage()
    {
        String txt = "/character/32x48/archer/archer";
        down1 = setup(txt + " 1", 1.5, true);
        down2 = setup(txt + " 2", 1.5, true);
        down3 = setup(txt + " 3", 1.5, true);
        left1 = setup(txt + " 4", 1.5, true);
        left2 = setup(txt + " 5", 1.5, true);
        left3 = setup(txt + " 6", 1.5, true);
        right1 = setup(txt + " 7", 1.5, true);
        right2 = setup(txt + " 8", 1.5, true);
        right3 = setup(txt + " 9", 1.5, true);
        up1 = setup(txt + " 10", 1.5, true);
        up2 = setup(txt + " 11", 1.5, true);
        up3 = setup(txt + " 12", 1.5, true);
    }

    @Override
    public void update()
    {
        if(gp.keyH.upPressed || gp.keyH.downPressed || gp.keyH.leftPressed || gp.keyH.rightPressed)
        {
            if (gp.keyH.upPressed)
                direction = "up";
            else if (gp.keyH.downPressed)
                direction = "down";
            else if (gp.keyH.leftPressed)
                direction = "left";
            else direction = "right";

            switch (direction)
            {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }

            spriteCounter++;
            if (spriteCounter > 8)
            {
                if (spriteNum == 1)
                    spriteNum = 3;
                else if (spriteNum == 3  || spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
        else
        {
            standCounter++;
            if(standCounter == 8)
            {
                spriteNum = 2;
                standCounter = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

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

        g2.drawImage(image, worldX, worldY, null);
    }
}
