package ui;

import entity.JobClass;
import state.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UI
{
    GamePanel gp;
    Graphics2D g2;

    BufferedImage imageEquip;
    BufferedImage imagePlayer;
    BufferedImage dialogPointer;

    public static Font maruMonica;
    public static Font pokemon;

    public int homeState = 0;
    public int titleState = 1;
    public int chooseState = 2;
    public int battleState = 3;
    public int inventoryState = 4;
    public int buyMerchantState = 5;
    public int startScreenState = homeState;
    public State[] states = new State[6];

    public int merchantState = 0;
    public int buyState = 1;
    public int sellState = 2;
    public int buyingThingState = 3;
    public int confirmationState = 4;
    public int selectionMerchantState = merchantState;
    public boolean buying = false;

    public int timeTransition = 255;
    public int countDown = 0;
    public int commandNum = 0;
    public int messageCounter = 0;
    public String currentDialogue = "";
    public boolean endDialogue = false;
    public boolean notCompatibleItemOn = false;
    public boolean inventoryFullMessageOn = false;
    public boolean dieAnimationOn = false;

    public UI(GamePanel gp)
    {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getClass().getResourceAsStream("/font/Pokemon.ttf");
            pokemon = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        getUIImage();
        setState();
    }

    public void setState()
    {
        states[homeState] = new HomeState(gp);
        states[titleState] = new TitleState(gp);
        states[chooseState] = new ChooseState(gp);
        states[battleState] = new BattleState(gp);
        states[inventoryState] = new InventoryState(gp);
        states[buyMerchantState] = new BuyState(gp);
    }

    public void getUIImage()
    {
        try
        {
            imageEquip = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/equip_ui.png")));
            imagePlayer = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/player_ui.png")));
            dialogPointer = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/dialog_pointer.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        if(gp.gameState == gp.startState)
            states[startScreenState].draw(g2);
        else if(gp.gameState == gp.battleState)
            states[battleState].draw(g2);
        else if(gp.gameState == gp.playState)
            drawPlayUI();
        else if(gp.gameState == gp.pauseState)
            drawPauseScreen();
        else if(gp.gameState == gp.chooseMapState)
            drawChooseMapScreen();
        else if(gp.gameState == gp.inventoryState)
        {
            drawPlayUI();
            ((InventoryState)states[inventoryState]).setSellState(false);
            states[inventoryState].draw(g2);
        }
        else if(gp.gameState == gp.dialogueState)
            drawDialogueScreen();
        else if(gp.gameState == gp.merchantState)
        {
            drawPlayUI();
            if(selectionMerchantState == merchantState)
                drawMerchantScreen();
            else if(selectionMerchantState == buyState)
                drawBuyOptionScreen();
            else if(selectionMerchantState == sellState)
            {
                ((InventoryState)states[inventoryState]).setSellState(true);
                states[inventoryState].draw(g2);
            }
            else if(selectionMerchantState == buyingThingState)
                states[buyMerchantState].draw(g2);
            else if(selectionMerchantState == confirmationState)
            {
                if(buying)
                {
                    states[buyMerchantState].draw(g2);
                    if(selectionMerchantState == confirmationState)
                        drawConfirmationScreen(false);
                }
                else
                {
                    ((InventoryState)states[inventoryState]).setSellState(true);
                    states[inventoryState].draw(g2);
                    if(selectionMerchantState == confirmationState)
                        drawConfirmationScreen(true);
                }
            }
        }
        else if(gp.gameState == gp.transitionState)
            drawTransition();
    }

    public void drawChooseMapScreen()
    {
        String mapOption = "PLAIN\nDUNGEON\nCASTLE\nSNOW\nBACK";
        int i = 0;
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = (int) (gp.tileSize * 5);
        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        for (String line: mapOption.split("\n"))
        {
            g2.drawString(line, x, y);
            if(commandNum == i)
                g2.drawString(">", x - 15, y);
            i++;
            if(i == 4)
                y += 80;
            else
                y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height)
    {
        g2.setColor(new Color(0, 0, 0, 215));
        g2.fillRoundRect(x, y, width, height, 35, 35);
        g2.setColor(new Color(255, 255, 255));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawPlayUI()
    {
        g2.drawImage(imagePlayer, 10,20, 200, 62, null);
        g2.drawImage(imageEquip, 10, 85, 130, 48, null);

        if(gp.player.getWeapon() != null)
            g2.drawImage(gp.player.getWeapon().image, 33, 92, 32, 32, null);

        g2.setFont(pokemon);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        g2.drawString("Level    " + gp.player.getLvl(), 60, 18);

        double health = (double) gp.player.getHP() / gp.player.getMaxHP();
        int healthBar = (int) (102 * health);
        double mp = (double) gp.player.getMP() / gp.player.getMaxMP();
        int mpBar = (int) (102 * mp);
        double exp = (double) gp.player.getEXP() / gp.player.getMaxEXP();
        int expBar = (int) (102 * exp);

        if(healthBar != 0)
        {
            g2.setColor(new Color(208, 70, 72));
            g2.fillRect(82 , 28, healthBar, 8);
            g2.setColor(new Color(210, 170, 153));
            g2.fillRect(84 , 28, healthBar - 2, 4);
        }
        else
        {
            g2.setColor(new Color(133, 149, 161));
            g2.fillRect(84 , 28, healthBar - 2, 4);
        }

        if(mpBar != 0)
        {
            g2.setColor(new Color(109, 170, 44));
            g2.fillRect(82 , 47, mpBar, 8);
            g2.setColor(new Color(218, 212, 94));
            g2.fillRect(84 , 47, mpBar - 2, 4);
        }
        else
        {
            g2.setColor(new Color(133, 149, 161));
            g2.fillRect(84 , 47, healthBar - 2, 4);
        }

        if(expBar != 0)
        {
            g2.setColor(new Color(89, 125, 206));
            g2.fillRect(82 , 67, expBar, 8);
            g2.setColor(new Color(109, 194, 202));
            g2.fillRect(84 , 67, expBar - 2, 4);
        }
        else
        {
            g2.setColor(new Color(133, 149, 161));
            g2.fillRect(84 , 67, healthBar - 2, 4);
        }
        if(gp.player.jobClass == JobClass.PALADIN)
            g2.drawImage(gp.player.down2, 18, 23, 48, 48, null);
        else
            g2.drawImage(gp.player.down2, 22, 23, 48, 48, null);
    }

    public void drawPauseScreen()
    {
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
        g2.setFont(pokemon);
    }

    public void drawDialogueScreen()
    {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        x += 30;
        y += gp.tileSize;
        g2.setFont(pokemon);
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.setFont(g2.getFont().deriveFont(attributes));
        for (String line: currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += 40;
        }

        messageCounter++;
        if(messageCounter < 15)
            g2.drawImage(dialogPointer, 860, 260, 18, 11, null);
        else if(messageCounter > 30)
            messageCounter = 0;
    }

    public void drawMerchantScreen()
    {
        String mapOption = "BUY\nSELL\nBACK";

        int i = 0;
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = (int) (gp.tileSize * 5);
        drawSubWindow(x, y, width, height);

        x += 50;
        y += gp.tileSize;

        g2.setFont(pokemon);
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.setFont(g2.getFont().deriveFont(attributes));

        g2.drawString("What do you want to do ?", x, y);

        y += 60;

        for (String line: mapOption.split("\n"))
        {
            g2.drawString(line, x, y);
            if(commandNum == i)
                g2.drawString(">", x - 20, y);
            i++;
            if(i == 2)
                y += 80;
            else
                y += 40;
        }
    }

    public void drawBuyOptionScreen()
    {
        String mapOption = "WEAPON\nARMOR\nPOTION\nBACK";

        int i = 0;
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = (int) (gp.tileSize * 5);
        drawSubWindow(x, y, width, height);

        x += 50;
        y += gp.tileSize;

        g2.setFont(pokemon);
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.setFont(g2.getFont().deriveFont(attributes));

        g2.drawString("What do you want to buy ?", x, y);

        y += 60;

        for (String line: mapOption.split("\n"))
        {
            g2.drawString(line, x, y);
            if(commandNum == i)
                g2.drawString(">", x - 20, y);
            i++;
            if(i == 3)
                y += 80;
            else
                y += 40;
        }
    }

    public void drawConfirmationScreen(boolean isSell)
    {
        String mapOption = "YES\nNO";

        int i = 0;
        int x = 322;
        int y = 278;
        int width = 384;
        int height = 140;

        g2.setColor(new Color(214, 154, 77));
        g2.fillRect(x, y, width, height);

        g2.setColor(new Color(173, 124, 71));
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(x - 2, y - 2, width + 3, height + 3);

        g2.setColor(Color.WHITE);
        g2.setFont(pokemon);
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 29F));
        g2.setFont(g2.getFont().deriveFont(attributes));

        if(isSell)
            g2.drawString("Do you want to sell this item ?", x + 16, y + 35);
        else
            g2.drawString("Do you want to buy this item ?", x + 19, y + 35);

        width = 90;
        height = 30;
        x += 75;
        y += 65;

        g2.setColor(new Color(28, 153, 131));
        g2.fillRect(x, y, width, height);

        if(commandNum == 0)
            g2.setColor(Color.WHITE);
        else
            g2.setColor(new Color(32, 106, 98));
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(x - 2, y - 2, width + 3, height + 3);

        x += width + 60;

        g2.setColor(new Color(189, 81, 90));
        g2.fillRect(x, y, width, height);

        if(commandNum == 1)
            g2.setColor(Color.WHITE);
        else
            g2.setColor(new Color(152, 67, 80));
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(x - 2, y - 2, width + 3, height + 3);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 33F));
        y += 23;
        x = 419;
        for (String line: mapOption.split("\n"))
        {
            g2.drawString(line, x, y);
            i++;
            x += width + 69;
        }
    }

    public void drawTransition()
    {
        g2.setColor(new Color(0, 0, 0, timeTransition));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        countDown++;
        if(countDown >= 30)
            timeTransition -=5;
        switch (commandNum) {
            case 0 -> {
                gp.adjustWorldWidth(gp.plain);
                gp.adjustWorldHeight(gp.plain);
                gp.setMapPick(gp.plain);
                gp.player.setXAndY(gp.plain);
            }
            case 1 -> {
                gp.adjustWorldWidth(gp.dungeon);
                gp.adjustWorldHeight(gp.dungeon);
                gp.setMapPick(gp.dungeon);
                gp.player.setXAndY(gp.dungeon);
            }
            case 2 -> {
                gp.adjustWorldWidth(gp.castle);
                gp.adjustWorldHeight(gp.castle);
                gp.setMapPick(gp.castle);
                gp.player.setXAndY(gp.castle);
            }
            case 3 -> {
                gp.adjustWorldWidth(gp.snow);
                gp.adjustWorldHeight(gp.snow);
                gp.setMapPick(gp.snow);
                gp.player.setXAndY(gp.snow);
            }
        }
        if(timeTransition <= 0)
        {
            gp.gameState = gp.playState;
            switch (commandNum) {
                case 0 -> gp.playMusic(1);
                case 1 -> gp.playMusic(2);
                case 2 -> gp.playMusic(3);
                case 3 -> gp.playMusic(4);
            }
            commandNum = 0;
            timeTransition = 255;
            countDown = 0;
        }
    }

    public int getXCenteredText(String text)
    {
        return ((gp.screenWidth / 2) - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2));
    }
}
