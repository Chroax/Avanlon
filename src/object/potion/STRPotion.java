package object.potion;

import entity.JobClass;
import ui.GamePanel;

import java.awt.*;

public class STRPotion extends Potion
{
    public STRPotion(GamePanel gp)
    {
        super(gp);
        setValue();
        getPotionImage();
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }

    public void setValue()
    {
        setName("Strength Potion");
        setType("Potion");
        setGenre("STR");
        setDescription("A potion that add 1 STR");
        setPoint(100);
        setBuyPrice(10);
        setSellPrice((int) (getBuyPrice() * 0.5));
        setSellAble(true);
        setCollision(false);
    }

    public void getPotionImage()
    {
        image = setup("/object/16x16/potion/potion 3");
    }
}
