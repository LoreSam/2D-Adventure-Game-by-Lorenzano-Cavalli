package main;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    /*public String message = "";
    int messageCounter = 0;*/
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameDone = false;

    public String currentDialog ="";
    public int commandNum = 0;
    public int titleScreenState = 0; //il numero determina la schermata (0: prima, 1: seconda...)
    public int slotCol = 0;
    public int slotRow = 0;


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
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void addMessage(String text){
        /*message = text;
        messageOn = true;*/
        message.add(text);
        messageCounter.add(0);
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
            drawMessage();
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

        //STATO PERSONAGGIO
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
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
    public void drawMessage(){
        int messageX = gp.tileSize, messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));
        for ( int i = 0; i < message.size(); i++){
            if (message.get(i) != null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                int counter = messageCounter.get(i) +1;
                messageCounter.set(i, counter);
                messageY += 50;
                if (messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
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
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialog.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
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
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        while (i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
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
            x += gp.tileSize;
        }
    }

    public void drawCharacterScreen(){
        //CREIAMO UN FRAME
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TESTO
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //NOMI
        g2.drawString("Livello", textX, textY);
        textY += lineHeight;
        g2.drawString("Vita", textX, textY);
        textY += lineHeight;
        g2.drawString("Forza", textX, textY);
        textY += lineHeight;
        g2.drawString("Destrezza", textX, textY);
        textY += lineHeight;
        g2.drawString("Attacco", textX, textY);
        textY += lineHeight;
        g2.drawString("Difesa", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Prossimo Livello", textX, textY);
        textY += lineHeight;
        g2.drawString("Monete", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Arma", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Scudo", textX, textY);

        //VALORI
        int tailX = (frameX + frameWidth) - 30;
        //reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strenght);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 14, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 14, null);
    }

    public int alignRightText(String text, int tailX){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }

    public void drawInventory(){

        //SCHERMATA
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //DISEGNA OGGETTI GIOCATORE
        for(int i = 0; i < gp.player.inventory.size(); i++){
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){ //OTTIMIZZABILE
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //CURSORE
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //DISEGNA CURSORE
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // fraem di descrizione
        int dFrameX = frameX, dFrameY = frameY + frameHeight, dFrameWidth = frameWidth, dFrameHeight = gp.tileSize*3;
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
        //disegna la descrizione dell' oggetto
        int textX = dFrameX +20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28f));
        int itemIndex = getItemIndexSlot();
        if (itemIndex < gp.player.inventory.size()){
            for (String line: gp.player.inventory.get(itemIndex).description.split("\n"))
            {
                g2.drawString(line, textX, textY);
                textY += 32;
            }

        }
    }
    public int getItemIndexSlot(){
        int itemIndex= slotCol + (slotRow*5);
        return itemIndex;
    }
}
