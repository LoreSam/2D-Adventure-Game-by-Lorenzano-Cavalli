package main;

import entity.NPC_OldMan;
import monster.MON_Dogs;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        /*gp.obj[0] = new OBJ_Door(gp);
        gp.obj[0].worldX = 21 * gp.tileSize;
        gp.obj[0].worldY = 22 * gp.tileSize;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 25 * gp.tileSize;*/
    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }

    public void setMonster(){
        int i = 0;
        gp.monster[i] = new MON_Dogs(gp);
        gp.monster[i].worldX = gp.tileSize * 22;
        gp.monster[i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[i] = new MON_Dogs(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 23;
        i++;
        gp.monster[i] = new MON_Dogs(gp);
        gp.monster[i].worldX = gp.tileSize * 24;
        gp.monster[i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[i] = new MON_Dogs(gp);
        gp.monster[i].worldX = gp.tileSize * 34;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[i] = new MON_Dogs(gp);
        gp.monster[i].worldX = gp.tileSize * 38;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;
    }
}
