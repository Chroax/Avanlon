package state;

import ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ChooseState extends State
{
    BufferedImage pointer;
    BufferedImage[] character = new BufferedImage[6];
    public boolean chooseGender = true;
    public boolean gender = false;
    private String[] genderMenu;

    public ChooseState(GamePanel gp)
    {
        super(gp);
        imageScreen = new BufferedImage[1];
        setOptionMenu();
        getChooseImage();
    }
    public void setOptionMenu()
    {
        optionMenu = new String[4];
        optionMenu[0] = "PALADIN";
        optionMenu[1] = "WIZARD";
        optionMenu[2] = "ARCHER";
        optionMenu[3] = "BACK";

        genderMenu = new String[3];
        genderMenu[0] = "MALE";
        genderMenu[1] = "FEMALE";
        genderMenu[2] = "BACK";
    }
    public void getChooseImage()
    {
        imageScreen[0] = setup("/screen/title_screen", gp.screenWidth, gp.screenHeight);
        pointer = setup("/ui/pointer");

        String txt = "/character/64x64/warrior/warrior";
        character[0] = setup(txt + " 2");
        txt = "/character/64x64/femalewarrior/female_warrior";
        character[1] = setup(txt + " 2");

        txt = "/character/32x48/magician/magician";
        character[2] = setup(txt + " 2", 1.5, true);
        txt = "/character/32x48/femalemagician/female_magician";
        character[3] = setup(txt + " 2", 1.5, true);

        txt = "/character/32x48/archer/archer";
        character[4] = setup(txt + " 2", 1.5, true);
        txt = "/character/32x48/femalearcher/female_archer";
        character[5] = setup(txt + " 2", 1.5, true);
    }
    @Override
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

        g2.drawImage(imageScreen[0], 0, 0, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));
        int[] x = {0}, y = {0};
        if(chooseGender)
        {
            text = "Choose your gender !";
            setTitleText(x, y);

            for (int i = 0; i < genderMenu.length; i++)
            {
                if(i == commandNum)
                {
                    g2.setColor(Color.GREEN);
                    g2.drawString(genderMenu[i], x[0], y[0]);
                    g2.drawImage(pointer, (int) (g2.getFontMetrics().getStringBounds(optionMenu[i], g2).getWidth() + gp.tileSize * 2.2), y[0] - gp.tileSize / 2, null);
                }
                else
                {
                    g2.setColor(Color.white);
                    g2.drawString(genderMenu[i], x[0], y[0]);
                }
                if(i + 1 == genderMenu.length)
                    y[0] += gp.tileSize * 2;
                else
                    y[0] += gp.tileSize;
            }
        }
        else
        {
            text = "Select your class !";
            setTitleText(x, y);
            int imgCounter = 0;

            for (int i = 0; i < optionMenu.length; i++)
            {
                if(i == commandNum)
                {
                    g2.setColor(Color.GREEN);
                    g2.drawString(optionMenu[i], x[0], y[0]);
                    g2.drawImage(pointer, (int) (g2.getFontMetrics().getStringBounds(optionMenu[i], g2).getWidth() + gp.tileSize * 2.2), y[0] - gp.tileSize / 2, null);
                    if(i + 1 != optionMenu.length)
                    {
                        if(gender)
                        {
                            if(i == 1)
                                g2.drawImage(character[imgCounter + i + 1], gp.tileSize * 7, (int) (gp.tileSize * 4.5), gp.tileSize * 3, gp.tileSize * 3, null);
                            else
                                g2.drawImage(character[imgCounter + i + 1], gp.tileSize * 7 + 10, (int) (gp.tileSize * 4.5), gp.tileSize * 3, gp.tileSize * 3, null);
                        }
                        else
                        {
                            if(i == 0)
                                g2.drawImage(character[imgCounter + i], gp.tileSize * 7, (int) (gp.tileSize * 4.5), gp.tileSize * 3, gp.tileSize * 3, null);
                            else
                                g2.drawImage(character[imgCounter + i], gp.tileSize * 7 + 10, (int) (gp.tileSize * 4.5), gp.tileSize * 3, gp.tileSize * 3, null);
                        }
                    }
                }
                else
                {
                    g2.setColor(Color.white);
                    g2.drawString(optionMenu[i], x[0], y[0]);
                }
                if(i + 1 == optionMenu.length)
                    y[0] += gp.tileSize * 2;
                else
                    y[0] += gp.tileSize;
                imgCounter++;
            }
        }
    }

    public void setTitleText(int[] x, int[] y)
    {
        x[0] = getXCenteredText(text);
        y[0] = gp.tileSize * 2;

        // Main Color
        g2.setColor(Color.white);
        g2.drawString(text, x[0], y[0]);

        // Option Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        x[0] = gp.tileSize * 2;
        y[0] += gp.tileSize * 3;
    }

}
