package tile;

import entity.monster.Monster;
import object.SuperObject;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public abstract class TileManager
{
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    protected int col;
    protected int row;
    protected String filePath;
    public Monster[] monsters;
    public SuperObject[] obj;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
    }

    public void getTileImage(){}

    public void setup(int index, String imageName, boolean collision)
    {
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = gp.uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].setCollision(collision);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap()
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
            int col = 0, row = 0;
            while (col < this.col && row < this.row)
            {
                String line = br.readLine();
                while (col < this.col)
                {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == this.col)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        int worldCol = 0, worldRow = 0;
        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
            int screenY = worldY - gp.player.getWorldY() + gp.player.getScreenY();

            // Stop moving camera at the edge
            if (gp.player.getScreenX() > gp.player.getWorldX())
                screenX = worldX;
            if (gp.player.getScreenY() > gp.player.getWorldY())
                screenY = worldY;

            int rightOffset = gp.screenWidth - gp.player.getScreenX();
            if (rightOffset > gp.getWorldWidth() - gp.player.getWorldX())
                screenX = gp.screenWidth - (gp.getWorldWidth() - worldX);

            int bottomOffset = gp.screenHeight - gp.player.getScreenY();
            if (bottomOffset > gp.getWorldHeight() - gp.player.getWorldY())
                screenY = gp.screenHeight - (gp.getWorldHeight() - worldY);

            /*
             * try to create boundaries so the only tiles render in player boundaries
             * try to cut some extra processing
             */
            if (worldX + gp.tileSize > gp.player.getWorldX() - gp.player.getScreenX() &&
                    worldX - gp.tileSize < gp.player.getWorldX() + gp.player.getScreenX() &&
                    worldY + gp.tileSize > gp.player.getWorldY() - gp.player.getScreenY() &&
                    worldY - gp.tileSize < gp.player.getWorldY() + gp.player.getScreenY())
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            else if (gp.player.getScreenX() > gp.player.getWorldX() ||
                    gp.player.getScreenY() > gp.player.getWorldY() ||
                    rightOffset > gp.getWorldWidth() - gp.player.getWorldX() ||
                    bottomOffset > gp.getWorldHeight() - gp.player.getWorldY())
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            worldCol++;
            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
