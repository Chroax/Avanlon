package object.other;

import object.SuperObject;
import ui.GamePanel;

import java.awt.*;

public class Portal extends SuperObject
{
    public Portal(GamePanel gp)
    {
        super(gp);
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        setName("Portal");
        setDescription("A Magical Portal that can make player teleport to another place");
        setSellAble(false);
        setType("Immutable");
        image = setup("/object/48x48/portal");

        setCollision(true);
    }
}
