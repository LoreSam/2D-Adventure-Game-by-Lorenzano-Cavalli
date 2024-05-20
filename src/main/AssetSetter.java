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

        int mapNum = 0;
        int i = 0;
        /*gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = 90 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 90 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Pickaxe(gp);
        gp.obj[mapNum][i].worldX = 91 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 90 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Scissors(gp);
        gp.obj[mapNum][i].worldX = 92 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 90 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Stick(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;*/


        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Pickaxe(gp);
        gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Scissors(gp);
        gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Void(gp);
        gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Void(gp);
        gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Void(gp);
        gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        /*gp.obj[mapNum][i] = new OBJ_Rock(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;*/
        gp.obj[mapNum][i] = new OBJ_Lantern(gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 20 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;
        /*gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
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

        /*gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 54;
        gp.npc[mapNum][i].worldY = gp.tileSize * 12;*/

        //SOTTOTERRA
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;

        /*if(gp.currentMap == 2){
            mapNum = 2;
            gp.npc[mapNum][i] = new NPC_OldMan(gp);
            gp.npc[mapNum][i].worldX = gp.tileSize * 50;
            gp.npc[mapNum][i].worldY = gp.tileSize * 48;
        }*/
    }

    public void setMonster(){

        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_Dogs(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;
        gp.monster[mapNum][i] = new MON_Dogs(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;
        i++;
    }

    public void setInteractiveTile(){

        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_Door(gp, 18, 18);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 18);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 18);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 45, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 46, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 47, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 48, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 50, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 51, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 52, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 54, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 55, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 56, 81);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 57, 81);
        i++;


        //CELLE ALTE
        gp.iTile[mapNum][i] = new IT_Bed(gp, 52, 11);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 55, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 55, 11);
        i++;

        gp.iTile[mapNum][i] = new IT_Bed(gp, 57, 11);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 60, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 60, 11);
        i++;

        gp.iTile[mapNum][i] = new IT_Bed(gp, 62, 11);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 65, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 65, 11);
        i++;

        //CELLE BASSE
        gp.iTile[mapNum][i] = new IT_Bed(gp, 52, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 55, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 55, 23);
        i++;

        gp.iTile[mapNum][i] = new IT_Bed(gp, 57, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 60, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 60, 23);
        i++;

        gp.iTile[mapNum][i] = new IT_Bed(gp, 62, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Chest(gp, 65, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Cesso(gp, 65, 23);
        i++;

        //DOCCINI
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 22, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 23, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 24, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 25, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 26, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 27, 10);
        i++;

        gp.iTile[mapNum][i] = new IT_Doccia(gp, 22, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 23, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 24, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 25, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 26, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_Doccia(gp, 27, 12);
        i++;

        //PALESTRA
        //TAPIRULANT
        gp.iTile[mapNum][i] = new IT_Tapirulan(gp, 20, 25);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulan(gp, 23, 25);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulan(gp, 26, 25);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulan(gp, 29, 25);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulan(gp, 20, 27);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulan(gp, 23, 27);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulan(gp, 26, 27);
        i++;
        gp.iTile[mapNum][i] = new IT_Tapirulan(gp, 29, 27);
        i++;

        //PANCA
        gp.iTile[mapNum][i] = new IT_Panca(gp, 20, 29);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 23, 29);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 26, 29);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 29, 29);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 20, 29);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 23, 29);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 26, 29);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 29, 29);
        i++;

        //MURI DISTRUGGIBILI
        gp.iTile[mapNum][i] = new IT_Wall(gp, 37, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 41, 11);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 39, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 35, 13);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 38, 21);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 42, 20);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 45, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 43, 28);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 51, 34);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 70, 38);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 64, 36);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 72, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 61, 39);
        i++;


        //GRATE DISTRUGGIBILI
        gp.iTile[mapNum][i] = new IT_Grate(gp, 9, 10);
        i++;
        gp.iTile[mapNum][i] = new IT_Grate(gp, 9, 11);
        i++;
        gp.iTile[mapNum][i] = new IT_Grate(gp, 9, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_Grate(gp, 17, 6);
        i++;
        gp.iTile[mapNum][i] = new IT_Grate(gp, 18, 6);
        i++;
        gp.iTile[mapNum][i] = new IT_Grate(gp, 19, 6);
        i++;

        //TAVOLI MENSA
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 20, 51);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 21, 51);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 22, 51);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 27, 51);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 28, 51);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 29, 51);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 20, 55);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 21, 55);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 22, 55);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 27, 55);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 28, 55);
        i++;
        gp.iTile[mapNum][i] = new IT_FoodTable(gp, 29, 55);
        i++;

    }
}
