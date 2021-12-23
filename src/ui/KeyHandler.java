package ui;

import entity.JobClass;
import state.BattleState;
import state.BuyState;
import state.ChooseState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public boolean stillBattle = false;
    GamePanel gp;

    public KeyHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(gp.gameState == gp.startState)
        {
            if(gp.ui.startScreenState == gp.ui.homeState)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    gp.playSE(9);
                    gp.ui.startScreenState = gp.ui.titleState;
                }
            }
            else if(gp.ui.startScreenState == gp.ui.titleState)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_W -> {
                        gp.playSE(18);
                        gp.ui.states[gp.ui.titleState].commandNum--;
                        if (gp.ui.states[gp.ui.titleState].commandNum < 0)
                            gp.ui.states[gp.ui.titleState].commandNum = 2;
                    }
                    case KeyEvent.VK_S -> {
                        gp.playSE(18);
                        gp.ui.states[gp.ui.titleState].commandNum++;
                        if (gp.ui.states[gp.ui.titleState].commandNum > 2)
                            gp.ui.states[gp.ui.titleState].commandNum = 0;
                    }
                    case KeyEvent.VK_ENTER -> {
                        gp.playSE(9);
                        switch (gp.ui.states[gp.ui.titleState].commandNum)
                        {
                            case 0 -> gp.ui.startScreenState = gp.ui.chooseState;
                            case 1 -> {}
                            case 2 -> System.exit(0);
                        }
                    }
                }
            }
            else if(gp.ui.startScreenState == gp.ui.chooseState)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_W -> {
                        gp.playSE(18);
                        gp.ui.states[gp.ui.chooseState].commandNum--;
                        if (gp.ui.states[gp.ui.chooseState].commandNum < 0)
                        {
                            if(((ChooseState)gp.ui.states[gp.ui.chooseState]).chooseGender)
                                gp.ui.states[gp.ui.chooseState].commandNum = 2;
                            else
                                gp.ui.states[gp.ui.chooseState].commandNum = 3;
                        }
                    }
                    case KeyEvent.VK_S -> {
                        gp.playSE(18);
                        gp.ui.states[gp.ui.chooseState].commandNum++;
                        if(((ChooseState)gp.ui.states[gp.ui.chooseState]).chooseGender)
                        {
                            if (gp.ui.states[gp.ui.chooseState].commandNum > 2)
                                gp.ui.states[gp.ui.chooseState].commandNum = 0;
                        }
                        else
                        {
                            if (gp.ui.states[gp.ui.chooseState].commandNum > 3)
                                gp.ui.states[gp.ui.chooseState].commandNum = 0;
                        }
                    }
                    case KeyEvent.VK_ENTER -> {
                        gp.playSE(9);
                        if (((ChooseState)gp.ui.states[gp.ui.chooseState]).chooseGender)
                        {
                            switch (gp.ui.states[gp.ui.chooseState].commandNum)
                            {
                                case 0 -> {
                                    ((ChooseState)gp.ui.states[gp.ui.chooseState]).gender = false;
                                    ((ChooseState)gp.ui.states[gp.ui.chooseState]).chooseGender = false;
                                }
                                case 1 -> {
                                    gp.ui.states[gp.ui.chooseState].commandNum = 0;
                                    ((ChooseState)gp.ui.states[gp.ui.chooseState]).gender = true;
                                    ((ChooseState)gp.ui.states[gp.ui.chooseState]).chooseGender = false;
                                }
                                case 2 -> {
                                    gp.ui.startScreenState = gp.ui.titleState;
                                    gp.ui.states[gp.ui.chooseState].commandNum = 0;
                                    gp.ui.states[gp.ui.titleState].commandNum = 0;
                                }
                            }
                        }
                        else
                        {
                            switch (gp.ui.states[gp.ui.chooseState].commandNum)
                            {
                                case 0 -> gp.generatePlayer(JobClass.PALADIN, ((ChooseState)gp.ui.states[gp.ui.chooseState]).gender);
                                case 1 -> gp.generatePlayer(JobClass.WIZARD, ((ChooseState)gp.ui.states[gp.ui.chooseState]).gender);
                                case 2 -> gp.generatePlayer(JobClass.ARCHER, ((ChooseState)gp.ui.states[gp.ui.chooseState]).gender);
                                case 3 -> {
                                    gp.ui.states[gp.ui.chooseState].commandNum = 0;
                                    ((ChooseState)gp.ui.states[gp.ui.chooseState]).chooseGender = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        // Key Handling at play state
        else if(gp.gameState == gp.playState)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_W -> upPressed = true;
                case KeyEvent.VK_S -> downPressed = true;
                case KeyEvent.VK_A -> leftPressed = true;
                case KeyEvent.VK_D -> rightPressed = true;
                case KeyEvent.VK_ENTER -> enterPressed = true;
                case KeyEvent.VK_P -> {
                    gp.returnState = gp.gameState;
                    gp.gameState = gp.pauseState;
                }
                case KeyEvent.VK_I -> gp.gameState = gp.inventoryState;
                case KeyEvent.VK_U -> gp.player.unEquipWeapon();
            }
        }
        else if(gp.gameState == gp.battleState)
        {
            stillBattle = true;
            if(((BattleState)gp.ui.states[gp.ui.battleState]).messageOn)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    switch (((BattleState)gp.ui.states[gp.ui.battleState]).status)
                    {
                        case "ATTACK" -> gp.playSE(9);
                        case "WIN" -> {
                            ((BattleState)gp.ui.states[gp.ui.battleState]).status = "ATTACK";
                            gp.gameState = gp.playState;
                            gp.map[gp.getMapPick()].monsters[gp.monsterIndex].generateMonster();
                            gp.stopMusic();
                            gp.playMusic(gp.getMapPick());
                        }
                        case "RUN" -> {
                            gp.gameState = gp.playState;
                            gp.stopMusic();
                            gp.playMusic(gp.getMapPick());
                            gp.ui.states[gp.ui.battleState].commandNum = 0;
                            gp.map[gp.getMapPick()].monsters[gp.monsterIndex].generateMonster();
                        }
                    }
                    ((BattleState)gp.ui.states[gp.ui.battleState]).messageOn = false;
                }
            }
            else
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_W, KeyEvent.VK_S -> {
                        gp.playSE(18);
                        if (gp.ui.states[gp.ui.battleState].commandNum == 0)
                            gp.ui.states[gp.ui.battleState].commandNum = 2;
                        else if (gp.ui.states[gp.ui.battleState].commandNum == 1)
                            gp.ui.states[gp.ui.battleState].commandNum = 3;
                        else if (gp.ui.states[gp.ui.battleState].commandNum == 2)
                            gp.ui.states[gp.ui.battleState].commandNum = 0;
                        else if (gp.ui.states[gp.ui.battleState].commandNum == 3)
                            gp.ui.states[gp.ui.battleState].commandNum = 1;
                    }
                    case KeyEvent.VK_A, KeyEvent.VK_D ->{
                        gp.playSE(18);
                        if (gp.ui.states[gp.ui.battleState].commandNum == 0)
                            gp.ui.states[gp.ui.battleState].commandNum = 1;
                        else if (gp.ui.states[gp.ui.battleState].commandNum == 1)
                            gp.ui.states[gp.ui.battleState].commandNum = 0;
                        else if (gp.ui.states[gp.ui.battleState].commandNum == 2)
                            gp.ui.states[gp.ui.battleState].commandNum = 3;
                        else if (gp.ui.states[gp.ui.battleState].commandNum == 3)
                            gp.ui.states[gp.ui.battleState].commandNum = 2;
                    }
                    case KeyEvent.VK_ENTER -> {
                        switch (gp.ui.states[gp.ui.battleState].commandNum)
                        {
                            case 0 -> {
                                {
                                    gp.player.attack(gp.map[gp.getMapPick()].monsters[gp.monsterIndex], 1);
                                    gp.map[gp.getMapPick()].monsters[gp.monsterIndex].attack(gp.player, 2);
                                    if(gp.map[gp.getMapPick()].monsters[gp.monsterIndex].getHP() <= 0)
                                    {
                                        stillBattle = false;
                                        gp.player.defeatMonster(gp.map[gp.getMapPick()].monsters[gp.monsterIndex]);
                                        gp.stopMusic();
                                        ((BattleState)gp.ui.states[gp.ui.battleState]).showMessage("", 0, "WIN");
                                    }
                                    else if(gp.player.getHP() <= 0)
                                    {
                                        stillBattle = false;
                                        gp.ui.dieAnimationOn = true;
                                        gp.gameState = gp.playState;
                                        gp.player.respawn();
                                        gp.player.resetStat();
                                        gp.map[gp.getMapPick()].monsters[gp.monsterIndex].generateMonster();
                                    }
                                    if(stillBattle)
                                        gp.playSE(9);
                                }
                            }
                            case 1 -> System.out.println("DO OPEN INVENTORY");
                            case 2 -> {
                                gp.stopMusic();
                                gp.playSE(15);
                                ((BattleState)gp.ui.states[gp.ui.battleState]).showMessage("", 0, "RUN");
                            }
                            case 3 -> System.out.println("DO SHOW STATUS");
                        }
                    }
                    case KeyEvent.VK_P -> {
                        gp.returnState = gp.gameState;
                        gp.gameState = gp.pauseState;
                    }
                }
            }
        }
        else if(gp.gameState == gp.pauseState)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_P -> gp.gameState = gp.returnState;
            }
        }
        else if(gp.gameState == gp.chooseMapState) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> {
                    gp.playSE(18);
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0)
                        gp.ui.commandNum = 4;
                }
                case KeyEvent.VK_S -> {
                    gp.playSE(18);
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 4)
                        gp.ui.commandNum = 0;
                }
                case KeyEvent.VK_ENTER -> {
                    switch (gp.ui.commandNum) {
                        case 0,1,2,3 -> {
                            gp.stopMusic();
                            gp.playSE(16);
                            gp.gameState = gp.transitionState;
                        }
                        case 4 -> {
                            gp.playSE(9);
                            gp.ui.commandNum = 0;
                            gp.gameState = gp.playState;
                        }
                    }
                }
            }
        }
        else if(gp.gameState == gp.inventoryState)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_W -> {
                    gp.playSE(18);
                    gp.ui.states[gp.ui.inventoryState].commandNum -= 8;
                    if(gp.ui.states[gp.ui.inventoryState].commandNum < 0)
                        gp.ui.states[gp.ui.inventoryState].commandNum += 32;
                }
                case KeyEvent.VK_S -> {
                    gp.playSE(18);
                    gp.ui.states[gp.ui.inventoryState].commandNum += 8;
                    if(gp.ui.states[gp.ui.inventoryState].commandNum > 31)
                        gp.ui.states[gp.ui.inventoryState].commandNum -= 32;
                }
                case KeyEvent.VK_A -> {
                    gp.playSE(18);
                    gp.ui.states[gp.ui.inventoryState].commandNum -= 1;
                    if((gp.ui.states[gp.ui.inventoryState].commandNum + 1) % 8 == 0 || gp.ui.states[gp.ui.inventoryState].commandNum < 0)
                        gp.ui.states[gp.ui.inventoryState].commandNum += 8;
                }
                case KeyEvent.VK_D -> {
                    gp.playSE(18);
                    gp.ui.states[gp.ui.inventoryState].commandNum += 1;
                    if(gp.ui.states[gp.ui.inventoryState].commandNum % 8 == 0)
                        gp.ui.states[gp.ui.inventoryState].commandNum -= 8;
                }
                case KeyEvent.VK_ENTER -> {
                    gp.playSE(9);
                    gp.player.useItem(gp.ui.states[gp.ui.inventoryState].commandNum, false);
                }
                case KeyEvent.VK_I -> gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.dialogueState)
        {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                gp.playSE(9);
                gp.map[gp.getMapPick()].NPC[gp.npcIndex].speak();
                if(gp.ui.endDialogue)
                    gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.merchantState)
        {
            if(gp.ui.selectionMerchantState == gp.ui.merchantState)
            {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> {
                        gp.playSE(18);
                        gp.ui.commandNum--;
                        if (gp.ui.commandNum < 0)
                            gp.ui.commandNum = 2;
                    }
                    case KeyEvent.VK_S -> {
                        gp.playSE(18);
                        gp.ui.commandNum++;
                        if (gp.ui.commandNum > 2)
                            gp.ui.commandNum = 0;
                    }
                    case KeyEvent.VK_ENTER -> {
                        gp.playSE(9);
                        switch (gp.ui.commandNum) {
                            case 0 -> gp.ui.selectionMerchantState = gp.ui.buyState;
                            case 1 -> {
                                gp.ui.commandNum = 0;
                                gp.ui.selectionMerchantState = gp.ui.sellState;
                            }
                            case 2 -> {
                                gp.ui.commandNum = 0;
                                gp.gameState = gp.playState;
                            }
                        }
                    }
                }
            }
            else if(gp.ui.selectionMerchantState == gp.ui.buyState)
            {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> {
                        gp.playSE(18);
                        gp.ui.commandNum--;
                        if (gp.ui.commandNum < 0)
                            gp.ui.commandNum = 3;
                    }
                    case KeyEvent.VK_S -> {
                        gp.playSE(18);
                        gp.ui.commandNum++;
                        if (gp.ui.commandNum > 3)
                            gp.ui.commandNum = 0;
                    }
                    case KeyEvent.VK_ENTER -> {
                        gp.playSE(9);
                        switch (gp.ui.commandNum) {
                            case 0 -> {
                                gp.ui.selectionMerchantState = gp.ui.buyingThingState;
                                ((BuyState)gp.ui.states[gp.ui.buyMerchantState]).setState("WEAPON");
                            }
                            case 1 -> {
                                gp.ui.selectionMerchantState = gp.ui.buyingThingState;
                                ((BuyState)gp.ui.states[gp.ui.buyMerchantState]).setState("ARMOR");
                            }
                            case 2 -> {
                                gp.ui.selectionMerchantState = gp.ui.buyingThingState;
                                ((BuyState)gp.ui.states[gp.ui.buyMerchantState]).setState("POTION");
                            }
                            case 3 -> {
                                gp.ui.selectionMerchantState = gp.ui.merchantState;
                                gp.ui.commandNum = 0;
                            }
                        }
                    }
                }
            }
            else if(gp.ui.selectionMerchantState == gp.ui.buyingThingState)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_W -> {
                        gp.playSE(18);
                        if(gp.ui.states[gp.ui.buyMerchantState].commandNum >= 0 && gp.ui.states[gp.ui.buyMerchantState].commandNum <= 7)
                            gp.ui.states[gp.ui.buyMerchantState].commandNum = 33;
                        else if(gp.ui.states[gp.ui.buyMerchantState].commandNum == 33)
                            gp.ui.states[gp.ui.buyMerchantState].commandNum = 24;
                        else
                        {
                            gp.ui.states[gp.ui.buyMerchantState].commandNum -= 8;
                            if(gp.ui.states[gp.ui.buyMerchantState].commandNum < 0)
                                gp.ui.states[gp.ui.buyMerchantState].commandNum += 32;
                        }
                    }
                    case KeyEvent.VK_S -> {
                        gp.playSE(18);
                        if(gp.ui.states[gp.ui.buyMerchantState].commandNum >= 24 && gp.ui.states[gp.ui.buyMerchantState].commandNum <= 31)
                            gp.ui.states[gp.ui.buyMerchantState].commandNum = 33;
                        else if(gp.ui.states[gp.ui.buyMerchantState].commandNum == 33)
                            gp.ui.states[gp.ui.buyMerchantState].commandNum = 0;
                        else
                        {
                            gp.ui.states[gp.ui.buyMerchantState].commandNum += 8;
                            if(gp.ui.states[gp.ui.buyMerchantState].commandNum > 31)
                                gp.ui.states[gp.ui.buyMerchantState].commandNum -= 32;
                        }
                    }
                    case KeyEvent.VK_A -> {
                        gp.playSE(18);
                        if(gp.ui.states[gp.ui.buyMerchantState].commandNum != 33)
                        {
                            gp.ui.states[gp.ui.buyMerchantState].commandNum -= 1;
                            if ((gp.ui.states[gp.ui.buyMerchantState].commandNum + 1) % 8 == 0 || gp.ui.states[gp.ui.buyMerchantState].commandNum < 0)
                                gp.ui.states[gp.ui.buyMerchantState].commandNum += 8;
                        }
                    }
                    case KeyEvent.VK_D -> {
                        gp.playSE(18);
                        if(gp.ui.states[gp.ui.buyMerchantState].commandNum != 33)
                        {
                            gp.ui.states[gp.ui.buyMerchantState].commandNum += 1;
                            if(gp.ui.states[gp.ui.buyMerchantState].commandNum % 8 == 0)
                                gp.ui.states[gp.ui.buyMerchantState].commandNum -= 8;
                        }
                    }
                    case KeyEvent.VK_ENTER -> {
                        if(gp.ui.states[gp.ui.buyMerchantState].commandNum != 33)
                        {
                            gp.ui.buying = true;
                            switch (((BuyState)gp.ui.states[gp.ui.buyMerchantState]).getState())
                            {
                                case "WEAPON", "ARMOR" -> {
                                    if(gp.ui.states[gp.ui.buyMerchantState].commandNum < 30)
                                        gp.ui.selectionMerchantState = gp.ui.confirmationState;
                                }
                                case "POTION" -> {
                                    if(gp.ui.states[gp.ui.buyMerchantState].commandNum < 9)
                                        gp.ui.selectionMerchantState = gp.ui.confirmationState;
                                }
                            }
                            gp.ui.commandNum = 0;
                        }
                        else
                        {
                            gp.playSE(9);
                            gp.ui.selectionMerchantState = gp.ui.buyState;
                            gp.ui.states[gp.ui.buyMerchantState].commandNum = 0;
                        }
                    }
                }
            }
            else if(gp.ui.selectionMerchantState == gp.ui.sellState)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_W -> {
                        gp.playSE(18);
                        if(gp.ui.states[gp.ui.inventoryState].commandNum >= 0 && gp.ui.states[gp.ui.inventoryState].commandNum <= 7)
                            gp.ui.states[gp.ui.inventoryState].commandNum = 33;
                        else if(gp.ui.states[gp.ui.inventoryState].commandNum == 33)
                            gp.ui.states[gp.ui.inventoryState].commandNum = 24;
                        else
                        {
                            gp.ui.states[gp.ui.inventoryState].commandNum -= 8;
                            if(gp.ui.states[gp.ui.inventoryState].commandNum < 0)
                                gp.ui.states[gp.ui.inventoryState].commandNum += 32;
                        }
                    }
                    case KeyEvent.VK_S -> {
                        gp.playSE(18);
                        if(gp.ui.states[gp.ui.inventoryState].commandNum >= 24 && gp.ui.states[gp.ui.inventoryState].commandNum <= 31)
                            gp.ui.states[gp.ui.inventoryState].commandNum = 33;
                        else if(gp.ui.states[gp.ui.inventoryState].commandNum == 33)
                            gp.ui.states[gp.ui.inventoryState].commandNum = 0;
                        else
                        {
                            gp.ui.states[gp.ui.inventoryState].commandNum += 8;
                            if(gp.ui.states[gp.ui.inventoryState].commandNum > 31)
                                gp.ui.states[gp.ui.inventoryState].commandNum -= 32;
                        }
                    }
                    case KeyEvent.VK_A -> {
                        gp.playSE(18);
                        if(gp.ui.states[gp.ui.inventoryState].commandNum != 33)
                        {
                            gp.ui.states[gp.ui.inventoryState].commandNum -= 1;
                            if ((gp.ui.states[gp.ui.inventoryState].commandNum + 1) % 8 == 0 || gp.ui.states[gp.ui.inventoryState].commandNum < 0)
                                gp.ui.states[gp.ui.inventoryState].commandNum += 8;
                        }
                    }
                    case KeyEvent.VK_D -> {
                        gp.playSE(18);
                        if(gp.ui.states[gp.ui.inventoryState].commandNum != 33)
                        {
                            gp.ui.states[gp.ui.inventoryState].commandNum += 1;
                            if(gp.ui.states[gp.ui.inventoryState].commandNum % 8 == 0)
                                gp.ui.states[gp.ui.inventoryState].commandNum -= 8;
                        }
                    }
                    case KeyEvent.VK_ENTER -> {
                        if(gp.ui.states[gp.ui.inventoryState].commandNum != 33)
                        {
                            gp.ui.buying = false;
                            if(gp.ui.states[gp.ui.inventoryState].commandNum < gp.player.items.size())
                                gp.ui.selectionMerchantState = gp.ui.confirmationState;
                        }
                        else
                        {
                            gp.playSE(9);
                            gp.ui.selectionMerchantState = gp.ui.merchantState;
                            gp.ui.states[gp.ui.inventoryState].commandNum = 0;
                        }
                    }
                }
            }
            else if(gp.ui.selectionMerchantState == gp.ui.confirmationState)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_D-> {
                        gp.playSE(18);
                        gp.ui.commandNum++;
                        if(gp.ui.commandNum > 1)
                            gp.ui.commandNum = 0;
                    }
                    case KeyEvent.VK_A -> {
                        gp.playSE(18);
                        gp.ui.commandNum--;
                        if(gp.ui.commandNum < 0)
                            gp.ui.commandNum = 1;
                    }
                    case KeyEvent.VK_ENTER -> {
                        if (gp.ui.commandNum == 0)
                        {
                            if (!gp.ui.buying)
                            {
                                gp.player.useItem(gp.ui.states[gp.ui.inventoryState].commandNum, true);
                                gp.playSE(8);
                            }
                            else
                            {
                                switch (((BuyState)gp.ui.states[gp.ui.buyMerchantState]).getState())
                                {
                                    case "WEAPON" -> gp.player.addItem(((BuyState) gp.ui.states[gp.ui.buyMerchantState]).weapons[gp.ui.states[gp.ui.buyMerchantState].commandNum], 1, true);
                                    case "ARMOR" -> gp.player.addItem(((BuyState) gp.ui.states[gp.ui.buyMerchantState]).armors[gp.ui.states[gp.ui.buyMerchantState].commandNum], 1, true);
                                    case "POTION" -> gp.player.addItem(((BuyState) gp.ui.states[gp.ui.buyMerchantState]).potions[gp.ui.states[gp.ui.buyMerchantState].commandNum], 1, true);
                                }
                            }
                        }
                        if(!gp.ui.buying)
                            gp.ui.selectionMerchantState = gp.ui.sellState;
                        else
                            gp.ui.selectionMerchantState = gp.ui.buyingThingState;
                        gp.ui.commandNum = 0;
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
        }
    }
}
