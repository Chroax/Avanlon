package tile;

import entity.monster.Monster;
import ui.GamePanel;

public class DungeonTile extends TileManager
{

    public DungeonTile(GamePanel gp)
    {
        super(gp);
        monsters = new Monster[5];
        tile = new Tile[100];
        mapTileNum = new int[66][35];
        col = 66;
        row = 35;
        filePath = "/map/worldV3.txt";
        getTileImage();
        loadMap();
    }

    @Override
    public void getTileImage()
    {
        // PLACEHOLDER to prevent NullPointer
        for (int i = 0; i <= 10; i++)
            setup(i, "dungeon/Layer 1", false);

        // Image
        setup(10, "dungeon/Layer 2", true);
        setup(11, "dungeon/Layer 3", true);
        setup(12, "dungeon/Layer 4", false);
        setup(13, "dungeon/Layer 5", true);
        setup(14, "dungeon/Layer 6", false);
        setup(15, "dungeon/Layer 7", true);
        setup(16, "dungeon/Layer 8", true);
        setup(17, "dungeon/Layer 9", false);
        setup(18, "dungeon/Layer 10", false);
        setup(19, "dungeon/Layer 11", false);
        setup(20, "dungeon/Layer 12", true);
        setup(21, "dungeon/Layer 13", true);
        setup(22, "dungeon/Layer 14", false);
        setup(23, "dungeon/Layer 15", false);
        setup(32, "dungeon/Layer 24", false);
        setup(33, "dungeon/Layer 25", true);
        setup(34, "dungeon/Layer 26", false);
        setup(35, "dungeon/Layer 27", false);
        setup(36, "dungeon/Layer 28", true);
        setup(37, "dungeon/Layer 29", true);
        setup(38, "dungeon/Layer 30", true);
        setup(39, "dungeon/Layer 31", true);
        setup(40, "dungeon/Layer 32", true);
        setup(41, "dungeon/Layer 33", true);
        setup(42, "dungeon/Layer 34", true);
        setup(43, "dungeon/Layer 35", true);
        setup(44, "dungeon/Layer 36", true);
        setup(46, "dungeon/Layer 38", true);
        setup(47, "dungeon/Layer 39", true);
        setup(48, "dungeon/Layer 40", true);
        setup(49, "dungeon/Layer 41", true);
        setup(50, "dungeon/Layer 42", true);
        setup(51, "dungeon/Layer 43", true);
        setup(52, "dungeon/Layer 44", true);
        setup(53, "dungeon/Layer 45", true);
        setup(55, "dungeon/Layer 47", false);
        setup(59, "dungeon/Layer 51", true);
        setup(60, "dungeon/Layer 52", true);
        setup(61, "dungeon/Layer 53", true);
        setup(62, "dungeon/Layer 54", true);
        setup(64, "dungeon/Layer 56", true);
        setup(65, "dungeon/Layer 57", true);
        setup(66, "dungeon/Layer 58", true);
        setup(67, "dungeon/Layer 59", true);
        setup(68, "dungeon/Layer 60", true);
        setup(69, "dungeon/Layer 61", true);
        setup(70, "dungeon/Layer 62", false);
        setup(71, "dungeon/Layer 63", false);
        setup(72, "dungeon/Layer 64", false);
        setup(73, "dungeon/Layer 65", true);
        setup(74, "dungeon/Layer 66", true);
        setup(75, "dungeon/Layer 67", false);
        setup(76, "dungeon/Layer 68", false);
        setup(77, "dungeon/Layer 69", false);
        setup(78, "dungeon/Layer 70", false);
        setup(79, "dungeon/Layer 71", false);
        setup(80, "dungeon/Layer 72", false);
        setup(81, "dungeon/Layer 73", false);
        setup(82, "dungeon/Layer 74", true);
    }
}
