package object.other;

import object.SuperObject;
import ui.GamePanel;

import java.awt.*;

public class Pillar extends SuperObject
{
    public boolean notSpawn = true;
    public Pillar(GamePanel gp)
    {
        super(gp);
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        setName("Pillar");
        setDescription("A Magical Pillar that can summon big monster");
        setSellAble(false);
        setType("Immutable");
        image = setup("/object/64x64/towersummon");

        setCollision(true);
    }
}
