package state;

import ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TitleState extends State
{
    BufferedImage pointer;

    public TitleState(GamePanel gp)
    {
        super(gp);
        imageScreen = new BufferedImage[1];
        setOptionMenu();
        getTitleImage();
    }
    public void setOptionMenu()
    {
        text = "The Chronicles of Avanlon";

        optionMenu = new String[3];
        optionMenu[0] = "NEW GAME";
        optionMenu[1] = "LOAD GAME";
        optionMenu[2] = "QUIT";
    }
    public void getTitleImage()
    {
        imageScreen[0] = setup("/screen/title_screen", gp.screenWidth, gp.screenHeight);
        pointer = setup("/ui/pointer");
    }
    @Override
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.drawImage(imageScreen[0], 0, 0, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));

        int x = getXCenteredText(text);
        int y = gp.tileSize * 2;

        // Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        // Main Color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Option Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        x = gp.tileSize;
        y += gp.tileSize * 3;
        for (int i = 0; i < optionMenu.length; i++)
        {
            if(i == commandNum)
            {
                g2.setColor(Color.GREEN);
                g2.drawString(optionMenu[i], x, y);
                g2.drawImage(pointer,  (int) (g2.getFontMetrics().getStringBounds(optionMenu[i], g2).getWidth() + gp.tileSize * 1.2), y - gp.tileSize / 2, null);
            }
            else
            {
                g2.setColor(Color.white);
                g2.drawString(optionMenu[i], x, y);
            }
            y += gp.tileSize;
        }
    }
}
