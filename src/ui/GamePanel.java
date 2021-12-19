package ui;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    // Default setting for the screen and tile size
    private final int oriTileSize = 16;
    private final int scale = 3;
    public final int tileSize = oriTileSize * scale;
    private final int maxScreenCol = 12;
    private final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenRow;
    public final int screenHeight = tileSize * maxScreenCol;

    // Default setting for world in game
    private int maxWorldCol;
    private int getMaxWorldRow;
    private int worldWidth;
    private int worldHeight;

    // FPS in Game
    private final int FPS = 60;

    // Thread in game (Function as time in game)
    Thread gameThread;

    // Game state
    public int gameState;
    public final int playState = 1;

    // ui.KeyHandler
    KeyHandler keyH = new KeyHandler(this);

    // Map pick
    private int mapPick;

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
        gameState = playState;
        mapPick = 0;
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

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

    }
}
