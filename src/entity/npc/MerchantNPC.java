package entity.npc;

import entity.Entity;
import ui.GamePanel;

import java.awt.*;
import java.util.Random;

public class MerchantNPC extends Entity
{
    public MerchantNPC(GamePanel gp)
    {
        super(gp);

        setName("Merchant");
        setDirection("down");
        setImgWidth(32);
        setImgHeight(48);
        dialogues = new String[1][3];
        solidArea = new Rectangle(10, 32, 32, 32);
        setSolidAreaDefaultX(solidArea.x);
        setSolidAreaDefaultY(solidArea.y);

        getMerchantImage();
        setDialogue();
    }

    public void getMerchantImage()
    {
        String txt = "/npc/32x48/merchant/merchant";
        down1 = setup(txt + " 1", 1.5, true);
        down2 = setup(txt + " 2", 1.5, true);
        down3 = setup(txt + " 3", 1.5, true);
        left1 = setup(txt + " 4", 1.5, true);
        left2 = setup(txt + " 5", 1.5, true);
        left3 = setup(txt + " 6", 1.5, true);
        right1 = setup(txt + " 7", 1.5, true);
        right2 = setup(txt + " 8", 1.5, true);
        right3 = setup(txt + " 9", 1.5, true);
        up1 = setup(txt + " 10", 1.5, true);
        up2 = setup(txt + " 11", 1.5, true);
        up3 = setup(txt + " 12", 1.5, true);
    }

    @Override
    public void setAction()
    {}

    public void setDialogue()
    {
        dialogues[0][0] = "Hello, adventure";
        dialogues[0][1] = "Im a merchant";
        dialogues[0][2] = "What is a merchant ?";
        dialogues[0][3] = "Merchant is a place where adventurers\nmake transactions to buy and sell items";
    }

    public void speak()
    {
        super.speak();
    }
}
