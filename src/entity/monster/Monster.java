package entity.monster;

import entity.Entity;
import ui.GamePanel;

public class Monster extends Entity
{
    private String type;
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

    // Getter & Setter
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public int getHP() {return HP;}
    public void setHP(int HP) {this.HP = HP;}
    public int getMaxHP() {return maxHP;}
    public void setMaxHP(int maxHP) {this.maxHP = maxHP;}
    public int getMP() {return MP;}
    public void setMP(int MP) {this.MP = MP;}
    public int getMaxMP() {return maxMP;}
    public void setMaxMP(int maxMP) {this.maxMP = maxMP;}
    public int getSTR() {return STR;}
    public void setSTR(int STR) {this.STR = STR;}
    public int getMaxSTR() {return maxSTR;}
    public void setMaxSTR(int maxSTR) {this.maxSTR = maxSTR;}
    public int getINT() {return INT;}
    public void setINT(int INT) {this.INT = INT;}
    public int getMaxINT() {return maxINT;}
    public void setMaxINT(int maxINT) {this.maxINT = maxINT;}
    public int getACC() {return ACC;}
    public void setACC(int ACC) {this.ACC = ACC;}
    public int getMaxACC() {return maxACC;}
    public void setMaxACC(int maxACC) {this.maxACC = maxACC;}
    public int getEVD() {return EVD;}
    public void setEVD(int EVD) {this.EVD = EVD;}
    public int getMaxEVD() {return maxEVD;}
    public void setMaxEVD(int maxEVD) {this.maxEVD = maxEVD;}
    public int getDEF() {return DEF;}
    public void setDEF(int DEF) {this.DEF = DEF;}
    public int getMaxDEF() {return maxDEF;}
    public void setMaxDEF(int maxDEF) {this.maxDEF = maxDEF;}
    public int getRST() {return RST;}
    public void setRST(int RST) {this.RST = RST;}
    public int getMaxRST() {return maxRST;}
    public void setMaxRST(int maxRST) {this.maxRST = maxRST;}
    public int getCRIT() {return CRIT;}
    public void setCRIT(int CRIT) {this.CRIT = CRIT;}
    public int getMaxCRIT() {return maxCRIT;}
    public void setMaxCRIT(int maxCRIT) {this.maxCRIT = maxCRIT;}
    public int getLvl() {return lvl;}
    public void setLvl(int lvl) {this.lvl = lvl;}
    public int getXp() {return xp;}
    public void setXp(int xp) {this.xp = xp;}
    public int getGold() {return gold;}
    public void setGold(int gold) {this.gold = gold;}
}
