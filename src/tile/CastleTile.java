package tile;

import entity.Entity;
import entity.monster.Monster;
import object.SuperObject;
import ui.GamePanel;

public class CastleTile extends TileManager
{

    public CastleTile(GamePanel gp)
    {
        super(gp);
        monsters = new Monster[10];
        obj = new SuperObject[2];
        NPC = new Entity[10];
        tile = new Tile[100];
        mapTileNum = new int[69][40];
        col = 69;
        row = 40;
        filePath = "/map/worldV4.txt";
        getTileImage();
        loadMap();
    }

    @Override
    public void getTileImage()
    {
        // PLACEHOLDER to prevent NullPointer
        for (int i = 0; i <= 10; i++)
            setup(i, "castle/Layer 1", false);

        // Image
        setup(11, "castle/Layer 1", false);
        setup(12, "castle/Layer 2", false);
        setup(13, "castle/Layer 3", false);
        setup(14, "castle/Layer 4", false);
        setup(15, "castle/Layer 5", false);
        setup(16, "castle/Layer 6", true);
        setup(17, "castle/Layer 7", true);
        setup(18, "castle/Layer 8", true);
        setup(19, "castle/Layer 9", true);
        setup(20, "castle/Layer 10", true);
        setup(21, "castle/Layer 11", true);
        setup(22, "castle/Layer 12", true);
        setup(23, "castle/Layer 13", true);
        setup(24, "castle/Layer 14", true);
        setup(25, "castle/Layer 15", true);
        setup(26, "castle/Layer 16", true);
        setup(27, "castle/Layer 17", true);
        setup(28, "castle/Layer 18", false);
        setup(29, "castle/Layer 19", false);
        setup(30, "castle/Layer 20", false);
        setup(31, "castle/Layer 21", false);
        setup(32, "castle/Layer 22", true);
        setup(33, "castle/Layer 23", false);
        setup(34, "castle/Layer 24", true);
        setup(35, "castle/Layer 25", true);
        setup(36, "castle/Layer 26", true);
        setup(37, "castle/Layer 27", true);
        setup(38, "castle/Layer 28", false);
        setup(39, "castle/Layer 29", true);
        setup(40, "castle/Layer 30", true);
        setup(41, "castle/Layer 31", true);
        setup(42, "castle/Layer 32", false);
        setup(43, "castle/Layer 33", false);
        setup(44, "castle/Layer 34", false);
        setup(45, "castle/Layer 35", false);
        setup(46, "castle/Layer 36", false);
        setup(47, "castle/Layer 37", false);
        setup(48, "castle/Layer 38", false);
        setup(49, "castle/Layer 39", false);
        setup(50, "castle/Layer 40", false);
        setup(51, "castle/Layer 41", false);
        setup(52, "castle/Layer 42", false);
        setup(53, "castle/Layer 43", false);
        setup(54, "castle/Layer 44", false);
        setup(55, "castle/Layer 45", true);
        setup(56, "castle/Layer 46", false);
        setup(57, "castle/Layer 47", true);
        setup(58, "castle/Layer 48", true);
        setup(59, "castle/Layer 49", false);
        setup(60, "castle/Layer 50", true);
        setup(61, "castle/Layer 51", true);
        setup(62, "castle/Layer 52", true);
        setup(63, "castle/Layer 53", true);
        setup(64, "castle/Layer 54", true);
        setup(65, "castle/Layer 55", false);
        setup(66, "castle/Layer 56", true);
        setup(67, "castle/Layer 57", true);
        setup(68, "castle/Layer 58", true);
        setup(69, "castle/Layer 59", true);
        setup(70, "castle/Layer 60", false);
        setup(71, "castle/Layer 61", false);
        setup(72, "castle/Layer 62", false);
        setup(73, "castle/Layer 63", false);
        setup(74, "castle/Layer 64", false);
        setup(75, "castle/Layer 65", false);
        setup(76, "castle/Layer 66", false);
        setup(77, "castle/Layer 67", true);
        setup(78, "castle/Layer 68", true);
        setup(79, "castle/Layer 69", true);
        setup(80, "castle/Layer 70", false);
        setup(81, "castle/Layer 71", true);
        setup(82, "castle/Layer 72", false);
        setup(83, "castle/Layer 73", false);
        setup(84, "castle/Layer 74", false);
        setup(85, "castle/Layer 75", false);
        setup(86, "castle/Layer 76", true);
        setup(87, "castle/Layer 77", true);
    }
}
