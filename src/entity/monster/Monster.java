package entity.monster;

import entity.Entity;
import ui.GamePanel;

public class Monster extends Entity
{
    private int HP;
    private int maxHP;
    private int MP;
    private int maxMP;
    private int STR;
    private int maxSTR;
    private int INT;
    private int maxINT;
    private int ACC;
    private int maxACC;
    private int EVD;
    private int maxEVD;
    private int DEF;
    private int maxDEF;
    private int RST;
    private int maxRST;
    private int CRIT;
    private int maxCRIT;

    private int lvl;
    private int xp;
    private int gold;

    public Monster(GamePanel gp) { super(gp); }
}
