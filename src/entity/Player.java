package entity;

import entity.monster.Monster;
import object.SuperObject;
import object.armor.Armor;
import object.other.Pillar;
import object.potion.Potion;
import object.weapon.Weapon;
import state.BattleState;
import ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Player extends Entity
{
    private int maxEXP;

    private final int screenX;
    private final int screenY;
    public final JobClass jobClass;
    private final boolean gender;
    public HashMap <SuperObject, Integer> items = new HashMap<>();
    private boolean fullInventory = false;
    private Weapon weapon;
    private Armor armor;
    private int soundCounter = 0;

    public Player(GamePanel gp, JobClass jobClass, boolean gender)
    {
        super(gp);
        this.jobClass = jobClass;
        this.gender = gender;

        if(jobClass == JobClass.PALADIN)
        {
            setImgHeight(64);
            setImgWidth(64);
            solidArea = new Rectangle(16, 32, 32, 32);
        }
        else
        {
            setImgHeight(32);
            setImgWidth(48);
            solidArea = new Rectangle(10, 32, 32, 32);
        }

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setSolidAreaDefaultX(solidArea.x);
        setSolidAreaDefaultY(solidArea.y);

        setDefaultValues();
        getPlayerImage();
    }

    private void setDefaultValues()
    {
        setHP(150);setMaxHP(150);
        setMP(100);setMaxMP(100);
        setSTR(10);setMaxSTR(10);
        setINT(6);setMaxINT(6);
        setACC(8);setMaxACC(8);
        setEVD(15);setMaxEVD(15);
        setDEF(15);setMaxDEF(15);
        setRST(1);setMaxRST(1);
        setCRIT(6);setMaxCRIT(6);
        setLvl(1);
        setGold(1000);
        setEXP(0);
        setMaxEXP(100);

        setName("Player");
        setXAndY(gp.getMapPick());
        setSpeed(10);
        setDirection("down");
        spriteNum = 2;
        spriteCounter = 0;
        dialogueIndex = 0;
    }

    public void setXAndY(int i)
    {
        switch (i)
        {
            case 0 -> {
                setWorldX((int)(gp.tileSize * 11.5));
                setWorldY(gp.tileSize * 34);
            }
            case 1 -> {
                setWorldX(gp.tileSize * 23);
                setWorldY(gp.tileSize * 21);
            }
            case 2 -> {
                setWorldX(gp.tileSize * 16);
                setWorldY(gp.tileSize * 32);
            }
            case 3 -> {
                setWorldX(gp.tileSize * 9);
                setWorldY(gp.tileSize * 34);
            }
            case 4 -> {
                setWorldX(gp.tileSize * 20);
                setWorldY(gp.tileSize * 21);
            }
        }
    }

    private void getPlayerImage()
    {
        String txt = "/character";

        if(jobClass == JobClass.PALADIN)
        {
            if(gender)
                txt += "/64x64/femalewarrior/female_warrior";
            else
                txt += "/64x64/warrior/warrior";
        }
        else if(jobClass == JobClass.WIZARD)
        {
            if(gender)
                txt += "/32x48/femalemagician/female_magician";
            else
                txt += "/32x48/magician/magician";
        }
        else if(jobClass == JobClass.ARCHER)
        {
            if(gender)
                txt += "/32x48/femalearcher/female_archer";
            else
                txt += "/32x48/archer/archer";
        }

        if(jobClass != JobClass.PALADIN)
        {
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
        else
        {
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
    }

    @Override
    public void update()
    {
        if(gp.keyH.upPressed || gp.keyH.downPressed || gp.keyH.leftPressed || gp.keyH.rightPressed)
        {
            if (gp.keyH.upPressed)
                setDirection("up");
            else if (gp.keyH.downPressed)
                setDirection("down");
            else if (gp.keyH.leftPressed)
                setDirection("left");
            else setDirection("right");

            // Check Tile Collision
            this.setCollisionOn(false);
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObj(this, true);
            interactObj(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.map[gp.getMapPick()].NPC);
            interactNPC(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.map[gp.getMapPick()].monsters);
            interactMonster(monsterIndex);

            if(!isCollisionOn())
            {
                switch (getDirection())
                {
                    case "up" -> setWorldY(getWorldY() - getSpeed());
                    case "down" -> setWorldY(getWorldY() + getSpeed());
                    case "left" -> setWorldX(getWorldX() - getSpeed());
                    case "right" -> setWorldX(getWorldX() + getSpeed());
                }
            }
            else
            {
                soundCounter++;
                if(soundCounter > 8)
                {
                    gp.playSE(7);
                    soundCounter = 0;
                }
            }

            spriteCounter++;
            if (spriteCounter > 8)
            {
                if (spriteNum == 1)
                    spriteNum = 3;
                else if (spriteNum == 3  || spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
        else
        {
            standCounter++;
            if(standCounter == 8)
            {
                spriteNum = 2;
                standCounter = 0;
            }
        }
    }

    public void interactMonster(int i)
    {
        if(i != 999)
        {
            if(gp.keyH.enterPressed)
            {
                ((BattleState)gp.ui.states[gp.ui.battleState]).isDrop = false;
                ((BattleState)gp.ui.states[gp.ui.battleState]).messageOn = false;
                gp.stopMusic();
                gp.playMusic(6);
                gp.gameState = gp.battleState;
                gp.monsterIndex = i;
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void interactNPC(int i)
    {
        if(i != 999)
        {
            if(gp.keyH.enterPressed)
            {
                switch (gp.map[gp.getMapPick()].NPC[i].getName())
                {
                    case "Merchant" -> {
                        if(gp.map[gp.getMapPick()].NPC[i].nextDialogue == 0)
                        {
                            gp.gameState = gp.dialogueState;
                            gp.ui.endDialogue = false;
                            gp.map[gp.getMapPick()].NPC[i].speak();
                            gp.npcIndex = i;
                        }
                        else
                            gp.gameState = gp.merchantState;
                    }
                    default -> {
                        gp.gameState = gp.dialogueState;
                        gp.ui.endDialogue = false;
                        gp.map[gp.getMapPick()].NPC[i].speak();
                        gp.npcIndex = i;
                    }
                }
                gp.keyH.enterPressed = false;
            }
        }
    }

    public void interactObj(int i)
    {
        if(i != 999)
        {
            String objectName = gp.map[gp.getMapPick()].obj[i].getName();
            switch (objectName)
            {
                case "Stair", "Portal" -> {
                    if(gp.keyH.enterPressed)
                    {
                        gp.ui.doneTp = true;
                        gp.gameState = gp.chooseMapState;
                    }
                }
                case "Pillar" -> {
                    if(gp.keyH.enterPressed)
                    {
                        if(((Pillar)gp.map[gp.getMapPick()].obj[i]).notSpawn)
                        {
                            if(gp.getMapPick() == gp.plain)
                                gp.aSetter.setBoss(gp.plain);
                            else if(gp.getMapPick() == gp.dungeon)
                                gp.aSetter.setBoss(gp.dungeon);
                            else if(gp.getMapPick() == gp.castle)
                                gp.aSetter.setBoss(gp.castle);
                            else if(gp.getMapPick() == gp.snow)
                                gp.aSetter.setBoss(gp.snow);
                            ((Pillar)gp.map[gp.getMapPick()].obj[i]).notSpawn = false;
                        }
                    }
                }
                case "Rusty Sword", "Bloin Staff", "Blue Flare Sword", "Coad Sword", "Bren Staff",
                        "Crimson Bow", "Crystal Staff", "Dream Staff", "Emerald Bow", "Fire Bow",
                        "Frost Staff", "Fury Bow", "Golden Staff", "Green Bow", "Holy Staff",
                        "Iron Sword", "Jade Bow", "Light Born Sword", "Magma Bow", "Majesty Staff",
                        "Metal Sword", "Night Bow", "Purple Flare Sword", "Root Bow", "Ruby Sword",
                        "Sky Bow", "Snowy Staff", "Starfall Sword", "Stone Staff", "Thin Sword",
                        "Athena Armor", "Beast Armor", "Bind Armor", "Bold Armor", "Bull Armor",
                        "Buzz Robe", "Bynn Robe", "Claw Armor", "Clone Armor", "Dim Armor", "Ex Armor",
                        "Firaga Armor", "Golden Armor", "Lusty Armor", "Magic Robe", "Plain Armor",
                        "Porc Armor", "Prime Armor", "Prism Armor", "Radiant Armor", "Saint Robe",
                        "Seyz Armor", "Solce Armor", "Solem Armor", "Solid Armor", "Star Robe", "Time Armor",
                        "Toy Armor", "Voyage Armor", "Yev Robe", "Health Potion", "Magic Point Potion",
                        "Strength Potion", "Intelligence Potion", "Accuracy Potion", "Evasion Potion",
                        "Defence Potion", "Resistance Potion", "Critical Potion" -> {
                    if(!fullInventory)
                    {
                        gp.playSE(14);
                        addItem(gp.map[gp.getMapPick()].obj[i], 1, false);
                        gp.map[gp.getMapPick()].obj[i] = null;
                    }
                }
            }
        }
    }

    public void addItem(SuperObject object, int quantity, boolean buyStatus)
    {
        if(buyStatus)
        {
            if(!fullInventory && getGold() - object.getBuyPrice() >= 0)
            {
                setGold(getGold() - object.getBuyPrice());
                gp.playSE(8);
            }
            else
                return;
        }
        for (Map.Entry<SuperObject, Integer> new_Map : items.entrySet())
        {
            if (new_Map.getKey().equals(object))
            {
                int total = new_Map.getValue() + quantity;
                items.put(new_Map.getKey(), total);
                return;
            }
        }
        if(items.size() == 32)
        {
            fullInventory = true;
            gp.ui.inventoryFullMessageOn = true;
        }
        else
            items.put(object, quantity);
    }

    public void defeatMonster(Entity entity)
    {
        if(((Monster)entity).getDropItem() != null)
        {
            Random random = new Random();
            if (random.nextDouble() >= 0.5)
            {
                ((BattleState)gp.ui.states[gp.ui.battleState]).isDrop = true;
                addItem(((Monster)entity).getDropItem(), 1, false);
            }
        }
        gp.playSE(19);
        setGold(getGold() + entity.getGold());
        setEXP(getEXP() + entity.getEXP());
        if(getEXP() >= getMaxEXP())
            gp.playSE(12);
        while(getEXP() >= getMaxEXP())
            levelUp();
        switch (entity.getName())
        {
            case "Behemoth" -> {
                gp.aSetter.setPortal();
                gp.unlockMap[gp.dungeon] = true;
            }
            case "Dragon" -> {
                gp.aSetter.setPortal();
                gp.unlockMap[gp.castle] = true;
            }
            case "Dragon Lord" -> {
                gp.aSetter.setPortal();
                gp.unlockMap[gp.snow] = true;
            }
            case "Fire Demon" -> {
                System.out.println("TAMAT");
            }
        }

    }

    public void respawn()
    {
        if(getLvl() > 1)
            setLvl(getLvl() - 1);

        setGold(getGold() - 100);
        if(getGold() < 0)
            setGold(0);

        gp.stopMusic();
        gp.playSE(16);
        gp.ui.respawn = true;
        gp.gameState = gp.transitionState;
    }

    @Override
    public void attack(Entity entity, int index)
    {
        Random random = new Random();
        if(getWeapon() != null)
        {
            if(random.nextDouble() < 0.15 * (entity.getEVD() + getWeapon().getSpd()) / getACC())
                ((BattleState)gp.ui.states[gp.ui.battleState]).showMessage(entity.name + "   dodge   the   attack", index, "ATTACK");
            else
            {
                int phyAtt = getSTR() * 2 - entity.getDEF() + getWeapon().getPhyDamage();
                int magAtt = getINT() * 2 - entity.getRST() + getWeapon().getMagDamage();
                int totalAtt = phyAtt + magAtt;
                if(random.nextDouble() < 0.15 * getCRIT() / entity.getEVD())
                    totalAtt *= 2;
                entity.setHP(entity.getHP() - totalAtt);
                ((BattleState)gp.ui.states[gp.ui.battleState]).showMessage(name + "   give   " + totalAtt + "   damage", index, "ATTACK");
            }
        }
        else
            super.attack(entity, index);
    }

    public void levelUp()
    {
        setMaxHP(getMaxHP() + 10);setMaxMP(getMaxMP() + 10);
        setMaxSTR(getMaxSTR() + 1);setMaxINT(getMaxINT() + 1);
        setMaxACC(getMaxACC() + 1);setMaxEVD(getMaxEVD() + 1);
        setMaxDEF(getMaxDEF() + 1);setMaxRST(getMaxRST() + 1);
        setMaxCRIT(getMaxSTR() + 1);

        setEXP(getEXP() - getMaxEXP());
        setMaxEXP(getMaxEXP() + (int) (getMaxEXP() * 0.5));
        setLvl(getLvl() + 1);
        resetStat();
    }

    public void useItem(int index, boolean sellStatus)
    {
        boolean use = false;
        int i = 0;
        if(index > items.size())
            return;
        for (Map.Entry<SuperObject, Integer> new_Map : items.entrySet())
        {
            if(i == index)
            {
                if(!sellStatus)
                {
                    switch (new_Map.getKey().getType())
                    {
                        case "Weapon" -> {
                            if(((Weapon) new_Map.getKey()).getJobClass().equals(this.jobClass))
                            {
                                equipWeapon((Weapon) new_Map.getKey());
                                use = true;
                            }
                        }
                        case "Potion" -> use = usePotion((Potion) new_Map.getKey());
                        case "Armor" -> {
                            if(((Armor) new_Map.getKey()).getJobClass().equals(this.jobClass))
                            {
                                equipArmor((Armor) new_Map.getKey());
                                use = true;
                            }
                        }
                        case "Key" -> System.out.println("Do something with key");
                        case "Drop" -> System.out.println("Do something with drop item");
                    }
                }
                else
                {
                    setGold(getGold() + new_Map.getKey().getSellPrice());
                    use = true;
                }

                if(use)
                {
                    int total = new_Map.getValue() - 1;
                    if(total <= 0)
                    {
                        items.remove(new_Map.getKey());
                        if(fullInventory)
                            fullInventory = false;
                    }
                    else
                        items.put(new_Map.getKey(), total);
                    break;
                }
            }
            i++;
        }
    }

    public void equipWeapon(Weapon weapon)
    {
        if(this.weapon != null)
            unEquipWeapon();
        this.setWeapon(weapon);
    }

    public void unEquipWeapon()
    {
        if(this.weapon != null)
        {
            addItem(this.weapon, 1, false);
            this.weapon = null;
        }
    }

    public void equipArmor(Armor armor)
    {
        if(this.weapon != null)
            unEquipArmor();
        this.setArmor(armor);
        this.setMaxDEF(getMaxDEF() + armor.getPhyDef());
        this.setMaxRST(getMaxRST() + armor.getMagDef());
        if(!gp.keyH.stillBattle)
        {
            this.setRST(getMaxRST());
            this.setDEF(getMaxDEF());
        }
    }

    public void unEquipArmor()
    {
        if(this.armor != null)
        {
            this.setMaxDEF(getMaxDEF() - armor.getPhyDef());
            this.setMaxRST(getMaxRST() - armor.getMagDef());
            if(!gp.keyH.stillBattle)
            {
                this.setRST(getMaxRST());
                this.setDEF(getMaxDEF());
            }
            addItem(this.armor, 1, false);
            this.armor = null;
        }
    }

    public boolean usePotion(Potion potion)
    {
        boolean use = true;
        switch (potion.getGenre())
        {
            case "HP" -> {
                if(getHP() == getMaxHP())
                    use = false;
                else
                {
                    setHP(getHP() + potion.getPoint());
                    if(getHP() > getMaxHP())
                        setHP(getMaxHP());
                }
            }
            case "MP" -> {
                if(getMP() == getMaxMP())
                    use = false;
                else
                {
                    setMP(getMP() + potion.getPoint());
                    if(getMP() > getMaxMP())
                        setMP(getMaxMP());
                }
            }
            case "STR" -> {
                setMaxSTR(getMaxSTR() + potion.getPoint());
                setSTR(getMaxSTR());
            }
            case "INT" ->{
                setMaxINT(getMaxINT() + potion.getPoint());
                setINT(getMaxINT());
            }
            case "ACC" ->{
                setMaxACC(getMaxACC() + potion.getPoint());
                setACC(getMaxACC());
            }
            case "EVD" ->{
                setMaxEVD(getMaxEVD() + potion.getPoint());
                setEVD(getMaxEVD());
            }
            case "DEF" ->{
                setMaxDEF(getMaxDEF() + potion.getPoint());
                setDEF(getMaxDEF());
            }
            case "RST" ->{
                setMaxRST(getMaxRST() + potion.getPoint());
                setRST(getMaxRST());
            }
            case "CRIT" ->{
                setMaxCRIT(getMaxCRIT() + potion.getPoint());
                setCRIT(getMaxCRIT());
            }
        }
        return use;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch (getDirection())
        {
            case "up" -> {
                if (spriteNum == 1)
                    image = up1;
                else if (spriteNum == 2)
                    image = up2;
                else if (spriteNum == 3)
                    image = up3;
            }
            case "down" -> {
                if (spriteNum == 1)
                    image = down1;
                else if (spriteNum == 2)
                    image = down2;
                else if (spriteNum == 3)
                    image = down3;
            }
            case "left" -> {
                if (spriteNum == 1)
                    image = left1;
                else if (spriteNum == 2)
                    image = left2;
                else if (spriteNum == 3)
                    image = left3;
            }
            case "right" -> {
                if (spriteNum == 1)
                    image = right1;
                else if (spriteNum == 2)
                    image = right2;
                else if (spriteNum == 3)
                    image = right3;
            }
        }

        // Stop moving camera at the edge
        int x = screenX;
        int y = screenY;

        if(screenX > worldX)
            x = worldX;
        if(screenY > worldY)
            y = worldY;

        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset > gp.getWorldWidth() - worldX)
            x = gp.screenWidth - (gp.getWorldWidth() - worldX);

        int bottomOffset = gp.screenHeight - screenY;
        if(bottomOffset > gp.getWorldHeight() - worldY)
            y = gp.screenHeight - (gp.getWorldHeight() - worldY);

        g2.drawImage(image, x, y, null);
    }

    // Getter
    public int getScreenX() { return screenX; }
    public int getScreenY() { return screenY; }
    public int getMaxEXP() {return maxEXP;}
    public void setMaxEXP(int maxEXP) {this.maxEXP = maxEXP;}
    public Weapon getWeapon() {return weapon;}
    public void setWeapon(Weapon weapon) {this.weapon = weapon;}

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}
