package main;

import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Energy;
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
    BufferedImage heart_full, heart_half, heart_blank, energy_full, energy_blank, energy_75, energy_50, energy_25, coin;
    public boolean messageOn = false;
    /*public String message = "";
    int messageCounter = 0;*/
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameDone = false;

    public String currentDialog = "";
    public int commandNum = 0;
    public int titleScreenState = 0; //il numero determina la schermata (0: prima, 1: seconda...)
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;

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
        Entity energy = new OBJ_Energy(gp);
        energy_full = energy.image;
        energy_75 = energy.image2;
        energy_50 = energy.image3;
        energy_25 = energy.image4;
        energy_blank = energy.image5;
        Entity bronzecoin = new OBJ_Coin_Bronze(gp);
        coin = bronzecoin.down1;
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

        //STATO INVENTARIO
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }

        //STATO OPZIONI
        if(gp.gameState == gp.optionsState){
            drawOptionScreen();
        }

        //STATO GAMEOVER
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }

        //STATO TRANSIZIONE
        if(gp.gameState == gp.transitionState){
            drawTransition();
        }

        //STATO SCAMBIO
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
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
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

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

        //FINESTRA
        int x = gp.tileSize*3, y = gp.tileSize/2, width = gp.screenWidth - (gp.tileSize*6) , height = gp.tileSize*4;
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

        //DISEGNO ENERGIA MASSIMA
        x = gp.tileSize/2 - 5;
        y = (int) (gp.tileSize*1.5);
        //g2.drawImage(energy_blank, x, y, null);

        //DISEGNO ENERGIA
        //g2.drawImage(energy_full, x, y, null);


        if(gp.player.energy == 0)
            g2.drawImage(energy_blank, x, y, null);
        else if(gp.player.energy > 0 && gp.player.energy <= 25)
            g2.drawImage(energy_25, x, y, null);
        else if(gp.player.energy > 25 && gp.player.energy <= 50)
            g2.drawImage(energy_50, x, y, null);
        else if(gp.player.energy > 50 && gp.player.energy <= 75)
            g2.drawImage(energy_75, x, y, null);
        else if(gp.player.energy >= 75)
            g2.drawImage(energy_full, x, y, null);
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
        g2.drawString("Energia", textX, textY);
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
        textY += lineHeight + 10;
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

        value = String.valueOf(gp.player.energy + "/" + gp.player.maxEnergy); //TODO % per l'energia
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
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

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 24, null);
    }

    public int alignRightText(String text, int tailX){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }

    public void drawInventory(Entity entity, boolean cursor){

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if(entity == gp.player){
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        //SCHERMATA
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //DISEGNA OGGETTI GIOCATORE
        for(int i = 0; i < entity.inventory.size(); i++){

            //CURSORE OGGETTO EQUIPAGGIATO
            if (entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){ //OTTIMIZZABILE
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //CURSORE
        if(cursor){
            int cursorX = slotXstart + (slotSize * npcSlotCol);
            int cursorY = slotYstart + (slotSize * npcSlotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            //DISEGNA CURSORE
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // frame di descrizione
            int dFrameX = frameX, dFrameY = frameY + frameHeight, dFrameWidth = frameWidth, dFrameHeight = gp.tileSize*3;

            //disegna la descrizione dell' oggetto
            int textX = dFrameX +20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28f));
            int itemIndex = getItemIndexSlot(slotCol, slotRow);
            if (itemIndex < entity.inventory.size()){

                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

                for (String line: entity.inventory.get(itemIndex).description.split("\n"))
                {
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }

            }
        }
    }

    public void drawGameOverScreen(){

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";

        //OMBRA TESTO
        g2.setColor(Color.black);
        x = centreText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);

        //TESTO
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //RIGIOCA
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Gioca ancora";
        x = centreText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - 40, y);
        }

        //TORNA AL MENU
        text = "Torna al menu principale";
        x = centreText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - 40, y);
        }
    }

    public void drawOptionScreen(){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SOTTOFINESTRA
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState){
            case 0:
                optionsTop(frameX, frameY);
                break;
            case 1:
                optionsFullScreenNotification(frameX, frameY);
                break;
            case 2:
                optionsControl(frameX, frameY);
                break;
            case 3:
                optionsEndGame(frameX, frameY);
                break;
        }

        gp.keyH.enterPressed = false;
    }

    public void optionsTop(int frameX, int frameY){

        int textX;
        int textY;

        //TITOLO
        g2.setFont(g2.getFont().deriveFont(48F));
        String text = "Impostazioni";
        textX = centreText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        g2.setFont(g2.getFont().deriveFont(32F));

        //FULLSCREEN ON - OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Schermo Intero", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                if(!gp.fullScreenOn)
                    gp.fullScreenOn = true;
                else
                    gp.fullScreenOn = false;
                subState = 1;
            }
        }

        //MUSICA
        textY += gp.tileSize;
        g2.drawString("Musica", textX, textY);
        if(commandNum == 1)
            g2.drawString(">", textX - 25, textY);

        //EFFETTI
        textY += gp.tileSize;
        g2.drawString("Effetti Sonori", textX, textY);
        if(commandNum == 2)
            g2.drawString(">", textX - 25, textY);

        //COMANDI
        textY += gp.tileSize;
        g2.drawString("Comandi", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }

        //FINE PARTITA
        textY += gp.tileSize;
        g2.drawString("Esci", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                subState = 3;
                commandNum = 0;
            }
        }

        //INDIETRO
        textY += gp.tileSize * 2;
        g2.drawString("Indietro", textX, textY);
        if(commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //BOX FULL SCREEN
        textX = frameX + (int) (gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 3 - 20;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn){
            g2.fillRect(textX, textY, 24, 24);
        }

        //SLIDER MUSICA
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 12 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //SLIDER SUONI
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 12 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        try {
            gp.config.saveConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void optionsFullScreenNotification(int frameX, int frameY){

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 2;

        currentDialog = "Riavvia il gioco \nper vedere le modifiche!";

        for(String line: currentDialog.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //RIAVVIA ORA
        /*textY = gp.tileSize * 5;
        g2.drawString("Vuoi riavviare ora?", textX, textY);

        String restart = "Si";
        textY = gp.tileSize * 6;
        textX = centreText(restart);
        g2.drawString(restart, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed)
                System.exit(0);
        }

        restart = "No";
        textY = gp.tileSize * 7;
        textX = centreText(restart);
        g2.drawString(restart, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed)
                subState = 0;
        }*/

        //INDIETRO
        textY = gp.tileSize * 9;
        g2.drawString("Indietro", textX, textY);
        if(commandNum == 0){ //da inserire 2 per il riavvia ora
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed)
                subState = 0;
        }
    }

    public void optionsControl(int frameX, int frameY){

        int textX;
        int textY;

        String text = "Comandi";
        textX = centreText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Movimento", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Attacca", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Conferma", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Inventario", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pausa", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Opzioni", textX, textY);

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("W A S D", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Spazio", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Invio", textX, textY);
        textY += gp.tileSize;
        g2.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Esc", textX, textY);

        //INDIETRO
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Indietro", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    public void optionsEndGame(int frameX, int frameY){

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialog = "Sei sicuro \ndi voler abbandonare la partita?";

        for(String line: currentDialog.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //SI
        String text = "Si";
        textX = centreText(text);
        textY = gp.tileSize * 7;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                subState = 0;
                titleScreenState = 0;
                gp.keyH.cambiaMusica(0);
                gp.gameState = gp.titleState;
            }
        }

        //NO
        text = "No";
        textX = centreText(text);
        textY = gp.tileSize * 8;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                subState = 0;
                commandNum = 4;
            }
        }
    }

    public int getItemIndexSlot(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }

    public void drawTransition(){

        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if(counter == 50){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }
    }

    public void drawTradeScreen(){

        switch(subState){
            case 0:
                trade_select();
                break;
            case 1:
                trade_buy();
                break;
            case 2:
                trade_sell();
                break;
        }
        gp.keyH.enterPressed = false;
    }

    public void trade_select(){

        drawDialogueScreen();

        //DISEGNA FINESTRA
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        //DISEGNA TESTI
        x += (int)(gp.tileSize/1.2);
        y += gp.tileSize;

        g2.drawString("Compra", x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed)
                subState = 1;
        }

        y += gp.tileSize;
        g2.drawString("Vendi", x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed)
                subState = 2;
        }

        y += gp.tileSize;
        g2.drawString("Esci", x, y);
        if(commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialog = "Torna eh, ahah!";
            }
        }
    }

    public void trade_buy(){

        //DISEGNO INVENTARIO GIOCATORE
        drawInventory(gp.player, false);

        //DISEGNO INVENTARIO NPC
        drawInventory(npc, true);

        //DISEGNO FINESTRA CONSIGLI
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Indietro", x + 24, y + 60);

        //DISEGNO MONETE GIOCATORE
        x = gp.tileSize*12;
        drawSubWindow(x, y, width, height);
        g2.drawString("Monete: " + gp.player.coin, x + 24, y + 60);

        //DISEGNO FINESTRA PREZZO
        int itemIndex = getItemIndexSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()){

            x = (int)(gp.tileSize*5.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = alignRightText(text, gp.tileSize * 8 - 20);
            g2.drawString(text, x, y + 32);

            //ACQUISTO
            if(gp.keyH.enterPressed){
                if(npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialog = "Ti servono più monete!";
                    drawDialogueScreen();
                }
                else if(gp.player.inventory.size() == gp.player.maxInventorySize){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialog = "Non hai più spazio!";
                    drawDialogueScreen();
                }
                else {
                    gp.player.coin -= npc.inventory.get(itemIndex).price;
                    gp.player.inventory.add(npc.inventory.get(itemIndex));
                }
            }
        }
    }

    public void trade_sell(){

        //DISEGNA INVENTARIO GIOCATORE
        drawInventory(gp.player, true);

        int x;
        int y;
        int width;
        int height;

        //DISEGNO FINESTRA CONSIGLI
        x = gp.tileSize*2;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Indietro", x + 24, y + 60);

        //DISEGNO MONETE GIOCATORE
        x = gp.tileSize*12;
        drawSubWindow(x, y, width, height);
        g2.drawString("Monete: " + gp.player.coin, x + 24, y + 60);

        //DISEGNO FINESTRA PREZZO
        int itemIndex = getItemIndexSlot(playerSlotCol, playerSlotRow);
        if(itemIndex < gp.player.inventory.size()){

            x = (int)(gp.tileSize*15.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = "" + price;
            x = alignRightText(text, gp.tileSize * 18 - 20);
            g2.drawString(text, x, y + 32);

            //VENDITA
            if(gp.keyH.enterPressed){

                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialog = "Non puoi vendere un oggetto equipaggiato!";
                }
                else{
                    gp.player.inventory.remove(itemIndex);
                    gp.player.coin += price;
                }
            }
        }
    }
}
