package state;

import entity.Player;
import object.SuperObject;
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

public class InventoryState extends State
{
    Rectangle[] rectangles = new Rectangle[34];
    BufferedImage coin;
    private boolean sellState = false;

    public InventoryState(GamePanel gp)
    {
        super(gp);
        setRectangles();
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
        int x = 211;
        int y = 166;

        for (int i = 0; i <= 32; i++)
        {
            if(i == 32)
            {
                width = 602;
                height = 128;
            }
            rectangles[i] = new Rectangle(x, y, width, height);

            if(i == 7 || i == 15 || i == 23 || i == 31)
            {
                x = 211;
                y += height + 13;
            }
            else
                x += width + 13;
        }

        rectangles[33] = new Rectangle(202, 630, 90, 25);
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        int x = 202;
        int y = 155;
        int width = 621;
        int height = 458;

        g2.setColor(new Color(255, 190, 27));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.drawImage(coin, x, y - 40, 32, 32, null);
        g2.drawString("" + gp.player.getGold(), x + 40, y - 15);

        g2.setColor(new Color(34, 34, 61));
        g2.fillRoundRect(x,y,width,height,0,0);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x - 2,y - 2,width + 3,height + 3,5,5);

        g2.setStroke(new BasicStroke(3));
        for (int i = 0; i <= 32; i++)
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

        for (Map.Entry<SuperObject, Integer> newMap : gp.player.items.entrySet())
        {
            int total = newMap.getValue();
            g2.drawImage(newMap.getKey().image, rectangles[i].x, rectangles[i].y, null);
            x = rectangles[i].x + 60 - (int) g2.getFontMetrics().getStringBounds("" + total, g2).getWidth();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16F));
            attributes.put(TextAttribute.TRACKING, 0.00);
            g2.setFont(g2.getFont().deriveFont(attributes));
            g2.drawString("" + total, x, rectangles[i].y + 63);

            if(i == commandNum)
            {
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 26F));
                attributes.put(TextAttribute.TRACKING, 0.05);
                g2.setFont(g2.getFont().deriveFont(attributes));
                if(newMap.getKey().getType().equals("Weapon"))
                {
                    g2.drawString(newMap.getKey().getName() + "  -  " + ((Weapon)newMap.getKey()).getJobClass(), rectangles[32].x + 15, rectangles[32].y + 35);
                    g2.drawString("Phy Att : " + ((Weapon) newMap.getKey()).getPhyDamage(), rectangles[32].x + 315, rectangles[32].y + 35);
                    g2.drawString("Mag Att : " + ((Weapon) newMap.getKey()).getMagDamage(), rectangles[32].x + 315, rectangles[32].y + 63);
                    g2.drawString("Speed   : " + ((Weapon) newMap.getKey()).getSpd(), rectangles[32].x + 315, rectangles[32].y + 90);
                }
                else
                    g2.drawString(newMap.getKey().getName(), rectangles[32].x + 15, rectangles[32].y + 35);

                int gap = 60;
                for (String line: newMap.getKey().getDescription().split("\n"))
                {
                    gap += 15;
                    g2.drawString(line, rectangles[32].x + 15, rectangles[32].y + gap);
                }
            }

            i++;
        }

        if(sellState)
        {
            if(commandNum == 33)
                g2.setColor(new Color(65, 91, 189));
            else
                g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(rectangles[33].x - 2, rectangles[33].y - 2, rectangles[33].width + 3, rectangles[33].height + 3, 0, 0);

            g2.setColor(new Color(34, 34, 61));
            g2.fillRoundRect(rectangles[33].x , rectangles[33].y, rectangles[33].width, rectangles[33].height, 0, 0);

            g2.setColor(Color.WHITE);
            attributes.put(TextAttribute.TRACKING, 0.00);
            g2.setFont(g2.getFont().deriveFont(attributes));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
            g2.drawString("BACK", rectangles[33].x + 10, rectangles[33].y + 20);
        }
    }

    public boolean isSellState() {return sellState;}
    public void setSellState(boolean sellState) {this.sellState = sellState;}
}
