package ui;

import state.BattleState;
import state.State;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI
{
    GamePanel gp;
    Graphics2D g2;
    public static Font maruMonica;
    public static Font pokemon;
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

        try {
            InputStream is = getClass().getResourceAsStream("/font/Pokemon.ttf");
            pokemon = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        getUIImage();
        setState();
    }

    public void setState()
    {
        states[0] = new BattleState(gp);
    }

    public void getUIImage()
    {

    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        if(gp.gameState == gp.battleState)
            states[0].draw(g2);
        else if(gp.gameState == gp.playState)
            drawPlayUI();
    }

    public void drawPlayUI()
    {

    }

    public int getXCenteredText(String text)
    {
        return ((gp.screenWidth / 2) - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2));
    }
}
