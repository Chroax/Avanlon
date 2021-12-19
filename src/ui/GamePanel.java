package ui;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    // Default setting for the screen and tile size
    private final int oriTileSize = 16;
    private final int scale = 3;
    private final int tileSize = oriTileSize * scale;
    private final int maxScreenCol = 12;
    private final int maxScreenRow = 16;
    private final int screenWidth = tileSize * maxScreenRow;
    private final int screenHeight = tileSize * maxScreenCol;

    // Default setting for world in game
    private int maxWorldCol;
    private int getMaxWorldRow;
    private int worldWidth;
    private int worldHeight;

    // FPS in Game
    private final int FPS = 60;

    // Thread in game (Function as time in game)
    Thread gameThread;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    @Override
    public void run()
    {

    }
}
