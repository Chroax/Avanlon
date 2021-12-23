package entity.monster;

import ui.GamePanel;

import java.awt.*;
import java.util.Random;

public class OrcArima extends Monster
{
    public OrcArima(GamePanel gp)
    {
        super(gp);
        setImgHeight(48);
        setImgWidth(48);
        solidArea = new Rectangle(0, 0, 60, 60);
        setSolidAreaDefaultX(solidArea.x);
        setSolidAreaDefaultY(solidArea.y);

        setDefaultValues();
        getOrcArimaImage();
    }

    public void setDefaultValues()
    {
        setName("Orc Arima");
        setType("Common");
        setHP(100);setMaxHP(100);
        setMP(50);setMaxMP(50);
        setSTR(10);setMaxSTR(10);
        setINT(3);setMaxINT(3);
        setACC(6);setMaxACC(6);
        setEVD(10);setMaxEVD(10);
        setDEF(10);setMaxDEF(10);
        setRST(1);setMaxRST(1);
        setCRIT(3);setMaxCRIT(3);

        setLvl(1);
        setEXP(10);
        setGold(100);

        setSpeed(3);
        setDirection("down");
        spriteNum = 2;
        spriteCounter = 0;
    }

    public void getOrcArimaImage()
    {
        String txt = "/monster/48x48/orcarima/orc_arima";
        down1 = setup(txt + " 1");
        down2 = setup(txt + " 2");
        down3 = setup(txt + " 3");
        left1 = setup(txt + " 4");
        left2 = setup(txt + " 5");
        left3 = setup(txt + " 6");
        right1 = setup(txt + " 7");
        right2 = setup(txt + " 8");
        right3 = setup(txt + " 9");
        up1 = setup(txt + " 10");
        up2 = setup(txt + " 11");
        up3 = setup(txt + " 12");
    }

    @Override
    public void update()
    {
        if(!stopMov)
        {
            setAction();

            setCollisionOn(false);
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObj(this, false);
            gp.cChecker.checkPlayer(this);

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
            stopCounter++;
            if (spriteCounter > 15)
            {
                if (spriteNum == 1)
                    spriteNum = 3;
                else if (spriteNum == 3 || spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
            if(stopCounter == 120)
            {
                stopMov = true;
                stopCounter = 0;
            }
        }
        else
        {
            standCounter++;
            stopCounter++;
            if(standCounter == 8)
            {
                spriteNum = 2;
                standCounter = 0;
            }
            if(stopCounter == 60)
            {
                stopMov = false;
                stopCounter = 0;
            }
        }
    }

    @Override
    public void setAction()
    {
        actionLockCounter++;
        if(actionLockCounter == 80)
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
