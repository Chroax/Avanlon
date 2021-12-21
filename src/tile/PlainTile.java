package tile;

import entity.monster.Monster;
import ui.GamePanel;

public class PlainTile extends TileManager
{

    public PlainTile(GamePanel gp)
    {
        super(gp);
        monsters = new Monster[5];
        tile = new Tile[42];
        mapTileNum = new int[50][50];
        col = 50;
        row = 50;
        filePath = "/map/worldV2.txt";
        getTileImage();
        loadMap();
    }

    @Override
    public void getTileImage()
    {
        // PLACEHOLDER to prevent NullPointer
        for (int i = 0; i <= 9; i++)
            setup(i, "plain/grass00", false);

        // Image
        setup(10, "plain/grass00", false);
        setup(11, "plain/grass01", false);
        setup(12, "plain/water00", true);
        setup(13, "plain/water01", true);
        setup(14, "plain/water02", true);
        setup(15, "plain/water03", true);
        setup(16, "plain/water04", true);
        setup(17, "plain/water05", true);
        setup(18, "plain/water06", true);
        setup(19, "plain/water07", true);
        setup(20, "plain/water08", true);
        setup(21, "plain/water09", true);
        setup(22, "plain/water10", true);
        setup(23, "plain/water11", true);
        setup(24, "plain/water12", true);
        setup(25, "plain/water13", true);
        setup(26, "plain/road00", false);
        setup(27, "plain/road01", false);
        setup(28, "plain/road02", false);
        setup(29, "plain/road03", false);
        setup(30, "plain/road04", false);
        setup(31, "plain/road05", false);
        setup(32, "plain/road06", false);
        setup(33, "plain/road07", false);
        setup(34, "plain/road08", false);
        setup(35, "plain/road09", false);
        setup(36, "plain/road10", false);
        setup(37, "plain/road11", false);
        setup(38, "plain/road12", false);
        setup(39, "plain/earth", false);
        setup(40, "plain/wall", true);
        setup(41, "plain/tree", true);
    }
}
