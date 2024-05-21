package main;

import entity.NPC_Merchant;
import entity.NPC_Guard;
import monster.NPC_Prisoner;
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
        gp.obj[mapNum][i] = new OBJ_Rock(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 80 * gp.tileSize;
        i++;
    }

    public void setNPC(){

        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_Guard(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 17;
        gp.npc[mapNum][i].worldY = gp.tileSize * 15;
        i++;

        gp.npc[mapNum][i] = new NPC_Guard(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 40;
        gp.npc[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.npc[mapNum][i] = new NPC_Guard(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 50;
        i++;

        gp.npc[mapNum][i] = new NPC_Guard(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 30;
        gp.npc[mapNum][i].worldY = gp.tileSize * 40;
        i++;

        gp.npc[mapNum][i] = new NPC_Guard(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 25;
        gp.npc[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        //SOTTOTERRA
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;
    }

    public void setMonster(){

        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new NPC_Prisoner(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 58;
        gp.monster[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        gp.monster[mapNum][i] = new NPC_Prisoner(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 62;
        gp.monster[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        gp.monster[mapNum][i] = new NPC_Prisoner(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 54;
        gp.monster[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.monster[mapNum][i] = new NPC_Prisoner(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        gp.monster[mapNum][i] = new NPC_Prisoner(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 45;
        gp.monster[mapNum][i].worldY = gp.tileSize * 38;
        i++;
        gp.monster[mapNum][i] = new NPC_Prisoner(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 43;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;
        gp.monster[mapNum][i] = new NPC_Prisoner(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 50;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
        i++;
    }

    public void setInteractiveTile(){

        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_Door(gp, 24, 15);
        i++;
        gp.iTile[mapNum][i] = new IT_Door(gp, 25, 15);
        i++;
        gp.iTile[mapNum][i] = new IT_Door(gp, 24, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Door(gp, 25, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Door(gp, 24, 47);
        i++;
        gp.iTile[mapNum][i] = new IT_Door(gp, 25, 47);
        i++;
        gp.iTile[mapNum][i] = new IT_Door(gp, 52, 15);
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
        gp.iTile[mapNum][i] = new IT_Panca(gp, 20, 31);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 23, 31);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 26, 31);
        i++;
        gp.iTile[mapNum][i] = new IT_Panca(gp, 29, 31);
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
        gp.iTile[mapNum][i] = new IT_Wall(gp, 38, 24);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 42, 22);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 45, 26);
        i++;
        gp.iTile[mapNum][i] = new IT_Wall(gp, 40, 28);
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
        //gp.iTile[mapNum][i] = new IT_Grate(gp, 9, 10);
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
