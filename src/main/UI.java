package main;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameDone = false;

    public String currentDialog ="";
    public int commandNum = 0;
    public int titleScreenState = 0; //il numero determina la schermata (0: prima, 1: seconda...)


    public UI(GamePanel gp) {
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/fonts/Escapists.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //hud object
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(font);
        g2.setColor(Color.white);

        //SCHERMATA TITOLO
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //stato di gioco
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        //stato di pausa
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //stato di dialogo
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen(){

        if(titleScreenState == 0){
            //COLORE BACKGROUND
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            //TITOLO
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
            String text = "L'escapista";
            int x = centreText(text);
            int y = gp.tileSize*3;

            //OMBRA TESTO
            g2.setColor(Color.GRAY);
            g2.drawString(text, x+5, y+5);

            //COLORE TESTO
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //IMMAGINE PERSONAGGIO
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
            text = "Nuova Partita";
            x = centreText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0)
                g2.drawString(">", x - gp.tileSize, y);

            text = "Carica Partita";
            x = centreText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
                g2.drawString(">", x - gp.tileSize, y);

            text = "Esci";
            x = centreText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2)
                g2.drawString(">", x - gp.tileSize, y);
        }
        else if(titleScreenState == 1){
            //SCHERMATA DI SELEZIONE
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(40F));

            String text = "Scegli il personaggio";
            int x = centreText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Gatti";
            x = centreText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 0)
                g2.drawString(">", x - gp.tileSize, y);

            text = "Sardano";
            x = centreText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
                g2.drawString(">", x - gp.tileSize, y);

            text = "Avanzini";
            x = centreText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2)
                g2.drawString(">", x - gp.tileSize, y);

            text = "Indietro";
            x = centreText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 3)
                g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSA";
        int x = centreText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        //finestra
        int x = gp.tileSize*2, y = gp.tileSize/2, width = gp.screenWidth - (gp.tileSize*4) , height = gp.tileSize*4;
        drawSubWindows(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
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
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public void drawPlayerLife(){
        int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i = 0;
        while (i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x +=gp.tileSize;
        }
        x=gp.tileSize/2;
        y=gp.tileSize/2;
        i = 0;
        // vita
        while (i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x +=gp.tileSize;
        }
    }
}
