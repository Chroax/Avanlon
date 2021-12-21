package state;

import ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HomeState extends State
{
    int counter = 0;

    public HomeState(GamePanel gp)
    {
        super(gp);
        imageScreen = new BufferedImage[1];
        setOptionMenu();
        getHomeImage();
    }
    public void setOptionMenu()
    {
        text = "Enter to continue";
    }

    public void getHomeImage()
    {
        imageScreen[0] = setup("/screen/home_screen", gp.screenWidth, gp.screenHeight);
    }

    @Override
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.drawImage(imageScreen[0], 0, 0, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));

        if(counter < 30)
            text = "Enter to continue";
        else
            text = "";

        int x = getXCenteredText(text);
        int y = gp.tileSize * 10;

        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        counter++;
        if(counter > 60)
            counter = 0;
    }
}
