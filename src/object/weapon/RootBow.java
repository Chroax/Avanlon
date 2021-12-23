package object.weapon;

import entity.JobClass;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class RootBow extends Weapon
{

    public RootBow(GamePanel gp)
    {
        super(gp);
        setValue();
        getWeaponImage();
        solidArea  = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }

    public void setValue()
    {
        setName("Root Bow");
        setType("Weapon");
        setDescription("Ya nanti lah isi sendiri\nYang penting ada dulu aja");
        setSpd(-2);
        setPhyDamage(15);
        setMagDamage(15);
        setBuyPrice(200);
        setSellPrice((int) (getBuyPrice() * 0.5));
        setSellAble(true);
        setJobClass(JobClass.ARCHER);
        setCollision(false);
    }

    public void getWeaponImage()
    {
        image = setup("/object/32x32/weapon/bow/bow 10");
    }
}
