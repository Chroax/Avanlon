package state;

import object.SuperObject;
import object.weapon.RustySword;
import object.weapon.Weapon;
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
    public SuperObject[] potions = new SuperObject[8];
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
        for (int i = 0; i < 30; i++)
            weapons[i] = new RustySword(this.gp);
        for (int i = 0; i < 30; i++)
            armors[i] = new RustySword(this.gp);
        for (int i = 0; i < 8; i++)
            potions[i] = new RustySword(this.gp);
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
                    g2.drawString(armors[commandNum].getName() + "  -  " + ((Weapon)armors[commandNum]).getJobClass(), rectangles[32].x + 15, rectangles[32].y + 20);
                    g2.drawString("Phy Def  :  " + ((Weapon) armors[commandNum]).getPhyDamage(), rectangles[32].x + 15, rectangles[32].y + 45);
                    g2.drawString("Mag Def  :  " + ((Weapon) armors[commandNum]).getMagDamage(), rectangles[32].x + 15, rectangles[32].y + 70);
                    g2.drawString("Speed    :  " + ((Weapon) armors[commandNum]).getSpd(), rectangles[32].x + 15, rectangles[32].y + 95);
                    g2.drawImage(coin, rectangles[32].x + 15, rectangles[32].y + 110, 32, 32, null);
                    g2.setColor(new Color(255, 190, 27));
                    g2.drawString("" + armors[commandNum].getBuyPrice(), rectangles[32].x + 50, rectangles[32].y + 134);
                }
            }
            case "POTION" -> {
                for (int j = 0; j < potions.length; j++)
                    g2.drawImage(potions[j].image, rectangles[j].x, rectangles[j].y, null);
                if(commandNum < 8)
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
