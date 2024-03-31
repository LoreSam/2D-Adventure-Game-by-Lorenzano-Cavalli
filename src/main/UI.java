package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font, congratulazioniB;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameDone = false;

    public String currentDialog ="";


    public UI(GamePanel gp) {
        this.gp = gp;

        font = new Font("Arial", Font.PLAIN, 40);
        congratulazioniB = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(font);
        g2.setColor(Color.white);

        //stato di gioco
        if(gp.gameState == gp.playState){
            //TODO play
        }
        //stato di pausa
        if(gp.gameState == gp.pauseState){
            //TODO pause
            drawPauseScreen();
        }
        //stato di dialogo
        if (gp.gameState == gp.dialogueState)
        {
            drawpDialogueScreen();
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String text = "PAUSA";
        int x = centreText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public void drawpDialogueScreen(){
        //finestra
        int x = gp.tileSize*2, y = gp.tileSize/2, width = gp.screenWidth - (gp.tileSize*4) , height = gp.tileSize*4;
        drawSubWindows(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialog.split("\n")){
            g2.drawString(line, x, y);
            y +=40;
        }
    }

    public void drawSubWindows(int x, int y, int width, int height){
        g2.setColor(new Color(0,0,0, 220));
        g2.fillRoundRect(x, y, width, height, 35, 35);
        g2.setColor(new Color(255,255,255));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5,width-10, height-10, 25, 25);
    }

    public int centreText(String text){
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }
}
