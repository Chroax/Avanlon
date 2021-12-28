package entity.monster;

import object.drop.BlackScale;
import object.other.Pillar;
import ui.GamePanel;
import ui.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Behemoth extends Monster
{

    public Behemoth(GamePanel gp)
    {
        super(gp);
        setImgHeight(48);
        setImgWidth(48);
        solidArea = new Rectangle(0, 0, 160, 224);
        setSolidAreaDefaultX(solidArea.x);
        setSolidAreaDefaultY(solidArea.y);

        setDefaultValues();
        getBlackArimaImage();
    }

    public void setDefaultValues()
    {
        setName("Behemoth");
        setType("Boss");
        setHP(1000);setMaxHP(1000);
        setMP(50);setMaxMP(50);
        setSTR(20);setMaxSTR(20);
        setINT(13);setMaxINT(13);
        setACC(6);setMaxACC(6);
        setEVD(10);setMaxEVD(10);
        setDEF(10);setMaxDEF(10);
        setRST(1);setMaxRST(1);
        setCRIT(3);setMaxCRIT(3);
        setLvl(5);
        setEXP(200);
        setGold(1000);

        setDirection("down");
        spriteNum = 2;
        spriteCounter = 0;
    }

    public void getBlackArimaImage()
    {
        String txt = "/monster/boss/behemoth/behemoth";
        try
        {
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/boss/behemoth/behemoth 1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/boss/behemoth/behemoth 2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/boss/behemoth/behemoth 3.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update()
    {
        setCollisionOn(false);
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObj(this, false);
        gp.cChecker.checkPlayer(this);

        spriteCounter++;
        stopCounter++;
        if (spriteCounter > 15)
        {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 3;
            else if(spriteNum == 3)
                spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        int screenY = worldY - gp.player.getWorldY() + gp.player.getScreenY();

        // STOP MOVING CAMERA
        if(gp.player.getWorldX() < gp.player.getScreenX())
            screenX = worldX;
        if(gp.player.getWorldY() < gp.player.getScreenY())
            screenY = worldY;

        int rightOffset = gp.screenWidth - gp.player.getScreenX();
        if(rightOffset > gp.getWorldWidth() - gp.player.getWorldX())
            screenX = gp.screenWidth - (gp.getWorldWidth() - worldX);

        int bottomOffset = gp.screenHeight - gp.player.getScreenY();
        if(bottomOffset > gp.getWorldHeight() - gp.player.getWorldY())
            screenY = gp.screenHeight - (gp.getWorldHeight() - worldY);

        switch (getDirection())
        {
            case "down" -> {
                if (spriteNum == 1)
                    image = down1;
                else if (spriteNum == 2)
                    image = down2;
                else if (spriteNum == 3)
                    image = down3;
            }
        }

        if(worldX + gp.tileSize > gp.player.getWorldX() - gp.player.getScreenX() &&
                worldX - gp.tileSize < gp.player.getWorldX() + gp.player.getScreenX() &&
                worldY + gp.tileSize > gp.player.getWorldY() - gp.player.getScreenY() &&
                worldY - gp.tileSize < gp.player.getWorldY() + gp.player.getScreenY())
            g2.drawImage(image, screenX, screenY, image.getWidth() * 2, image.getHeight() * 2, null);

            // If player is around the edge, draw everything
        else if(gp.player.getWorldX() < gp.player.getScreenX() ||
                gp.player.getWorldY() < gp.player.getScreenY() ||
                rightOffset > gp.getWorldWidth() - gp.player.getWorldX() ||
                bottomOffset > gp.getWorldHeight() - gp.player.getWorldY())
            g2.drawImage(image, screenX, screenY, image.getWidth() * 2, image.getHeight() * 2, null);

        g2.setColor(Color.BLACK);
        g2.setFont(UI.pokemon);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14F));
        String text = name;

        if(getLvl() != 0)
            text += "   Lvl-" + getLvl();

        int x = ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() - solidArea.width) / 2;
        g2.drawString(text, screenX - x, solidArea.y + solidArea.height + screenY + 10);
    }
}
