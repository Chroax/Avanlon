package entity.monster;

import entity.Entity;
import object.SuperObject;
import ui.GamePanel;

import java.util.Objects;
import java.util.Random;

public class Monster extends Entity
{
    private String type;

    protected boolean stopMov = false;
    protected int stopCounter = 0;
    private SuperObject dropItem;

    public Monster(GamePanel gp) { super(gp); }

    public void generateMonster()
    {
        if(Objects.equals(this.type, "Boss"))
            return;

        resetStat();
        int col = 0, row = 0;
        boolean spawn = false;
        while (!spawn)
        {
            Random random = new Random();
            col = random.nextInt(gp.getMaxWorldCol());
            row = random.nextInt(gp.getMaxWorldRow());
            int tileNum = gp.map[gp.getMapPick()].mapTileNum[col][row];
            if(!gp.map[gp.getMapPick()].tile[tileNum].isCollision())
                spawn = true;
        }

        worldX = col * gp.tileSize;
        worldY = row * gp.tileSize;
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

    // Getter & Setter
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public SuperObject getDropItem() {return dropItem;}
    public void setDropItem(SuperObject dropItem) {this.dropItem = dropItem;}
}
