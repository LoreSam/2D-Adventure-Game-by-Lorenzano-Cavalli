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

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

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

        if(gp.gameState == gp.playState){
            //TODO play
        }
        if(gp.gameState == gp.pauseState){
            //TODO pause
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String text = "PAUSA";
        int x = centreText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public int centreText(String text){
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }
}
