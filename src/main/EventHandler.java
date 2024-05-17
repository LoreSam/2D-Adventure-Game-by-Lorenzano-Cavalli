package main;

import entity.Entity;
import entity.NPC_OldMan;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {

        this.gp = gp;

        eventMaster = new Entity(gp);

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol){
                col = 0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }

        setDialogue();
    }

    public void setDialogue(){

        //eventMaster.dialogues[0][0] =
    }

    public void checkEvent (){

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize)
            canTouchEvent = true;

        if(canTouchEvent){
            if(hit(0, 13, 35, "any"))
                teleport(1, 12, 15, gp.underground);
            else if(hit(1, 12, 16, "any"))
                teleport(0, 13, 36, gp.outside);
            else if(hit(1, 12, 9, "up"))
                speak(gp.npc[1][0]);
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection){

        boolean hit = false;

        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row* gp.tileSize + eventRect[map][col][row].y;
            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void teleport(int map, int col, int row, int area){

        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;

        /*gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;*/
        canTouchEvent = false;
        //gp.saveLoad.save();
    }

    public void speak(Entity entity){

        if(gp.keyH.enterPressed){
            gp.gameState = gp.dialogueState;
            entity.speak();
        }
    }

    public void tutorial(){

        //gp.currentMap = 2;

        /*gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.tutorial;*/

        for(int i = 0; i < gp.npc[1].length; i++){

            if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name == NPC_OldMan.npcName){
                gp.ui.npc = gp.npc[gp.currentMap][i];
                gp.ui.drawDialogueScreen();
                break;
            }
        }
    }
}
