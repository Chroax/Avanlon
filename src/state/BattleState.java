package state;

import ui.GamePanel;
import ui.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BattleState extends State
{
    BufferedImage pointer;
    Rectangle[] rectangles;

    public BattleState(GamePanel gp)
    {
        super(gp);
        rectangles = new Rectangle[4];

        imageScreen = new BufferedImage[4];
        setOptionMenu();
        setRectangles();
        getBattleImage();
    }

    public void setOptionMenu()
    {
        optionMenu = new String[4];
        optionMenu[0] = "ATTACK";
        optionMenu[1] = "INVENTORY";
        optionMenu[2] = "RUN";
        optionMenu[3] = "STATUS";
    }

    public void setRectangles()
    {
        int width = 216, height = 64, gap = 10, x = 550, y = 600;
        rectangles[0] = new Rectangle(x, y, width, height);
        rectangles[1] = new Rectangle(x + width, y, width, height);
        rectangles[2] = new Rectangle(x, y + height + gap, width, height);
        rectangles[3] = new Rectangle(x + width, y + height + gap, width, height);
    }

    public void getBattleImage()
    {
        try
        {
            pointer = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/pointer2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }

        imageScreen[0] = setup("/screen/plain_battle", gp.screenWidth, gp.screenHeight);
        imageScreen[1] = setup("/screen/plain_battle", gp.screenWidth, gp.screenHeight);
   }

   @Override
   public void draw(Graphics2D g2)
   {
       this.g2 = g2;

       g2.drawImage(imageScreen[gp.getMapPick()], 0, 0, null);

       g2.setFont(UI.pokemon);
       g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

       Map<TextAttribute, Object> attributes = new HashMap<>();
       attributes.put(TextAttribute.TRACKING, 0.05);
       g2.setFont(g2.getFont().deriveFont(attributes));

       for (int i = 0; i < optionMenu.length; i++)
       {
           g2.setColor(new Color(208, 208, 200));
           g2.drawString(optionMenu[i], rectangles[i].x + 42, rectangles[i].y + 44);
           g2.setColor(new Color(72, 72, 72));
           g2.drawString(optionMenu[i], rectangles[i].x + 40, rectangles[i].y + 42);
           if(i == commandNum)
               g2.drawImage(pointer, rectangles[i].x, rectangles[i].y + 13, 24, 40, null);
       }

       if(!Objects.equals(gp.plain.monsters[gp.monsterIndex].getType(), "Boss"))
       {
           if(gp.plain.monsters[gp.monsterIndex].getImgWidth() == gp.plain.monsters[gp.monsterIndex].getImgHeight())
               g2.drawImage(gp.plain.monsters[gp.monsterIndex].down2, 600, 270, 192, 192, null);
           else
               g2.drawImage(gp.plain.monsters[gp.monsterIndex].down2, 620, 270, 144, 192, null);
       }
       else
       {
           int width = 0;
           int height = 0;
           int x = 0;
           int y = 0;
           switch (gp.plain.monsters[gp.monsterIndex].getName())
           {
               case "Dragon" -> {
                   width = 576;
                   height = 384;
                   y = 72;
                   x = 400;
               }
               case "Dragon Lord" -> {
                   width = 560;
                   height = 400;
                   y = 80;
                   x = 410;
               }
               case "Gold Dragon", "Behemoth" -> {
                   width = 320;
                   height = 448;
                   y = 50;
                   x = 520;
               }
               case "Black Demon", "Fire Demon", "Purple Demon", "Leaf Demon" -> {
                   width = 448;
                   height = 448;
                   y = 50;
                   x = 450;
               }
           }
           g2.drawImage(gp.plain.monsters[gp.monsterIndex].down2, x, y, width, height, null);
       }
   }
}