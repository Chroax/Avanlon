package entity.monster;

import entity.Entity;
import ui.GamePanel;

import java.util.Objects;
import java.util.Random;

public class Monster extends Entity
{
    private String type;

    protected boolean stopMov = false;
    protected int stopCounter = 0;

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

    // Getter & Setter
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
}
