package util;

import entity.monster.Werewolf;
import entity.npc.MerchantNPC;
import object.drop.Stair;
import object.weapon.RustySword;
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

    public void setObj()
    {
        gp.map[gp.village].obj[0] = new Stair(gp);
        gp.map[gp.village].obj[0].setWorldX(gp.tileSize * 29);
        gp.map[gp.village].obj[0].setWorldY(gp.tileSize * 5);

        gp.map[gp.village].obj[1] = new Stair(gp);
        gp.map[gp.village].obj[1].setWorldX(gp.tileSize * 30);
        gp.map[gp.village].obj[1].setWorldY(gp.tileSize * 5);

        gp.map[gp.village].obj[2] = new RustySword(gp);
        gp.map[gp.village].obj[2].setWorldX(gp.tileSize * 10);
        gp.map[gp.village].obj[2].setWorldY(gp.tileSize * 34);

        gp.map[gp.village].obj[3] = new RustySword(gp);
        gp.map[gp.village].obj[3].setWorldX(gp.tileSize * 13);
        gp.map[gp.village].obj[3].setWorldY(gp.tileSize * 34);
    }

    public void setNPC()
    {
        gp.map[gp.village].NPC[0] = new MerchantNPC(gp);
        gp.map[gp.village].NPC[0].setWorldX(gp.tileSize * 11);
        gp.map[gp.village].NPC[0].setWorldY(gp.tileSize * 32);
    }
}
