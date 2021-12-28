package object.drop;

import object.SuperObject;
import ui.GamePanel;

import java.awt.*;

public class BlackScale extends SuperObject
{
    public BlackScale(GamePanel gp)
    {
        super(gp);
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        setName("Black Scale");
        setDescription("A Scale that drop from \nBlack Arima");
        setSellAble(true);
        setType("Mutable");
        image = setup("/object/16x16/drop/black_scale");

        setCollision(false);
    }
}
