package entity.monster;

import ui.GamePanel;

import java.awt.*;
import java.util.Random;

public class Werewolf extends Monster
{
    public Werewolf(GamePanel gp)
    {
        super(gp);
        imgHeight = 48;
        imgWidth = 48;
        solidArea = new Rectangle(0, 0, 48, 48);
        setDefaultValues();
        getWerewolfImage();
    }

    public void setDefaultValues()
    {
        setName("Werewolf");
        setType("Common");
        setHP(100);
        setMaxHP(100);
        setMP(50);
        setMaxMP(50);
        setSTR(10);
        setMaxSTR(10);
        setINT(3);
        setMaxINT(3);
        setACC(6);
        setMaxACC(6);
        setEVD(10);
        setMaxEVD(10);
        setDEF(10);
        setMaxDEF(10);
        setRST(1);
        setMaxRST(1);
        setCRIT(3);
        setMaxCRIT(3);

        setLvl(1);
        setXp(10);
        setGold(100);

        setSpeed(4);
        setDirection("down");
        spriteNum = 2;
        spriteCounter = 0;
    }

    public void getWerewolfImage()
    {
        String txt = "/monster/48x48/werewolf/werewolf";
        down1 = setup(txt + " 1", 1.5, 1.5);
        down2 = setup(txt + " 2", 1.5, 1.5);
        down3 = setup(txt + " 3", 1.5, 1.5);
        left1 = setup(txt + " 4", 1.5, 1.5);
        left2 = setup(txt + " 5", 1.5, 1.5);
        left3 = setup(txt + " 6", 1.5, 1.5);
        right1 = setup(txt + " 7", 1.5, 1.5);
        right2 = setup(txt + " 8", 1.5, 1.5);
        right3 = setup(txt + " 9", 1.5, 1.5);
        up1 = setup(txt + " 10", 1.5, 1.5);
        up2 = setup(txt + " 11", 1.5, 1.5);
        up3 = setup(txt + " 12", 1.5, 1.5);
    }

    @Override
    public void update()
    {
        setAction();
        setCollisionOn(false);
        gp.cChecker.checkTile(this);

        if(!isCollisionOn())
        {
            switch (getDirection())
            {
                case "up" -> worldY -= getSpeed();
                case "down" -> worldY += getSpeed();
                case "left" -> worldX -= getSpeed();
                case "right" -> worldX += getSpeed();
            }
        }

        spriteCounter++;
        if (spriteCounter > 8)
        {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 1;
            spriteCounter = 0;
        }
    }

    @Override
    public void setAction()
    {
        actionLockCounter++;
        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25)
                setDirection("up");
            else if(i <= 50)
                setDirection("down");
            else if(i <= 75)
                setDirection("left");
            else setDirection("right");

            actionLockCounter = 0;
        }
    }
}
