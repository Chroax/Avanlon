package util;

import entity.Entity;
import ui.GamePanel;

public class CollisionChecker
{
    GamePanel gp;
    public CollisionChecker(GamePanel gp){ this.gp = gp; }

    public void checkTile(Entity entity)
    {
        int entityLWX = entity.getWorldX() + entity.solidArea.x;
        int entityRWX = entity.getWorldX() + entity.solidArea.x + entity.solidArea.width;
        int entityTWY = entity.getWorldY() + entity.solidArea.y;
        int entityBWY = entity.getWorldY() + entity.solidArea.y + entity.solidArea.height;

        int entityLCol = entityLWX / gp.tileSize;
        int entityRCol = entityRWX / gp.tileSize;
        int entityTRow = entityTWY / gp.tileSize;
        int entityBRow = entityBWY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.getDirection())
        {
            case "up" ->{
                entityTRow = (entityTWY - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.plain.mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.plain.mapTileNum[entityRCol][entityTRow];
                if(gp.plain.tile[tileNum1].isCollision() || gp.plain.tile[tileNum2].isCollision())
                    entity.setCollisionOn(true);
            }
            case "down" ->{
                entityBRow = (entityBWY + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.plain.mapTileNum[entityLCol][entityBRow];
                tileNum2 = gp.plain.mapTileNum[entityRCol][entityBRow];
                if(gp.plain.tile[tileNum1].isCollision() || gp.plain.tile[tileNum2].isCollision())
                    entity.setCollisionOn(true);
            }
            case "left" ->{
                entityLCol = (entityLWX - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.plain.mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.plain.mapTileNum[entityLCol][entityBRow];
                if(gp.plain.tile[tileNum1].isCollision() || gp.plain.tile[tileNum2].isCollision())
                    entity.setCollisionOn(true);
            }
            case "right" ->{
                entityRCol = (entityRWX + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.plain.mapTileNum[entityRCol][entityTRow];
                tileNum2 = gp.plain.mapTileNum[entityRCol][entityBRow];
                if(gp.plain.tile[tileNum1].isCollision() || gp.plain.tile[tileNum2].isCollision())
                    entity.setCollisionOn(true);
            }
        }
    }

    public void checkEntity(Entity entity, Entity[] target)
    {

    }

    public void checkPlayer(Entity entity)
    {
        // Get entity solid area position
        entity.solidArea.x = entity.getWorldX() + entity.solidArea.x;
        entity.solidArea.y = entity.getWorldY() + entity.solidArea.y;

        // Get the object solid area position
        gp.player.solidArea.x += gp.player.getWorldX();
        gp.player.solidArea.y += gp.player.getWorldY();

        switch (entity.getDirection())
        {
            case "up" -> {
                // To predict what tiles are player to step in
                entity.solidArea.y -= entity.getSpeed();
                if(entity.solidArea.intersects(gp.player.solidArea))
                    entity.setCollisionOn(true);
            }
            case "down" -> {
                // To predict what tiles are player to step in
                entity.solidArea.y += entity.getSpeed();
                if(entity.solidArea.intersects(gp.player.solidArea))
                    entity.setCollisionOn(true);
            }
            case "left" -> {
                // To predict what tiles are player to step in
                entity.solidArea.x -= entity.getSpeed();
                if(entity.solidArea.intersects(gp.player.solidArea))
                    entity.setCollisionOn(true);
            }
            case "right" -> {
                // To predict what tiles are player to step in
                entity.solidArea.x += entity.getSpeed();
                if(entity.solidArea.intersects(gp.player.solidArea))
                    entity.setCollisionOn(true);
            }
        }

        entity.solidArea.x = entity.getSolidAreaDefaultX();
        entity.solidArea.y = entity.getSolidAreaDefaultY();
        gp.player.solidArea.x = gp.player.getSolidAreaDefaultX();
        gp.player.solidArea.y = gp.player.getSolidAreaDefaultY();
    }
}
