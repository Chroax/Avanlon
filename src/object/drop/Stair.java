package object.drop;

import object.SuperObject;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Stair extends SuperObject
{
    public Stair(GamePanel gp)
    {
        super(gp);
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        setName("Stair");
        setDescription("A Magical Stair that can make player teleport to another place");
        setSellAble(false);
        image = setup("/object/16x16/other/stairs");

        setCollision(true);
    }
}
