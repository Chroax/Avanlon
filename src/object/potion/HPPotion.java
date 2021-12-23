package object.potion;

import entity.JobClass;
import ui.GamePanel;

import java.awt.*;

public class HPPotion extends Potion
{
    public HPPotion(GamePanel gp)
    {
        super(gp);
        setValue();
        getPotionImage();
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }

    public void setValue()
    {
        setName("Health Potion");
        setType("Potion");
        setGenre("HP");
        setDescription("A potion that recover 100 HP");
        setPoint(100);
        setBuyPrice(10);
        setSellPrice((int) (getBuyPrice() * 0.5));
        setSellAble(true);
        setCollision(false);
    }

    public void getPotionImage()
    {
        image = setup("/object/16x16/potion/potion 1");
    }
}
