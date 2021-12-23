package object.potion;

import ui.GamePanel;

import java.awt.*;

public class EVDPotion extends Potion
{
    public EVDPotion(GamePanel gp)
    {
        super(gp);
        setValue();
        getPotionImage();
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }

    public void setValue()
    {
        setName("Evasion Potion");
        setType("Potion");
        setGenre("EVD");
        setDescription("A potion that add 1 EVD");
        setPoint(100);
        setBuyPrice(10);
        setSellPrice((int) (getBuyPrice() * 0.5));
        setSellAble(true);
        setCollision(false);
    }

    public void getPotionImage()
    {
        image = setup("/object/16x16/potion/potion 6");
    }
}
