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
    BufferedImage dialogPointer;
    BufferedImage playerHPBar;
    BufferedImage playerMPBar;
    Rectangle[] rectangles;
    public boolean messageOn = false;
    private String message1 = "";
    private String message2 = "";
    private int messageCounter = 0;

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
            playerHPBar = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/health_bar.png")));
            playerMPBar = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/mana_bar.png")));
            dialogPointer = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/dialog_pointer.png")));
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

       g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
       g2.setColor(new Color(216, 208, 176));
       g2.drawString(Integer.toString(gp.plain.monsters[gp.monsterIndex].getLvl()), 312, 74);
       g2.setColor(new Color(64, 64, 64));
       g2.drawString(Integer.toString(gp.plain.monsters[gp.monsterIndex].getLvl()), 310, 72);

       g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
       g2.setColor(new Color(216, 208, 176));
       g2.drawString(gp.plain.monsters[gp.monsterIndex].getName(), 56,74);
       g2.setColor(new Color(64, 64, 64));
       g2.drawString(gp.plain.monsters[gp.monsterIndex].getName(), 54,72);

       double health = (double) gp.plain.monsters[gp.monsterIndex].getHP() / gp.plain.monsters[gp.monsterIndex].getMaxHP();
       int healthBar = (int) (163 * health);

       g2.setColor(new Color(82, 106, 90));
       g2.fillRect(163, 91, 163, 12);
       if(health <= 0.3)
           g2.setColor(new Color(252, 89 , 58));
       else
           g2.setColor(new Color(96, 232 , 208));
       g2.fillRect(163, 91, healthBar, 12);

       if(!messageOn)
       {
           g2.setColor(new Color(95, 94, 110));
           g2.drawString("HP", 82, 642);
           g2.drawString("MP", 82, 712);

           g2.setColor(new Color(236, 239, 244));
           g2.drawString("HP", 80, 640);
           g2.drawString("MP", 80, 710);

           g2.drawImage(playerHPBar, 140, 610, 340, 44, null);
           g2.drawImage(playerMPBar, 140, 680, 340, 44, null);
           health = (double) gp.player.getHP() / gp.player.getMaxHP();
           healthBar = (int) (228 * health);
           double mp = (double) gp.player.getMP() / gp.player.getMaxMP();
           int mpBar = (int) (228 * mp);

           if(healthBar != 0)
           {
               g2.setColor(new Color(208, 70, 72));
               g2.fillRect(196 , 622, healthBar, 19);
               g2.setColor(new Color(210, 170, 153));
               g2.fillRect(199 , 622, healthBar - 3, 9);
           }
           else
           {
               g2.setColor(new Color(133, 149, 161));
               g2.fillRect(199 , 622, healthBar - 3, 9);
           }

           if(mpBar != 0)
           {
               g2.setColor(new Color(89, 125, 206));
               g2.fillRect(196 , 692, mpBar, 19);
               g2.setColor(new Color(109, 194, 202));
               g2.fillRect(199 , 692, mpBar - 3, 9);
           }
           else
           {
               g2.setColor(new Color(133, 149, 161));
               g2.fillRect(199 , 692, mpBar - 3, 9);
           }
       }
       else
       {
           attributes.put(TextAttribute.TRACKING, 0);
           g2.setFont(g2.getFont().deriveFont(attributes));

           g2.setColor(new Color(95, 94, 110));
           g2.drawString(message1, 70, 654);
           g2.drawString(message2, 70, 702);

           g2.setColor(new Color(236, 239, 244));
           g2.drawString(message1, 70, 652);
           g2.drawString(message2, 70, 700);
           messageCounter++;
           if(messageCounter < 15)
               g2.drawImage(dialogPointer, 490, 710, 18, 11, null);
           else if(messageCounter > 30)
               messageCounter = 0;
       }
   }

    public void showMessage(String text, int index)
    {
        if(index == 1)
            message1 = text;
        else
            message2 = text;
        messageOn = true;
    }
}