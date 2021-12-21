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
                tileNum1 = gp.map[gp.getMapPick()].mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.map[gp.getMapPick()].mapTileNum[entityRCol][entityTRow];
                if(gp.map[gp.getMapPick()].tile[tileNum1].isCollision() || gp.map[gp.getMapPick()].tile[tileNum2].isCollision())
                    entity.setCollisionOn(true);
            }
            case "down" ->{
                entityBRow = (entityBWY + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.map[gp.getMapPick()].mapTileNum[entityLCol][entityBRow];
                tileNum2 = gp.map[gp.getMapPick()].mapTileNum[entityRCol][entityBRow];
                if(gp.map[gp.getMapPick()].tile[tileNum1].isCollision() || gp.map[gp.getMapPick()].tile[tileNum2].isCollision())
                    entity.setCollisionOn(true);
            }
            case "left" ->{
                entityLCol = (entityLWX - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.map[gp.getMapPick()].mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.map[gp.getMapPick()].mapTileNum[entityLCol][entityBRow];
                if(gp.map[gp.getMapPick()].tile[tileNum1].isCollision() || gp.map[gp.getMapPick()].tile[tileNum2].isCollision())
                    entity.setCollisionOn(true);
            }
            case "right" ->{
                entityRCol = (entityRWX + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.map[gp.getMapPick()].mapTileNum[entityRCol][entityTRow];
                tileNum2 = gp.map[gp.getMapPick()].mapTileNum[entityRCol][entityBRow];
                if(gp.map[gp.getMapPick()].tile[tileNum1].isCollision() || gp.map[gp.getMapPick()].tile[tileNum2].isCollision())
                    entity.setCollisionOn(true);
            }
        }
    }

    public int checkEntity(Entity entity, Entity[] target)
    {
        int index = 999;

        for (int i = 0; i < target.length; i++)
        {
            if(target[i] != null)
            {
                // Get entity solid area position
                entity.solidArea.x = entity.getWorldX() + entity.solidArea.x;
                entity.solidArea.y = entity.getWorldY() + entity.solidArea.y;

                // Get the object solid area position
                target[i].solidArea.x += target[i].getWorldX();
                target[i].solidArea.y += target[i].getWorldY();

                switch (entity.getDirection())
                {
                    case "up" -> {
                        // To predict what tiles are player to step in
                        entity.solidArea.y -= entity.getSpeed();
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                    }
                    case "down" -> {
                        // To predict what tiles are player to step in
                        entity.solidArea.y += entity.getSpeed();
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                    }
                    case "left" -> {
                        // To predict what tiles are player to step in
                        entity.solidArea.x -= entity.getSpeed();
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                    }
                    case "right" -> {
                        // To predict what tiles are player to step in
                        entity.solidArea.x += entity.getSpeed();
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                    }
                }
                entity.solidArea.x = entity.getSolidAreaDefaultX();
                entity.solidArea.y = entity.getSolidAreaDefaultY();
                target[i].solidArea.x = target[i].getSolidAreaDefaultX();
                target[i].solidArea.y = target[i].getSolidAreaDefaultY();
            }
        }
        return index;
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
    
    public int checkObj(Entity entity, boolean player)
    {
        int index = 999;

        for (int i = 0; i < gp.map[gp.getMapPick()].obj.length; i++)
        {
            if(gp.map[gp.getMapPick()].obj[i] != null)
            {
                // Get entity solid area position
                entity.solidArea.x = entity.getWorldX() + entity.solidArea.x;
                entity.solidArea.y = entity.getWorldY() + entity.solidArea.y;

                // Get the object solid area position
                gp.map[gp.getMapPick()].obj[i].solidArea.x += gp.map[gp.getMapPick()].obj[i].getWorldX();
                gp.map[gp.getMapPick()].obj[i].solidArea.y += gp.map[gp.getMapPick()].obj[i].getWorldY();

                switch (entity.getDirection())
                {
                    case "up" -> {
                        // To predict what tiles are player to step in
                        entity.solidArea.y -= entity.getSpeed();
                        if(entity.solidArea.intersects(gp.map[gp.getMapPick()].obj[i].solidArea))
                        {
                            if(gp.map[gp.getMapPick()].obj[i].isCollision())
                                entity.setCollisionOn(true);
                            if(player)
                                index = i;
                        }
                    }
                    case "down" -> {
                        // To predict what tiles are player to step in
                        entity.solidArea.y += entity.getSpeed();
                        if(entity.solidArea.intersects(gp.map[gp.getMapPick()].obj[i].solidArea))
                        {
                            if(gp.map[gp.getMapPick()].obj[i].isCollision())
                                entity.setCollisionOn(true);
                            if(player)
                                index = i;
                        }
                    }
                    case "left" -> {
                        // To predict what tiles are player to step in
                        entity.solidArea.x -= entity.getSpeed();
                        if(entity.solidArea.intersects(gp.map[gp.getMapPick()].obj[i].solidArea))
                        {
                            if(gp.map[gp.getMapPick()].obj[i].isCollision())
                                entity.setCollisionOn(true);
                            if(player)
                                index = i;
                        }
                    }
                    case "right" -> {
                        // To predict what tiles are player to step in
                        entity.solidArea.x += entity.getSpeed();
                        if(entity.solidArea.intersects(gp.map[gp.getMapPick()].obj[i].solidArea))
                        {
                            if(gp.map[gp.getMapPick()].obj[i].isCollision())
                                entity.setCollisionOn(true);
                            if(player)
                                index = i;
                        }
                    }
                }
                entity.solidArea.x = entity.getSolidAreaDefaultX();
                entity.solidArea.y = entity.getSolidAreaDefaultY();
                gp.map[gp.getMapPick()].obj[i].solidArea.x = gp.map[gp.getMapPick()].obj[i].solidAreaDefaultX;
                gp.map[gp.getMapPick()].obj[i].solidArea.y = gp.map[gp.getMapPick()].obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
