package entity;

import state.BattleState;
import ui.GamePanel;
import ui.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class Entity
{
    protected String name;
    protected int HP;
    protected int maxHP;
    protected int MP;
    protected int maxMP;
    protected int STR;
    protected int maxSTR;
    protected int INT;
    protected int maxINT;
    protected int ACC;
    protected int maxACC;
    protected int EVD;
    protected int maxEVD;
    protected int DEF;
    protected int maxDEF;
    protected int RST;
    protected int maxRST;
    protected int CRIT;
    protected int maxCRIT;
    protected int worldX;
    protected int worldY;
    private int speed;
    private int lvl;
    private int EXP;
    private int gold;

    // Sprites Image
    protected BufferedImage up1, up2, up3;
    public BufferedImage down1, down2, down3;
    protected BufferedImage left1, left2, left3;
    protected BufferedImage right1, right2, right3;
    private String direction;
    public int spriteCounter;
    public int spriteNum;
    protected int standCounter = 0;
    protected int actionLockCounter = 0;

    private int imgWidth;
    private int imgHeight;

    // Dialog Chat
    protected String[][] dialogues;
    protected int dialogueIndex;

    // Collision
    private boolean collisionOn;
    public Rectangle solidArea;
    private int solidAreaDefaultX;
    private int solidAreaDefaultY;

    public GamePanel gp;

    public Entity(GamePanel gp) { this.gp = gp; }

    public void update() {}

    public void setAction(){}

    public void speak(){}

    public void attack(Entity entity, int index)
    {
        Random random = new Random();
        if(random.nextDouble() < 0.15 * entity.getEVD() / getACC())
            ((BattleState)gp.ui.states[gp.ui.battleState]).showMessage(entity.name + "   dodge   the   attack", index);
        else
        {
            int phyAtt = getSTR() * 2 - entity.getDEF();
            int magAtt = getINT() * 2 - entity.getRST();
            int totalAtt = phyAtt + magAtt;
            if(random.nextDouble() < 0.15 * getCRIT() / entity.getEVD())
                totalAtt *= 2;
            entity.setHP(entity.getHP() - totalAtt);
            ((BattleState)gp.ui.states[gp.ui.battleState]).showMessage(name + "   give   " + totalAtt + "   damage", index);
        }
    }

    public void resetStat()
    {
        HP = maxHP; MP = maxMP;
        STR = maxSTR; INT = maxINT;
        ACC = maxACC; EVD = maxEVD;
        DEF = maxDEF; RST = maxRST;
        CRIT = maxCRIT;
    }

    public BufferedImage setup(String imagePath)
    {
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            scaledImage = gp.uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public BufferedImage setup(String imagePath, double scale, boolean width)
    {
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            if(width)
                scaledImage = gp.uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize, (int) (scaledImage.getWidth() * scale), gp.tileSize);
            else
                scaledImage = gp.uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize, gp.tileSize, (int) (scaledImage.getHeight() * scale));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public BufferedImage setup(String imagePath, double scaleWidth, double scaleHeight)
    {
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            scaledImage = gp.uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize, (int) (scaledImage.getWidth() * scaleWidth), (int) (scaledImage.getHeight() * scaleHeight));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.getScreenX();
        int screenY = worldY - gp.player.worldY + gp.player.getScreenY();

        // STOP MOVING CAMERA
        if(gp.player.worldX < gp.player.getScreenX())
            screenX = worldX;
        if(gp.player.worldY < gp.player.getScreenY())
            screenY = worldY;

        int rightOffset = gp.screenWidth - gp.player.getScreenX();
        if(rightOffset > gp.getWorldWidth() - gp.player.worldX)
            screenX = gp.screenWidth - (gp.getWorldWidth() - worldX);

        int bottomOffset = gp.screenHeight - gp.player.getScreenY();
        if(bottomOffset > gp.getWorldHeight() - gp.player.worldY)
            screenY = gp.screenHeight - (gp.getWorldHeight() - worldY);

        switch (direction)
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

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.getScreenX() &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.getScreenX() &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.getScreenY() &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.getScreenY())
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            // If player is around the edge, draw everything
        else if(gp.player.worldX < gp.player.getScreenX() ||
                gp.player.worldY < gp.player.getScreenY() ||
                rightOffset > gp.getWorldWidth() - gp.player.worldX ||
                bottomOffset > gp.getWorldHeight() - gp.player.worldY)
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        g2.setColor(Color.BLACK);
        g2.setFont(UI.pokemon);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14F));
        String text = name;

        if(lvl != 0)
            text += "   Lvl-" + lvl;

        int x = ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() - solidArea.width) / 2;
        g2.drawString(text, screenX - x, solidArea.y + solidArea.height + screenY + 10);
    }

    // Getter & Setter
    public int getWorldX() { return worldX; }
    public void setWorldX(int worldX) { this.worldX = worldX; }
    public int getWorldY() { return worldY;}
    public void setWorldY(int worldY) { this.worldY = worldY; }
    public boolean isCollisionOn() { return collisionOn; }
    public void setCollisionOn(boolean collisionOn) { this.collisionOn = collisionOn; }
    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getSolidAreaDefaultX() {return solidAreaDefaultX;}
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {this.solidAreaDefaultX = solidAreaDefaultX;}
    public int getSolidAreaDefaultY() {return solidAreaDefaultY;}
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {this.solidAreaDefaultY = solidAreaDefaultY;}
    public int getImgWidth() {return imgWidth;}
    public void setImgWidth(int imgWidth) {this.imgWidth = imgWidth;}
    public int getImgHeight() {return imgHeight;}
    public void setImgHeight(int imgHeight) {this.imgHeight = imgHeight;}
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
    public int getEXP() {return EXP;}
    public void setEXP(int EXP) {this.EXP = EXP;}
    public int getGold() {return gold;}
    public void setGold(int gold) {this.gold = gold;}
}
