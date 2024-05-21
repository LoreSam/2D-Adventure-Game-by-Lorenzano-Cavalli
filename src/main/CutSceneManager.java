package main;

import entity.Entity;
import entity.NPC_Guard;

import java.awt.*;

public class CutSceneManager {

    //TODO SE VOGLIAMO USARLA, PER ALTRE CUTSCENE, IL TUTORIAL è APPOSTO

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    int y;
    float alpha = 0;
    String text, endCredit;

    //NUMERO SCENA
    public final int ending = 0;
    public final int start = -1;

    public CutSceneManager(GamePanel gp) {
        this.gp = gp;

        endCredit = "Realizzato da\n"
                + "Saverio Enzano & Gabriele Cavalli\n\n"
                + "Grafica a cura di Saverio Enzano Gabriele Cavalli\n"
                + "Musica di Samuele Lorenzano\n"
                + "Supporto morale Tommasetti Samuele\n"
                + "Insulti Giovanni Pantaleo\n"
                + "Un grazie speciale a Intellij IDEA 2024.1\n";
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        switch(sceneNum){
            case ending:
                scene_ending();
                break;
            case start:
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

    public void scene_ending(){

        if(scenePhase == 0){
            gp.stopMusic();
            gp.ui.npc.dialogueIndex++;
            gp.ui.npc = new NPC_Guard(gp);
            scenePhase++;
        }
        if(scenePhase == 1){
            gp.ui.drawDialogueScreen();
            scenePhase++;
        }
        if(scenePhase == 2){
            gp.playSoundEffect(8);
            scenePhase++;
        }
        if(scenePhase == 3){

            if(counterReached(300))
                scenePhase++;
        }
        if(scenePhase == 4){

            alpha += 0.005f;
            if(alpha > 1f)
                alpha = 1f;
            drawBackground(alpha);

            if(alpha == 1f){
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 5){

            drawBackground(1f);

            alpha += 0.005f;
            if(alpha > 1f)
                alpha = 1f;

            if(gp.language == 1){

                text = "Updates incoming!\n"
                        + "With new maps and features!\n"
                        + "And new crimes to commit!\n\n";
            }
            else {
                text = "Aggiornamenti in arrivo!\n"
                        + "Con nuove mappe e funzionalità!\n"
                        + "E nuovi crimini da commettere!\n\n";
            }
            drawString(alpha, 38f, 200, text, 70);

            if(counterReached(600)) {
                gp.playMusic(0);
                scenePhase++;
            }
        }
        if(scenePhase == 6){

            drawBackground(1f);

            if(gp.language == 1)
                drawString(1f, 120, gp.screenHeight/2, "The Escapologist", 48);
            else
                drawString(1f, 120, gp.screenHeight/2, "L'Escapologo", 48);

            if(counterReached(480))
                scenePhase++;
        }
        if(scenePhase == 7){

            drawBackground(1f);

            y = gp.screenHeight/2;
            drawString(1f, 38f, gp.screenHeight/2, endCredit, 40);

            if(counterReached(480))
                scenePhase++;
        }
        if(scenePhase == 8){

            drawBackground(1f);

            y--;
            drawString(1f, 38f, y, endCredit, 40);
        }
    }

    public boolean counterReached(int target){

        boolean counterReached = false;

        counter++;
        if(counter > target){
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }

    public void drawBackground(float alpha){

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0,  gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text2, int lineHeight){

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line: text2.split("\n")){
            int x = gp.ui.centerText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
