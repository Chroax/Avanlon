package state;

import entity.Player;
import ui.GamePanel;

import java.awt.*;

public class InventoryState extends State
{
    Rectangle[] rectangles = new Rectangle[33];

    public InventoryState(GamePanel gp) {
        super(gp);
        setRectangles();
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
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        int x = 202;
        int y = 155;
        int width = 621;
        int height = 458;
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
    }
}
