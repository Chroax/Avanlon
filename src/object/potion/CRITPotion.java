package object.potion;

import ui.GamePanel;

import java.awt.*;

public class CRITPotion extends Potion
{
    public CRITPotion(GamePanel gp)
    {
        super(gp);
        setValue();
        getPotionImage();
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }

    public void setValue()
    {
        setName("Critical Potion");
        setType("Potion");
        setGenre("CRIT");
        setDescription("A potion that add 1 CRIT");
        setPoint(100);
        setBuyPrice(10);
        setSellPrice((int) (getBuyPrice() * 0.5));
        setSellAble(true);
        setCollision(false);
    }

    public void getPotionImage()
    {
        image = setup("/object/16x16/potion/potion 9");
    }
}