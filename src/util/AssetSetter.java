package util;

import entity.monster.Werewolf;
import ui.GamePanel;

public class AssetSetter
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setMonster()
    {
        gp.map[gp.plain].monsters[0] = new Werewolf(gp);
        gp.map[gp.plain].monsters[0].setWorldX(gp.tileSize * 20);
        gp.map[gp.plain].monsters[0].setWorldY(gp.tileSize * 20);
    }
}
