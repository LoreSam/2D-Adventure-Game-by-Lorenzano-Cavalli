package main;

import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_Dogs;
import object.*;
import tile_interactive.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){

        /*
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 29 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Energy(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 31 * gp.tileSize;*/

    }

    public void setNPC(){

        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 19;
        gp.npc[mapNum][i].worldY = gp.tileSize * 15;

        //SOTTOTERRA
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;
    }

    public void setMonster(){
        /*int i = 0;
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
        gp.monster[i].worldY = gp.tileSize * 42;*/
    }

    public void setInteractiveTile(){

        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 10);
        i++;

        //CELLE ALTE
        gp.iTile[mapNum][i] = new IT_Bed(gp, 51, 7);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 54, 8);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 54, 7);
        i++;

        gp.iTile[mapNum][i] = new IT_Bed(gp, 56, 7);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 59, 8);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 59, 7);
        i++;

        gp.iTile[mapNum][i] = new IT_Bed(gp, 61, 7);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 64, 8);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 64, 7);
        i++;

        //CELLE BASSE
        gp.iTile[mapNum][i] = new IT_Bed(gp, 51, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 54, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 54, 19);
        i++;

        gp.iTile[mapNum][i] = new IT_Bed(gp, 56, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 59, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 59, 19);
        i++;

        gp.iTile[mapNum][i] = new IT_Bed(gp, 61, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 64, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 64, 19);
        i++;

        //DOCCINI
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 19, 6);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 20, 6);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 21, 6);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 22, 6);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 23, 6);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 24, 6);
        i++;

        gp.iTile[mapNum][i] = new IT_Doccia(gp, 19, 8);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 20, 8);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 21, 8);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 22, 8);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 23, 8);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 24, 8);
        i++;

        //PALESTRA
        //TAPIRULANT
        gp.iTile[mapNum][i] = new IT_Tapirulant(gp, 19, 21);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulant(gp, 22, 21);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulant(gp, 25, 21);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulant(gp, 28, 21);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulant(gp, 19, 23);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulant(gp, 22, 23);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulant(gp, 25, 23);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulant(gp, 28, 23);
        i++;

        //PANCA
        gp.iTile[mapNum][i] = new IT_Panca(gp, 19, 25);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 22, 25);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 25, 25);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 28, 25);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 19, 27);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 22, 27);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 25, 27);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 28, 27);
        i++;
    }
}
