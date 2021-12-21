package ui;

import entity.Player;
import entity.monster.Monster;
import tile.PlainTile;
import util.AssetSetter;
import util.CollisionChecker;
import util.UtilityTool;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    // Default setting for the screen and tile size
    private final int oriTileSize = 16;
    public final int tileSize = oriTileSize * getScale();
    private final int maxScreenCol = 12;
    private final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenRow;
    public final int screenHeight = tileSize * maxScreenCol;

    // Default setting for world in game
    private int maxWorldCol;
    private int maxWorldRow;
    private int worldWidth;
    private int worldHeight;

    // Thread in game (Function as time in game)
    Thread gameThread;

    // ui.KeyHandler
    public KeyHandler keyH = new KeyHandler(this);

    // Utility Tool
    public UtilityTool uTool = new UtilityTool(this);

    // Collision Checker
    public CollisionChecker cChecker = new CollisionChecker(this);

    // Asset setter (Object, monster, npc)
    public AssetSetter aSetter = new AssetSetter(this);

    // UI Handler
    public UI ui = new UI(this);

    // Game state
    public int gameState;
    public int returnState;
    public final int startState = 0;
    public final int playState = 1;
    public final int battleState = 2;
    public final int pauseState = 3;

    // Map pick
    private int mapPick = 1;

    // Map
    public PlainTile plain = new PlainTile(this);

    // Player
    public Player player = new Player(this);

    // Index monster
    public int monsterIndex;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        gameState = startState;
        adjustWorldWidth(mapPick);
        adjustWorldHeight(mapPick);
        aSetter.setMonster();
    }

    public void adjustWorldWidth(int i)
    {
        switch (i)
        {
            case 0,1 -> maxWorldCol = 50;
            case 2 -> maxWorldCol = 66;
            case 3 -> maxWorldCol = 69;
            case 4 -> maxWorldCol = 31;
        }
        worldWidth = tileSize * maxWorldCol;
        mapPick = i;
    }

    public void adjustWorldHeight(int i)
    {
        switch (i)
        {
            case 0 -> maxWorldRow = 38;
            case 1 -> maxWorldRow = 50;
            case 2 -> maxWorldRow = 35;
            case 3 -> maxWorldRow = 40;
            case 4 -> maxWorldRow = 39;
        }
        worldHeight = tileSize * maxWorldRow;
        mapPick = i;
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = 16666666.666666666666666666666667D; // 10^9 ns (1s) divided by FPS (60)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            // Update every delta reach 1 means has passed the draw time interval
            if(delta >= 1)
            {
                update();
                repaint();
                delta -= 1;
            }
        }
    }

    public void update()
    {
        if(gameState == playState)
        {
            player.update();

            for(Monster monster: plain.monsters)
            {
                if(monster != null)
                {
                    monster.update();
                }
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(gameState == startState)
            ui.draw(g2);
        else if(gameState == playState)
        {
            plain.draw(g2);

            for(Monster monster: plain.monsters)
            {
                if(monster != null)
                    monster.draw(g2);
            }

            player.draw(g2);

            ui.draw(g2);
        }
        else if(gameState == battleState || gameState == pauseState)
            ui.draw(g2);

        g2.dispose();
    }

    // Getter and Setter
    public int getScale() { return 4; }
    public int getMaxWorldCol() { return maxWorldCol; }
    public void setMaxWorldCol(int maxWorldCol) { this.maxWorldCol = maxWorldCol; }
    public int getMaxWorldRow() { return maxWorldRow; }
    public void setMaxWorldRow(int maxWorldRow) { this.maxWorldRow = maxWorldRow; }
    public int getWorldWidth() { return worldWidth; }
    public void setWorldWidth(int worldWidth) { this.worldWidth = worldWidth; }
    public int getWorldHeight() { return worldHeight; }
    public void setWorldHeight(int worldHeight) { this.worldHeight = worldHeight; }
    public int getMapPick() {return mapPick;}
    public void setMapPick(int mapPick) {this.mapPick = mapPick;}
}
