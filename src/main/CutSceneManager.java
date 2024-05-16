package main;

import entity.NPC_OldMan;

import java.awt.*;

public class CutSceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    //NUMERO SCENA
    public final int start = 0;
    public final int tutorial = 1;

    public CutSceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        switch(sceneNum){
            case tutorial:
                sceneTutorial();
                break;
        }
    }

    public void sceneTutorial(){

        if(scenePhase == 0){

            gp.player.worldX = gp.tileSize*50;
            gp.player.worldY = gp.tileSize*49;
            gp.aSetter.setInteractiveTile();
            gp.aSetter.setNPC();

            for(int i = 0; i < gp.npc[1].length; i++){

                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name == NPC_OldMan.npcName){
                    gp.ui.npc = gp.npc[gp.currentMap][i];
                    gp.ui.drawDialogueScreen();
                    //scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 1){

        }
    }
}
