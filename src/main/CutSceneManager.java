package main;

import entity.NPC_OldMan;

import java.awt.*;

public class CutSceneManager {

    //TODO SE VOGLIAMO USARLA, PER ALTRE CUTSCENE, IL TUTORIAL è APPOSTO

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
                //sceneTutorial();
                break;
        }
    }/*

    public void sceneTutorial(){

        if(!gp.ui.temp)
            gp.eHandler.tutorial();
    }

    public void start(){

        gp.gameState = gp.playState;
    }*/
}
