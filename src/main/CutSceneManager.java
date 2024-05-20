package main;

import java.awt.*;

public class CutSceneManager {

    //TODO SE VOGLIAMO USARLA, PER ALTRE CUTSCENE, IL TUTORIAL è APPOSTO

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    String endCredit;

    //NUMERO SCENA
    public final int start = 0;
    public final int tutorial = 1;

    public CutSceneManager(GamePanel gp) {
        this.gp = gp;


    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        credits();
        /*switch(sceneNum){
            case tutorial:
                //sceneTutorial();
                break;
        }*/
    }/*

    public void sceneTutorial(){

        if(!gp.ui.temp)
            gp.eHandler.tutorial();
    }

    public void start(){

        gp.gameState = gp.playState;
    }*/
    public void drawBackground(float alpha){

        //TODO DA CHIAMARE ALLA FINE DEL GIOCO
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0,  gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line: text.split("\n")){
            int x = gp.ui.centerText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void credits(){

        int y = gp.screenHeight/2;
        endCredit = "Realizzato da\n"
                +"Saverio Enzano & Gabriele Cavalli\n\n"
                +"Grafica a cura di Saverio Enzano Gabriele Cavalli\n"
                +"Musica di Samuele Lorenzano\n"
                +"Supporto morale Tommasetti Samuele\n"
                +"Insulti Giovanni Pantaleo\n"
                +"Un grazie speciale a Intellij IDEA 2024.1\n";

        drawBackground(1f);
        drawString(1f,38f, y,endCredit, 40);

        drawBackground(1f);
        y--;
        drawString(1f,38f, y, endCredit, 40);

    }
}
