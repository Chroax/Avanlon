package util;

import entity.monster.*;
import entity.npc.*;
import object.armor.LustyArmor;
import object.other.Pillar;
import object.other.Portal;
import object.other.Stair;
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
        //plain map
        gp.map[gp.plain].monsters[0] = new GoblinArcher(gp);
        gp.map[gp.plain].monsters[0].setWorldX(gp.tileSize * 20);
        gp.map[gp.plain].monsters[0].setWorldY(gp.tileSize * 20);

        gp.map[gp.plain].monsters[1] = new GoblinSpear(gp);
        gp.map[gp.plain].monsters[1].setWorldX(gp.tileSize * 35);
        gp.map[gp.plain].monsters[1].setWorldY(gp.tileSize * 32);

        gp.map[gp.plain].monsters[2] = new GoblinKing(gp);
        gp.map[gp.plain].monsters[2].setWorldX(gp.tileSize * 10);
        gp.map[gp.plain].monsters[2].setWorldY(gp.tileSize * 18);

        gp.map[gp.plain].monsters[3] = new GoblinWarrior(gp);
        gp.map[gp.plain].monsters[3].setWorldX(gp.tileSize * 20);
        gp.map[gp.plain].monsters[3].setWorldY(gp.tileSize * 10);

        gp.map[gp.plain].monsters[4] = new OrcArima(gp);
        gp.map[gp.plain].monsters[4].setWorldX(gp.tileSize * 24);
        gp.map[gp.plain].monsters[4].setWorldY(gp.tileSize * 40);

        gp.map[gp.plain].monsters[5] = new BlackArima(gp);
        gp.map[gp.plain].monsters[5].setWorldX(gp.tileSize * 22);
        gp.map[gp.plain].monsters[5].setWorldY(gp.tileSize * 37);

        //dungeon map
        gp.map[gp.dungeon].monsters[0] = new BlackArima(gp);
        gp.map[gp.dungeon].monsters[0].setWorldX(gp.tileSize * 10);
        gp.map[gp.dungeon].monsters[0].setWorldY(gp.tileSize * 15);

        gp.map[gp.dungeon].monsters[1] = new DevilArima(gp);
        gp.map[gp.dungeon].monsters[1].setWorldX(gp.tileSize * 15);
        gp.map[gp.dungeon].monsters[1].setWorldY(gp.tileSize * 10);

        gp.map[gp.dungeon].monsters[2] = new HornArima(gp);
        gp.map[gp.dungeon].monsters[2].setWorldX(gp.tileSize * 20);
        gp.map[gp.dungeon].monsters[2].setWorldY(gp.tileSize * 8);

        gp.map[gp.dungeon].monsters[3] = new EarthGolem(gp);
        gp.map[gp.dungeon].monsters[3].setWorldX(gp.tileSize * 15);
        gp.map[gp.dungeon].monsters[3].setWorldY(gp.tileSize * 27);

        gp.map[gp.dungeon].monsters[4] = new BlackArima(gp);
        gp.map[gp.dungeon].monsters[4].setWorldX(gp.tileSize * 13);
        gp.map[gp.dungeon].monsters[4].setWorldY(gp.tileSize * 15);

        gp.map[gp.dungeon].monsters[5] = new DevilArima(gp);
        gp.map[gp.dungeon].monsters[5].setWorldX(gp.tileSize * 17);
        gp.map[gp.dungeon].monsters[5].setWorldY(gp.tileSize * 10);

        gp.map[gp.dungeon].monsters[6] = new HornArima(gp);
        gp.map[gp.dungeon].monsters[6].setWorldX(gp.tileSize * 10);
        gp.map[gp.dungeon].monsters[6].setWorldY(gp.tileSize * 8);

        gp.map[gp.dungeon].monsters[7] = new ShadowKing(gp);
        gp.map[gp.dungeon].monsters[7].setWorldX(gp.tileSize * 35);
        gp.map[gp.dungeon].monsters[7].setWorldY(gp.tileSize * 18);

        gp.map[gp.dungeon].monsters[8] = new ShadowQueen(gp);
        gp.map[gp.dungeon].monsters[8].setWorldX(gp.tileSize * 37);
        gp.map[gp.dungeon].monsters[8].setWorldY(gp.tileSize * 18);

        //castle map
        gp.map[gp.castle].monsters[0] = new Skeleton(gp);
        gp.map[gp.castle].monsters[0].setWorldX(gp.tileSize * 15);
        gp.map[gp.castle].monsters[0].setWorldY(gp.tileSize * 15);

        gp.map[gp.castle].monsters[1] = new Zomb(gp);
        gp.map[gp.castle].monsters[1].setWorldX(gp.tileSize * 25);
        gp.map[gp.castle].monsters[1].setWorldY(gp.tileSize * 22);

        gp.map[gp.castle].monsters[2] = new ZombKing(gp);
        gp.map[gp.castle].monsters[2].setWorldX(gp.tileSize * 35);
        gp.map[gp.castle].monsters[2].setWorldY(gp.tileSize * 28);

        gp.map[gp.castle].monsters[3] = new ZombMaid(gp);
        gp.map[gp.castle].monsters[3].setWorldX(gp.tileSize * 25);
        gp.map[gp.castle].monsters[3].setWorldY(gp.tileSize * 30);

        gp.map[gp.castle].monsters[4] = new PurpleArima(gp);
        gp.map[gp.castle].monsters[4].setWorldX(gp.tileSize * 35);
        gp.map[gp.castle].monsters[4].setWorldY(gp.tileSize * 32);

        gp.map[gp.castle].monsters[5] = new DarkMagician(gp);
        gp.map[gp.castle].monsters[5].setWorldX(gp.tileSize * 17);
        gp.map[gp.castle].monsters[5].setWorldY(gp.tileSize * 33);

        //snow map
        gp.map[gp.snow].monsters[0] = new IceWerewolf(gp);
        gp.map[gp.snow].monsters[0].setWorldX(gp.tileSize * 10);
        gp.map[gp.snow].monsters[0].setWorldY(gp.tileSize * 14);

        gp.map[gp.snow].monsters[1] = new IceWerewolf(gp);
        gp.map[gp.snow].monsters[1].setWorldX(gp.tileSize * 30);
        gp.map[gp.snow].monsters[1].setWorldY(gp.tileSize * 14);

        gp.map[gp.snow].monsters[2] = new GhostIce(gp);
        gp.map[gp.snow].monsters[2].setWorldX(gp.tileSize * 20);
        gp.map[gp.snow].monsters[2].setWorldY(gp.tileSize * 20);

        gp.map[gp.snow].monsters[3] = new GhostIce(gp);
        gp.map[gp.snow].monsters[3].setWorldX(gp.tileSize * 8);
        gp.map[gp.snow].monsters[3].setWorldY(gp.tileSize * 25);

        gp.map[gp.snow].monsters[4] = new Necromancer(gp);
        gp.map[gp.snow].monsters[4].setWorldX(gp.tileSize * 20);
        gp.map[gp.snow].monsters[4].setWorldY(gp.tileSize * 15);

        gp.map[gp.snow].monsters[5] = new SnowGolem(gp);
        gp.map[gp.snow].monsters[5].setWorldX(gp.tileSize * 30);
        gp.map[gp.snow].monsters[5].setWorldY(gp.tileSize * 20);
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

        gp.map[gp.village].obj[3] = new LustyArmor(gp);
        gp.map[gp.village].obj[3].setWorldX(gp.tileSize * 13);
        gp.map[gp.village].obj[3].setWorldY(gp.tileSize * 34);

        gp.map[gp.plain].obj[1] = new Pillar(gp);
        gp.map[gp.plain].obj[1].setWorldX(gp.tileSize * 10);
        gp.map[gp.plain].obj[1].setWorldY(gp.tileSize * 10);
    }

    public void setPortal()
    {
        if(gp.unlockMap[gp.plain])
        {
            gp.map[gp.plain].obj[0] = new Portal(gp);
            gp.map[gp.plain].obj[0].setWorldX(gp.tileSize * 20);
            gp.map[gp.plain].obj[0].setWorldY(gp.tileSize * 20);
        }
        if(gp.unlockMap[gp.dungeon])
        {
            gp.map[gp.dungeon].obj[0] = new Portal(gp);
            gp.map[gp.dungeon].obj[0].setWorldX(gp.tileSize * 20);
            gp.map[gp.dungeon].obj[0].setWorldY(gp.tileSize * 20);
        }
        if(gp.unlockMap[gp.castle])
        {
            gp.map[gp.castle].obj[0] = new Portal(gp);
            gp.map[gp.castle].obj[0].setWorldX(gp.tileSize * 20);
            gp.map[gp.castle].obj[0].setWorldY(gp.tileSize * 20);
        }
        if(gp.unlockMap[gp.snow])
        {
            gp.map[gp.snow].obj[0] = new Portal(gp);
            gp.map[gp.snow].obj[0].setWorldX(gp.tileSize * 20);
            gp.map[gp.snow].obj[0].setWorldY(gp.tileSize * 20);
        }
    }

    public void setBoss(int index)
    {
        gp.playSE(17);
        if(index == gp.plain)
        {
            gp.map[gp.plain].monsters[6] = new Behemoth(gp);
            gp.map[gp.plain].monsters[6].setWorldX((int)(gp.tileSize * 22.5));
            gp.map[gp.plain].monsters[6].setWorldY(gp.tileSize * 18);
        }
        else if(index == gp.dungeon);
        else if(index == gp.castle);
        else if(index == gp.snow);
    }

    public void setNPC()
    {
        //village map
        gp.map[gp.village].NPC[0] = new MerchantNPC(gp);
        gp.map[gp.village].NPC[0].setWorldX(gp.tileSize * 11);
        gp.map[gp.village].NPC[0].setWorldY(gp.tileSize * 32);

        gp.map[gp.village].NPC[1] = new ChiefVillage(gp);
        gp.map[gp.village].NPC[1].setWorldX(gp.tileSize * 46);
        gp.map[gp.village].NPC[1].setWorldY(gp.tileSize * 22);

        gp.map[gp.village].NPC[2] = new Alex(gp);
        gp.map[gp.village].NPC[2].setWorldX(gp.tileSize * 20);
        gp.map[gp.village].NPC[2].setWorldY(gp.tileSize * 7);

        gp.map[gp.village].NPC[3] = new Abigail(gp);
        gp.map[gp.village].NPC[3].setWorldX(gp.tileSize * 26);
        gp.map[gp.village].NPC[3].setWorldY(gp.tileSize * 22);

        gp.map[gp.village].NPC[4] = new Aerith(gp);
        gp.map[gp.village].NPC[4].setWorldX(gp.tileSize * 27);
        gp.map[gp.village].NPC[4].setWorldY(gp.tileSize * 22);

        gp.map[gp.village].NPC[5] = new Captain(gp);
        gp.map[gp.village].NPC[5].setWorldX(gp.tileSize * 12);
        gp.map[gp.village].NPC[5].setWorldY(gp.tileSize * 13);

        gp.map[gp.village].NPC[6] = new Carlo(gp);
        gp.map[gp.village].NPC[6].setWorldX(gp.tileSize * 40);
        gp.map[gp.village].NPC[6].setWorldY(gp.tileSize * 6);

        //plain map
        gp.map[gp.plain].NPC[0] = new PostMan(gp);
        gp.map[gp.plain].NPC[0].setWorldX(gp.tileSize * 17);
        gp.map[gp.plain].NPC[0].setWorldY(gp.tileSize * 20);

        gp.map[gp.plain].NPC[1] = new Milton(gp);
        gp.map[gp.plain].NPC[1].setWorldX(gp.tileSize * 23);
        gp.map[gp.plain].NPC[1].setWorldY(gp.tileSize * 22);

        gp.map[gp.plain].NPC[2] = new Noble(gp);
        gp.map[gp.plain].NPC[2].setWorldX(gp.tileSize * 30);
        gp.map[gp.plain].NPC[2].setWorldY(gp.tileSize * 22);

        gp.map[gp.plain].NPC[3] = new OldMan(gp);
        gp.map[gp.plain].NPC[3].setWorldX(gp.tileSize * 26);
        gp.map[gp.plain].NPC[3].setWorldY(gp.tileSize * 11);

        //dungeon map
        gp.map[gp.dungeon].NPC[0] = new Lisa(gp);
        gp.map[gp.dungeon].NPC[0].setWorldX(gp.tileSize * 50);
        gp.map[gp.dungeon].NPC[0].setWorldY(gp.tileSize * 10);

        gp.map[gp.dungeon].NPC[1] = new Helen(gp);
        gp.map[gp.dungeon].NPC[1].setWorldX(gp.tileSize * 35);
        gp.map[gp.dungeon].NPC[1].setWorldY(gp.tileSize * 14);

        gp.map[gp.dungeon].NPC[2] = new Hannah(gp);
        gp.map[gp.dungeon].NPC[2].setWorldX(gp.tileSize * 36);
        gp.map[gp.dungeon].NPC[2].setWorldY(gp.tileSize * 14);

        gp.map[gp.dungeon].NPC[3] = new FemaleScholar(gp);
        gp.map[gp.dungeon].NPC[3].setWorldX(gp.tileSize * 50);
        gp.map[gp.dungeon].NPC[3].setWorldY(gp.tileSize * 33);

        gp.map[gp.dungeon].NPC[4] = new Jenna(gp);
        gp.map[gp.dungeon].NPC[4].setWorldX(gp.tileSize * 30);
        gp.map[gp.dungeon].NPC[4].setWorldY(gp.tileSize * 15);

        gp.map[gp.dungeon].NPC[5] = new OldWoman(gp);
        gp.map[gp.dungeon].NPC[5].setWorldX(gp.tileSize * 10);
        gp.map[gp.dungeon].NPC[5].setWorldY(gp.tileSize * 8);

        //castle map
        gp.map[gp.castle].NPC[0] = new King(gp);
        gp.map[gp.castle].NPC[0].setWorldX(gp.tileSize * 10);
        gp.map[gp.castle].NPC[0].setWorldY(gp.tileSize * 13);

        gp.map[gp.castle].NPC[1] = new Mauro(gp);
        gp.map[gp.castle].NPC[1].setWorldX(gp.tileSize * 66);
        gp.map[gp.castle].NPC[1].setWorldY(gp.tileSize * 5);

        gp.map[gp.castle].NPC[2] = new Mercenary(gp);
        gp.map[gp.castle].NPC[2].setWorldX(gp.tileSize * 60);
        gp.map[gp.castle].NPC[2].setWorldY(gp.tileSize * 10);

        gp.map[gp.castle].NPC[3] = new Queen(gp);
        gp.map[gp.castle].NPC[3].setWorldX(gp.tileSize * 13);
        gp.map[gp.castle].NPC[3].setWorldY(gp.tileSize * 13);

        gp.map[gp.castle].NPC[4] = new PrimeMinister(gp);
        gp.map[gp.castle].NPC[4].setWorldX(gp.tileSize * 28);
        gp.map[gp.castle].NPC[4].setWorldY(gp.tileSize * 9);

        //snow map
        gp.map[gp.snow].NPC[0] = new Ralph(gp);
        gp.map[gp.snow].NPC[0].setWorldX(gp.tileSize * 3);
        gp.map[gp.snow].NPC[0].setWorldY(gp.tileSize * 6);

        gp.map[gp.snow].NPC[1] = new Stefano(gp);
        gp.map[gp.snow].NPC[1].setWorldX(gp.tileSize * 16);
        gp.map[gp.snow].NPC[1].setWorldY(gp.tileSize * 3);

        gp.map[gp.snow].NPC[2] = new Stranger(gp);
        gp.map[gp.snow].NPC[2].setWorldX(gp.tileSize * 16);
        gp.map[gp.snow].NPC[2].setWorldY(gp.tileSize * 18);

        gp.map[gp.snow].NPC[3] = new Strife(gp);
        gp.map[gp.snow].NPC[3].setWorldX(gp.tileSize * 29);
        gp.map[gp.snow].NPC[3].setWorldY(gp.tileSize * 15);

        gp.map[gp.snow].NPC[4] = new Tilda(gp);
        gp.map[gp.snow].NPC[4].setWorldX(gp.tileSize * 35);
        gp.map[gp.snow].NPC[4].setWorldY(gp.tileSize * 4);

        gp.map[gp.snow].NPC[5] = new Vincent(gp);
        gp.map[gp.snow].NPC[5].setWorldX(gp.tileSize * 30);
        gp.map[gp.snow].NPC[5].setWorldY(gp.tileSize * 23);

        gp.map[gp.snow].NPC[6] = new Zillah(gp);
        gp.map[gp.snow].NPC[6].setWorldX(gp.tileSize * 35);
        gp.map[gp.snow].NPC[6].setWorldY(gp.tileSize * 18);
    }
}
