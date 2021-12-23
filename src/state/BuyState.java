package state;

import object.SuperObject;
import object.armor.*;
import object.potion.*;
import object.weapon.*;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BuyState extends State
{
    Rectangle[] rectangles = new Rectangle[34];
    public SuperObject[] weapons = new SuperObject[30];
    public SuperObject[] armors = new SuperObject[30];
    public SuperObject[] potions = new SuperObject[9];
    BufferedImage coin;
    private String state = "";
    public BuyState(GamePanel gp)
    {
        super(gp);
        setRectangles();
        setItems();
        try
        {
            coin = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream( "/ui/coin.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setRectangles()
    {
        int width = 64;
        int height = 64;
        int x = 60;
        int y = 210;

        for (int i = 0; i <= 32; i++)
        {
            if(i == 32)
            {
                width = 290;
                height = 152;
                x += 20;
                y = 201;
            }
            rectangles[i] = new Rectangle(x, y, width, height);

            if(i == 7 || i == 15 || i == 23)
            {
                x = 60;
                y += height + 13;
            }
            else
                x += width + 13;
        }

        rectangles[33] = new Rectangle(51, 530, 90, 25);
    }

    public void setItems()
    {
        weapons[0] = new RustySword(this.gp);
        weapons[1] = new BlueFlareSword(this.gp);
        weapons[2] = new CoadSword(this.gp);
        weapons[3] = new IronSword(this.gp);
        weapons[4] = new LightBornSword(this.gp);
        weapons[5] = new MetalSword(this.gp);
        weapons[6] = new RubySword(this.gp);
        weapons[7] = new StarfallSword(this.gp);
        weapons[8] = new ThinSword(this.gp);
        weapons[9] = new PurpleFlareSword(this.gp);
        weapons[10] = new BloinStaff(this.gp);
        weapons[11] = new BrenStaff(this.gp);
        weapons[12] = new CrystalStaff(this.gp);
        weapons[13] = new DreamStaff(this.gp);
        weapons[14] = new FrostStaff(this.gp);
        weapons[15] = new GoldenStaff(this.gp);
        weapons[16] = new HolyStaff(this.gp);
        weapons[17] = new MajestyStaff(this.gp);
        weapons[18] = new SnowyStaff(this.gp);
        weapons[19] = new StoneStaff(this.gp);
        weapons[20] = new CrimsonBow(this.gp);
        weapons[21] = new EmeraldBow(this.gp);
        weapons[22] = new FireBow(this.gp);
        weapons[23] = new FuryBow(this.gp);
        weapons[24] = new GreenBow(this.gp);
        weapons[25] = new JadeBow(this.gp);
        weapons[26] = new MagmaBow(this.gp);
        weapons[27] = new NightBow(this.gp);
        weapons[28] = new RootBow(this.gp);
        weapons[29] = new SkyBow(this.gp);

        armors[0] = new LustyArmor(this.gp);
        armors[1] = new PlainArmor(this.gp);
        armors[2] = new RadiantArmor(this.gp);
        armors[3] = new ToyArmor(this.gp);
        armors[4] = new SolceArmor(this.gp);
        armors[5] = new PorcArmor(this.gp);
        armors[6] = new AthenaArmor(this.gp);
        armors[7] = new BindArmor(this.gp);
        armors[8] = new GoldenArmor(this.gp);
        armors[9] = new BoldArmor(this.gp);
        armors[10] = new MagicRobe(this.gp);
        armors[11] = new StarRobe(this.gp);
        armors[12] = new BynnRobe(this.gp);
        armors[13] = new BuzzRobe(this.gp);
        armors[14] = new SaintRobe(this.gp);
        armors[15] = new YevRobe(this.gp);
        armors[16] = new ExArmor(this.gp);
        armors[17] = new FiragaArmor(this.gp);
        armors[18] = new PrimeArmor(this.gp);
        armors[19] = new SolemArmor(this.gp);
        armors[20] = new BeastArmor(this.gp);
        armors[21] = new ClawArmor(this.gp);
        armors[22] = new SeyzArmor(this.gp);
        armors[23] = new BullArmor(this.gp);
        armors[24] = new PrismArmor(this.gp);
        armors[25] = new SolidArmor(this.gp);
        armors[26] = new VoyageArmor(this.gp);
        armors[27] = new TimeArmor(this.gp);
        armors[28] = new CloneArmor(this.gp);
        armors[29] = new DimArmor(this.gp);

        potions[0] = new HPPotion(this.gp);
        potions[1] = new MPPotion(this.gp);
        potions[2] = new STRPotion(this.gp);
        potions[3] = new INTPotion(this.gp);
        potions[4] = new ACCPotion(this.gp);
        potions[5] = new EVDPotion(this.gp);
        potions[6] = new DEFPotion(this.gp);
        potions[7] = new RSTPotion(this.gp);
        potions[8] = new CRITPotion(this.gp);
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        int x = 51;
        int y = 201;
        int width = 621;
        int height = 314;

        g2.setColor(new Color(255, 190, 27));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.drawImage(coin, x, y - 40, 32, 32, null);
        g2.drawString("" + gp.player.getGold(), x + 40, y - 15);

        g2.setColor(new Color(34, 34, 61));
        g2.fillRoundRect(x,y,width,height,0,0);
        g2.fillRoundRect(rectangles[32].x, rectangles[32].y, rectangles[32].width, rectangles[32].height, 0, 0);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x - 2,y - 2,width + 3,height + 3,5,5);
        g2.drawRoundRect(rectangles[32].x - 2, rectangles[32].y - 2, rectangles[32].width + 3, rectangles[32].height + 3, 0, 0);

        g2.setStroke(new BasicStroke(3));
        for (int i = 0; i <= 31; i++)
        {
            if(i == commandNum)
                g2.setColor(new Color(65, 91, 189));
            else
                g2.setColor(Color.WHITE);
            g2.drawRoundRect(rectangles[i].x - 3, rectangles[i].y - 3, rectangles[i].width + 6, rectangles[i].height + 6, 0, 0);
        }

        g2.setColor(Color.WHITE);
        g2.fillRect(rectangles[32].x + 300, rectangles[32].y + 4, 3, 120);
        int i = 0;

        Map<TextAttribute, Object> attributes = new HashMap<>();
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 26F));
        attributes.put(TextAttribute.TRACKING, 0.00);
        g2.setFont(g2.getFont().deriveFont(attributes));
        switch (state)
        {
            case "WEAPON" -> {
                for (int j = 0; j < weapons.length; j++)
                    g2.drawImage(weapons[j].image, rectangles[j].x, rectangles[j].y, null);

                if(commandNum < 30)
                {
                    g2.drawString(weapons[commandNum].getName() + "  -  " + ((Weapon)weapons[commandNum]).getJobClass(), rectangles[32].x + 15, rectangles[32].y + 20);
                    g2.drawString("Phy Att  :  " + ((Weapon) weapons[commandNum]).getPhyDamage(), rectangles[32].x + 15, rectangles[32].y + 45);
                    g2.drawString("Mag Att  :  " + ((Weapon) weapons[commandNum]).getMagDamage(), rectangles[32].x + 15, rectangles[32].y + 70);
                    g2.drawString("Speed    :  " + ((Weapon) weapons[commandNum]).getSpd(), rectangles[32].x + 15, rectangles[32].y + 95);
                    g2.drawImage(coin, rectangles[32].x + 15, rectangles[32].y + 110, 32, 32, null);
                    g2.setColor(new Color(255, 190, 27));
                    g2.drawString("" + weapons[commandNum].getBuyPrice(), rectangles[32].x + 50, rectangles[32].y + 134);
                }
            }
            case "ARMOR" -> {
                for (int j = 0; j < armors.length; j++)
                    g2.drawImage(armors[j].image, rectangles[j].x, rectangles[j].y, null);
                if(commandNum < 30)
                {
                    g2.drawString(armors[commandNum].getName() + "  -  " + ((Armor)armors[commandNum]).getJobClass(), rectangles[32].x + 15, rectangles[32].y + 20);
                    g2.drawString("Phy Def  :  " + ((Armor) armors[commandNum]).getPhyDef(), rectangles[32].x + 15, rectangles[32].y + 45);
                    g2.drawString("Mag Def  :  " + ((Armor) armors[commandNum]).getMagDef(), rectangles[32].x + 15, rectangles[32].y + 70);
                    g2.drawString("Speed    :  " + ((Armor) armors[commandNum]).getSpd(), rectangles[32].x + 15, rectangles[32].y + 95);
                    g2.drawImage(coin, rectangles[32].x + 15, rectangles[32].y + 110, 32, 32, null);
                    g2.setColor(new Color(255, 190, 27));
                    g2.drawString("" + armors[commandNum].getBuyPrice(), rectangles[32].x + 50, rectangles[32].y + 134);
                }
            }
            case "POTION" -> {
                for (int j = 0; j < potions.length; j++)
                    g2.drawImage(potions[j].image, rectangles[j].x, rectangles[j].y, null);
                if(commandNum < 9)
                {
                    g2.drawString(potions[commandNum].getName(), rectangles[32].x + 15, rectangles[32].y + 25);
                    int gap = 50;
                    for (String line: potions[commandNum].getDescription().split("\n"))
                    {
                        gap += 15;
                        g2.drawString(line, rectangles[32].x + 15, rectangles[32].y + gap);
                    }
                    g2.setColor(new Color(255, 190, 27));
                    g2.drawImage(coin, rectangles[32].x + 15, rectangles[32].y + 110, 32, 32, null);
                    g2.drawString("" + potions[commandNum].getBuyPrice(), rectangles[32].x + 50, rectangles[32].y + 134);
                }
            }
        }

        if(commandNum == 33)
            g2.setColor(new Color(65, 91, 189));
        else
            g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(rectangles[33].x - 2, rectangles[33].y - 2, rectangles[33].width + 3, rectangles[33].height + 3, 0, 0);

        g2.setColor(new Color(34, 34, 61));
        g2.fillRoundRect(rectangles[33].x , rectangles[33].y, rectangles[33].width, rectangles[33].height, 0, 0);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        attributes.put(TextAttribute.TRACKING, 0.00);
        g2.setFont(g2.getFont().deriveFont(attributes));
        g2.drawString("BACK", rectangles[33].x + 10, rectangles[33].y + 20);
    }

    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
}
