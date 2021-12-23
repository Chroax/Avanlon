package object.armor;

import entity.JobClass;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ToyArmor extends Armor
{
    public ToyArmor(GamePanel gp)
    {
        super(gp);
        setValue();
        getWeaponImage();
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }

    public void setValue()
    {
        setName("Toy Armor");
        setType("Armor");
        setDescription("Ya nanti lah isi sendiri\nYang penting ada dulu aja");
        setSpd(-2);
        setPhyDef(15);
        setMagDef(15);
        setBuyPrice(200);
        setSellPrice((int) (getBuyPrice() * 0.5));
        setSellAble(true);
        setJobClass(JobClass.PALADIN);
        setCollision(false);
    }

    public void getWeaponImage()
    {
        image = setup("/object/64x64/armor/armor 4");
    }
}
