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
        setSellAble(false);
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/16x16/other/stairs.png")));
            image = gp.uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        setCollision(true);
    }
}
