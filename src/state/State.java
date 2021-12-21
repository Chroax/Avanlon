package state;

import ui.GamePanel;

import java.awt.*;

public abstract class State
{
    GamePanel gp;

    public State(GamePanel gp)
    {
        this.gp = gp;
    }

    public void draw(Graphics2D g2){}
}
