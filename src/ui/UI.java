package ui;

import state.State;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI
{
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    public State[] states = new State[1];

    public UI(GamePanel gp)
    {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        if(gp.gameState == gp.battleState)
        {

        }
    }

    public int getXCenteredText(String text)
    {
        return ((gp.screenWidth / 2) - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2));
    }
}
