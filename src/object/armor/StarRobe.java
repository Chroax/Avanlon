package object.armor;

import entity.JobClass;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class StarRobe extends Armor
{
    public StarRobe(GamePanel gp)
    {
        super(gp);
        setValue();
        getWeaponImage();
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }

    public void setValue()
    {
        setName("Star Robe");
        setType("Armor");
        setDescription("Ya nanti lah isi sendiri\nYang penting ada dulu aja");
        setSpd(-2);
        setPhyDef(15);
        setMagDef(15);
        setBuyPrice(200);
        setSellPrice((int) (getBuyPrice() * 0.5));
        setSellAble(true);
        setJobClass(JobClass.WIZARD);
        setCollision(false);
    }

    public void getWeaponImage()
    {
        image = setup("/object/64x64/armor/armor 12");
    }
}
