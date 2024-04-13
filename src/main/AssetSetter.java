package main;

import entity.NPC_OldMan;
import monster.MON_Dogs;
import object.*;
import tile_interactive.IT_Bed;
import tile_interactive.IT_Cesso;
import tile_interactive.IT_Chest;
import tile_interactive.IT_DryTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){

        int i = 0;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = 25 * gp.tileSize;
        gp.obj[i].worldY = 19 * gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = 21 * gp.tileSize;
        gp.obj[i].worldY = 19 * gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = 26 * gp.tileSize;
        gp.obj[i].worldY = 21 * gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = 33 * gp.tileSize;
        gp.obj[i].worldY = 21 * gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = 35 * gp.tileSize;
        gp.obj[i].worldY = 21 * gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = 36 * gp.tileSize;
        gp.obj[i].worldY = 21 * gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].worldX = 22 * gp.tileSize;
        gp.obj[i].worldY = 29 * gp.tileSize;
        i++;
        gp.obj[i] = new OBJ_Energy(gp);
        gp.obj[i].worldX = 22 * gp.tileSize;
        gp.obj[i].worldY = 31 * gp.tileSize;

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
    }
    public void setInteractiveTile(){
        int i = 0;
        gp.iTile[i] = new IT_DryTree(gp, 27, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 28, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 29, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 30, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 31, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 32, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 33, 12);
        i++;
        gp.iTile[i] = new IT_Bed(gp, 51, 7);
        i++;
        gp.iTile[i] = new IT_Chest(gp, 55, 8);
        i++;
        gp.iTile[i] = new IT_Cesso(gp, 55, 7);

    }
}
