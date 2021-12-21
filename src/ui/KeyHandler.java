package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
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
        // Key Handling at play state
        if(gp.gameState == gp.playState)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_W -> upPressed = true;
                case KeyEvent.VK_S -> downPressed = true;
                case KeyEvent.VK_A -> leftPressed = true;
                case KeyEvent.VK_D -> rightPressed = true;
                case KeyEvent.VK_ENTER -> enterPressed = true;
            }
        }
        if(gp.gameState == gp.battleState)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_W, KeyEvent.VK_S -> {
                    if (gp.ui.states[0].commandNum == 0)
                        gp.ui.states[0].commandNum = 2;
                    else if (gp.ui.states[0].commandNum == 1)
                        gp.ui.states[0].commandNum = 3;
                    else if (gp.ui.states[0].commandNum == 2)
                        gp.ui.states[0].commandNum = 0;
                    else if (gp.ui.states[0].commandNum == 3)
                        gp.ui.states[0].commandNum = 1;
                }
                case KeyEvent.VK_A, KeyEvent.VK_D ->{
                    if (gp.ui.states[0].commandNum == 0)
                        gp.ui.states[0].commandNum = 1;
                    else if (gp.ui.states[0].commandNum == 1)
                        gp.ui.states[0].commandNum = 0;
                    else if (gp.ui.states[0].commandNum == 2)
                        gp.ui.states[0].commandNum = 3;
                    else if (gp.ui.states[0].commandNum == 3)
                        gp.ui.states[0].commandNum = 2;
                }
                case KeyEvent.VK_ENTER -> {
                    switch (gp.ui.states[0].commandNum)
                    {
                        case 0 -> System.out.println("DO ATTACK MONSTER");
                        case 1 -> System.out.println("DO OPEN INVENTORY");
                        case 2 -> {
                            gp.gameState = gp.playState;
                            gp.plain.monsters[gp.monsterIndex].generateMonster();
                        }
                        case 3 -> System.out.println("DO SHOW STATUS");
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
