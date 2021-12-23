package entity.npc;

import entity.Entity;
import ui.GamePanel;
import ui.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Zillah extends Entity
{
    public Zillah(GamePanel gp)
    {
        super(gp);

        setName("Zillah");
        setDirection("down");
        setImgWidth(32);
        setImgHeight(48);
        dialogues = new String[2][4];
        solidArea = new Rectangle(10, 32, 32, 32);
        nextDialogue = 0;
        prevDialogue = 0;
        setSolidAreaDefaultX(solidArea.x);
        setSolidAreaDefaultY(solidArea.y);

        getZillahImage();
        setDialogue();
    }

    public void getZillahImage()
    {
        String txt = "/npc/32x48/zillah/zillah";
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
    public void setAction()
    {}

    public void setDialogue()
    {
        dialogues[0][0] = "Hello,  adventure";
        dialogues[0][1] = "Im  a  merchant";
        dialogues[0][2] = "What  is  a  merchant  ?";
        dialogues[0][3] = "Merchant  is  a  place  where  adventurers\nmake  transactions  to  buy  and  sell  items";
    }

    public void speak()
    {
        super.speak();
    }

    @Override
    public void draw(Graphics2D g2)
    {
        BufferedImage image = down2;
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

        if(worldX + gp.tileSize > gp.player.getWorldX() - gp.player.getScreenX() &&
                worldX - gp.tileSize < gp.player.getWorldX() + gp.player.getScreenX() &&
                worldY + gp.tileSize > gp.player.getWorldY() - gp.player.getScreenY() &&
                worldY - gp.tileSize < gp.player.getWorldY() + gp.player.getScreenY())
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            // If player is around the edge, draw everything
        else if(gp.player.getWorldX() < gp.player.getScreenX() ||
                gp.player.getWorldY() < gp.player.getScreenY() ||
                rightOffset > gp.getWorldWidth() - gp.player.getWorldX() ||
                bottomOffset > gp.getWorldHeight() - gp.player.getWorldY())
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        g2.setColor(Color.BLACK);
        g2.setFont(UI.pokemon);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14F));
        String text = name;

        g2.drawString(text, screenX + 6, solidArea.y + solidArea.height + screenY + 7);
    }
}
