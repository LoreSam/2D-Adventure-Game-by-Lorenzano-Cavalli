package main;

import entity.Entity;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    public Font font;
    BufferedImage heart_full, heart_half, heart_blank, energy_full, energy_blank, energy_75, energy_50, energy_25, coin;
    public boolean messageOn = false;
    /*public String message = "";
    int messageCounter = 0;*/
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameDone = false;
    public boolean start = false;

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
        Entity bronzecoin = new OBJ_Coin(gp);
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
            //drawHotbar();
            drawMessage();
            drawClock();
            changeDayText();
        }

        //stato di pausa
        if(gp.gameState == gp.pauseState){
            drawClock();
            drawPlayerLife();
            drawPauseScreen();
        }

        //stato di dialogo
        if (gp.gameState == gp.dialogueState) {
            drawClock();
            drawPlayerLife();
            drawDialogueScreen();
        }

        //STATO INVENTARIO
        if(gp.gameState == gp.characterState){
            drawClock();
            drawInventory(gp.player, true);
            drawCharacterScreen();
        }

        //STATO OPZIONI
        if(gp.gameState == gp.optionsState){
            drawClock();
            drawOptionScreen();
        }

        //STATO GAMEOVER
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }

        //STATO TRANSIZIONE
        if(gp.gameState == gp.transitionState){
            drawClock();
            drawTransition();
        }

        //STATO SCAMBIO
        if(gp.gameState == gp.tradeState){
            drawClock();
            drawTradeScreen();
        }

        //STATO DORMIRE
        if(gp.gameState == gp.sleepState){
            drawSleepScreen();
        }

        if(gp.gameState == gp.craftingState){
            drawClock();
            drawCraftingScreen(gp.player, true);
        }
    }

    public void drawTitleScreen(){

        if(titleScreenState == 0){
            //COLORE BACKGROUND
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //TITOLO
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
            String text;
            if(gp.language == 1)
                text = "The Escapologist";
            else
                text = "L'escapologo";
            int x = centerText(text);
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
            if(gp.language == 1)
                text = "New Game";
            else
                text = "Nuova Partita";
            x = centerText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0)
                g2.drawString(">", x - gp.tileSize, y);

            if(gp.language == 1)
                text = "Load Game";
            else
                text = "Carica Partita";
            x = centerText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1)
                g2.drawString(">", x - gp.tileSize, y);

            if(gp.language == 1)
                text = "Settings";
            else
                text = "Impostazioni";
            x = centerText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2)
                g2.drawString(">", x - gp.tileSize, y);

            if(gp.language == 1)
                text = "Quit Game";
            else
                text = "Esci";
            x = centerText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 3)
                g2.drawString(">", x - gp.tileSize, y);
        }
        else if(gp.ui.titleScreenState == 1){
            subState = 0;
            drawOptionScreen();
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
        int x = centerText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public boolean temp = false;

    public void drawDialogueScreen(){

        //FINESTRA
        int x = gp.tileSize*3, y = gp.tileSize/2, width = gp.screenWidth - (gp.tileSize*6) , height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        x += gp.tileSize;
        y += gp.tileSize;

        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null){

            currentDialog = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];

            if(gp.keyH.enterPressed){
                if(gp.gameState == gp.dialogueState || gp.gameState == gp.tutorialState){
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        }
        else{
            temp = true;
            npc.dialogueIndex = 0;
            if(gp.gameState == gp.dialogueState || gp.gameState == gp.tutorialState){
                gp.gameState = gp.playState;
                changeDayMusic();
                start = true;
            }
        }

        for (String line : currentDialog.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){

        g2.setColor(new Color(25,25,25, 200));
        g2.fillRoundRect(x, y, width, height, 20, 20);
        g2.setColor(new Color(255,255,255));
        g2.setStroke(new BasicStroke(2.7f));
        g2.drawRoundRect(x+5, y+5,width-10, height-10, 10, 10);
    }

    public int centerText(String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void drawPlayerLife(){

        int x = gp.tileSize/2 - 12;
        int y = gp.tileSize/2 - 12;

        //VITA
        if(gp.player.life == 0)
            g2.drawImage(heart_blank, x, y, null);
        else if(gp.player.life <= 50)
            g2.drawImage(heart_half, x, y, null);
        else
            g2.drawImage(heart_full, x, y, null);

        //SCRITTA VITA
        int i = 100;
        x = gp.tileSize + 16;
        y = gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.setColor(Color.red);
        g2.drawString(String.valueOf(gp.player.life), x+2, y+2);
        g2.setColor(Color.white);
        g2.drawString(String.valueOf(gp.player.life), x, y);
        if(gp.player.energy < i){
            g2.drawString(String.valueOf(gp.player.life), x, y);
        }

        //ENERGIA
        x = gp.tileSize/2 - 12;
        y = (int) (gp.tileSize * 1.5);

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

        //SCRITTA ENERGIA
        x = gp.tileSize + 16;
        y = gp.tileSize * 2 + 8;
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.setColor(new Color(250, 185, 0));
        g2.drawString(String.valueOf(gp.player.energy), x+2, y+2);
        g2.setColor(Color.white);
        g2.drawString(String.valueOf(gp.player.energy), x, y);
        if(gp.player.energy < i){
            g2.drawString(String.valueOf(gp.player.energy), x, y);
        }
    }

    public void drawCharacterScreen(){

        String text;

        //CREIAMO UN FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize * 2;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TESTO
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        if(gp.language == 1){
            text = "Life";
            g2.drawString(text, textX, textY);
            textY += lineHeight;
            text = "Energy";
            g2.drawString(text, textX, textY);
            textY += lineHeight;
            text = "Strength";
            g2.drawString(text, textX, textY);
            textY += lineHeight;
            text = "Speed";
            g2.drawString(text, textX, textY);
            textY += lineHeight;
            text = "Coins";
            g2.drawString(text, textX, textY);
            textY += lineHeight + 10;
            text = "Gun";
            g2.drawString(text, textX, textY);
        }
        //NOMI
        else {
            g2.drawString("Vita", textX, textY);
            textY += lineHeight;
            g2.drawString("Energia", textX, textY);
            textY += lineHeight;
            g2.drawString("Forza", textX, textY);
            textY += lineHeight;
            g2.drawString("Velocità", textX, textY);
            textY += lineHeight;
            g2.drawString("Monete", textX, textY);
            textY += lineHeight + 10;
            g2.drawString("Arma", textX, textY);
        }

        //VALORI
        int tailX = (frameX + frameWidth) - 30;
        //reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.energy + "/" + gp.player.maxEnergy);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength + "/100");
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.speed_cont*2 + "/100");
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = alignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
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
            frameX = gp.tileSize * 7;
            frameY = gp.tileSize * 2;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 6;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;

            if(gp.gameState == gp.tradeState){

                frameX = gp.tileSize * 12;
                frameY = gp.tileSize;
                frameHeight = gp.tileSize * 5;
            }
            else if(gp.gameState == gp.craftingState){

                frameX = gp.tileSize * 3;

            }
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
            if (entity.inventory.get(i) == entity.currentWeapon  ||entity.inventory.get(i) == entity.currentLight){
                g2.setColor(new Color(255,255, 255,220));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14 || i == 19){ //OTTIMIZZABILE
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //CURSORE
        if(cursor){
            int cursorX = slotXstart + (slotSize * playerSlotCol);
            int cursorY = slotYstart + (slotSize * playerSlotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            if(entity == gp.ui.npc){
                cursorX = slotXstart + (slotSize * npcSlotCol);
                cursorY = slotYstart + (slotSize * npcSlotRow);
            }

            //DISEGNA CURSORE
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // frame di descrizione
            int dFrameX = frameX, dFrameY = frameY + frameHeight + 12, dFrameWidth = frameWidth, dFrameHeight = gp.tileSize * 2;

            //disegna la descrizione dell' oggetto
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize - 12;
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

        //OGGETTI EQUIPAGGIATI
        /*frameX = gp.tileSize * 7 + 20;
        frameY = gp.tileSize * 7 - 16;
        frameWidth = gp.tileSize * 6 - 36;
        frameHeight = gp.tileSize;

        g2.setStroke(new BasicStroke(3));
        g2.setColor(new Color(250, 250, 250, 170));

        if(gp.gameState == gp.tradeState){
            frameX = gp.tileSize * 12 + 20;
            frameY = gp.tileSize * 5 - 16;
        }
        else if(gp.gameState == gp.craftingState){
            frameX = gp.tileSize * 3 + 18;
        }

        g2.drawRoundRect(frameX, frameY, frameWidth, frameHeight, 10, 10);*/
    }

    public void drawHotbar(){

        playerSlotRow = 0;
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;

        frameX = gp.tileSize * 7;
        frameY = gp.tileSize * 11 - 12;
        frameWidth = gp.tileSize * 5 + 24;
        frameHeight = (int) (gp.tileSize * 1.2);

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        drawInventoryCursor(gp.tileSize * 7 - 14, gp.tileSize * 10 + 20);
    }

    public void drawInventoryCursor(int frameX, int frameY){

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        int cursorX = slotXstart + (slotSize * playerSlotCol);
        int cursorY = slotYstart + (slotSize * playerSlotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //DISEGNA CURSORE
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }

    public void drawCraftingScreen(Entity entity, boolean cursor){

        drawInventory(gp.player, false);

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        final int slotXstart = frameX + gp.tileSize * 11 + 20;
        final int slotYstart = frameY + gp.tileSize * 2 + 10;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        frameX = gp.tileSize * 11;
        frameY = gp.tileSize * 2;
        frameWidth = gp.tileSize * 6;
        frameHeight = (int) (gp.tileSize * 1.5);

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        for(int i = 0; i < 3; i++){

            g2.drawImage(gp.obj[gp.currentMap][i].down1, slotX, slotY, null);
            slotX += 100;
        }

        if(cursor){

            int cursorX = slotXstart + (slotSize * playerSlotCol);
            int cursorY = slotYstart + (slotSize * playerSlotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            //DISEGNA CURSORE
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        }
    }

    public void drawMissingItemsText(){

        g2.getFont().deriveFont(Font.BOLD, 28f);
        g2.setColor(Color.WHITE);
        g2.drawString("Non hai gli oggetti necessari!", gp.tileSize * 11, gp.tileSize * 3);
    }

    public void drawGameOverScreen(){

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        if(gp.language == 1)
            text = "Game Over";
        else
            text = "Sei Morto";

        //OMBRA TESTO
        g2.setColor(Color.black);
        x = centerText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);

        //TESTO
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //RIGIOCA
        g2.setFont(g2.getFont().deriveFont(50f));
        if(gp.language == 1)
            text = "Play Again!";
        else
            text = "Gioca ancora!";
        x = centerText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - 40, y);
        }

        //TORNA AL MENU
        if(gp.language == 1)
            text = "Quit to Menu";
        else
            text = "Torna al Menu Principale";
        x = centerText(text);
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
                optionsLanguage(frameX, frameY);
                break;
            case 4:
                optionsEndGame(frameX, frameY);
                break;
        }

        gp.keyH.enterPressed = false;
    }

    public void optionsTop(int frameX, int frameY){

        int textX;
        int textY;
        String text;

        //TITOLO
        g2.setFont(g2.getFont().deriveFont(48F));
        if(gp.language == 1)
            text = "Settings";
        else
            text = "Impostazioni";
        textX = centerText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        g2.setFont(g2.getFont().deriveFont(32F));

        //FULLSCREEN ON - OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        if(gp.language == 1)
            text = "Full Screen";
        else
            text = "Schermo Intero";
        g2.drawString(text, textX, textY);

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
        if(gp.language == 1)
            text = "Music";
        else
            text = "Musica";
        g2.drawString(text, textX, textY);
        if(commandNum == 1)
            g2.drawString(">", textX - 25, textY);

        //EFFETTI
        textY += gp.tileSize;
        if(gp.language == 1)
            text = "Sound Effects";
        else
            text = "Effetti Sonori";
        g2.drawString(text, textX, textY);
        if(commandNum == 2)
            g2.drawString(">", textX - 25, textY);

        //COMANDI
        textY += gp.tileSize;
        if(gp.language == 1)
            text = "Key Bindings";
        else
            text = "Comandi";
        g2.drawString(text, textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }

        textY += gp.tileSize;
        if(gp.language == 1)
            text = "Language";
        else
            text = "Lingua";
        g2.drawString(text, textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 3;
                commandNum = 0;
            }
        }

        //FINE PARTITA
        textY += gp.tileSize;
        if(gp.language == 1)
            text = "Quit";
        else
            text = "Esci";
        g2.drawString(text, textX, textY);
        if(commandNum == 5){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                subState = 4;
                commandNum = 0;
            }
        }

        //INDIETRO
        textY += gp.tileSize * 2 - 24;
        if(gp.language == 1)
            text = "Back";
        else
            text = "Indietro";
        g2.drawString(text, textX, textY);
        if(commandNum == 6) {
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
        String text;

        if(gp.language == 1)
            currentDialog = "Restart the game \nto see the changes!";
        else
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
        if(gp.language == 1)
            text = "Back";
        else
            text = "Indietro";
        g2.drawString(text, textX, textY);
        if(commandNum == 0){ //da inserire 2 per il riavvia ora
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed)
                subState = 0;
        }
    }

    public void optionsControl(int frameX, int frameY){

        int textX;
        int textY;

        String text;
        if(gp.language == 1)
            text = "Key Bindings";
        else
            text = "Comandi";
        textX = centerText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        if(gp.language == 1) {
            text = "Movement";
            textX = frameX + gp.tileSize;
            textY += gp.tileSize;
            g2.drawString(text, textX, textY);
            textY += gp.tileSize;
            text = "Attack";
            g2.drawString(text, textX, textY);
            textY += gp.tileSize;
            text = "Confirm";
            g2.drawString(text, textX, textY);
            textY += gp.tileSize;
            text = "Inventory";
            g2.drawString(text, textX, textY);
            textY += gp.tileSize;
            text = "Pause";
            g2.drawString(text, textX, textY);
            textY += gp.tileSize;
            text = "Settings";
            g2.drawString(text, textX, textY);

            textX = frameX + gp.tileSize * 6;
            textY = frameY + gp.tileSize * 2;
            g2.drawString("W A S D", textX, textY);
            textY += gp.tileSize;
            text = "Space";
            g2.drawString(text, textX, textY);
            textY += gp.tileSize;
            text = "Enter";
            g2.drawString(text, textX, textY);
            textY += gp.tileSize;
            g2.drawString("C", textX, textY);
            textY += gp.tileSize;
            g2.drawString("P", textX, textY);
            textY += gp.tileSize;
            g2.drawString("Esc", textX, textY);
        }

        else{

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
        }

        //INDIETRO
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        if(gp.language == 1)
            text = "Back";
        else{
            text = "Indietro";
        }
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    public void optionsLanguage(int frameX, int frameY){

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        String text;
        if(gp.language == 1)
            text = "Choose the language you want to play with";
        else
            text = "Scegli la lingua con cui vuoi giocare";

        currentDialog = text;

        for(String line: currentDialog.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //TODO SE RIUSCIAMO, FAR PARTIRE IL COMMANDNUM SULLA LINGUA SELEZIONATA

        //Italiano
        text = "Italiano";
        textX = centerText(text);
        textY = gp.tileSize * 7;
        g2.drawString(text, textX, textY);

        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                gp.language = 0;
                subState = 0;
                commandNum = 0;
            }
        }

        //Inglese
        text = "English";
        textX = centerText(text);
        textY = gp.tileSize * 8;
        g2.drawString(text, textX, textY);

        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                gp.language = 1;
                subState = 0;
                commandNum = 0;
            }
        }

        //INDIETRO
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        if(gp.language == 1)
            text = "Back";
        else
            text = "Indietro";
        g2.drawString(text, textX, textY);
        if(commandNum == 2){
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
        String text;
        if(gp.language == 1)
            text = "Are you sure\nyou want to quit the game?";
        else
            text = "Sei sicuro \ndi voler abbandonare la partita?";
        currentDialog = text;

        for(String line: currentDialog.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //SI
        if (gp.language == 1)
            text = "Yes";
        else
            text = "Si";
        textX = centerText(text);
        textY = gp.tileSize * 7;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed){
                subState = 0;
                titleScreenState = 0;
                gp.keyH.changeMusic(0);
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }

        //NO
        text = "No";
        textX = centerText(text);
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
            gp.changeArea();
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
        String text;
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

        if(gp.language == 1)
            text = "Buy";
        else
            text = "Compra";
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed)
                subState = 1;
        }
        if(gp.language == 1)
            text = "Sell";
        else
            text = "Vendi";
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed)
                subState = 2;
        }

        y += gp.tileSize;
        if(gp.language == 1)
            text = "Exit";
        else
            text = "Esci";
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                if(gp.language == 1)
                    currentDialog = "Come back, ahah!";
                else
                    currentDialog = "Torna eh, ahah!";
            }
        }
    }

    public void trade_buy(){

        String text;

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

        if(gp.language == 1)
            text = "[ESC] Back";
        else
            text = "[ESC] Indietro";
        g2.drawString(text, x + 24, y + 60);

        //DISEGNO MONETE GIOCATORE
        x = gp.tileSize*12;
        drawSubWindow(x, y, width, height);

        if(gp.language == 1)
            text = "Coins: ";
        else
            text = "Monete: ";
        g2.drawString(text, x + 24, y + 60);

        g2.drawString(text + gp.player.coin, x + 24, y + 60);

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
            String text2 = "" + price;
            x = alignRightText(text2, gp.tileSize * 8 - 20);
            g2.drawString(text2, x, y + 32);

            //ACQUISTO
            if(gp.keyH.enterPressed){
                if(npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    gp.gameState = gp.dialogueState;

                    if(gp.language == 1)
                        text2 = "You need more coins!";
                    else
                        text2 = "Ti servono più monete!";
                    g2.drawString(text, x + 24, y + 60);

                    currentDialog = text2;
                    drawDialogueScreen();
                }
                else if(gp.player.inventory.size() == gp.player.maxInventorySize){
                    subState = 0;
                    gp.gameState = gp.dialogueState;

                    if(gp.language == 1)
                        text2 = "You're out of space!";
                    else
                        text2 = "Non hai più spazio!";
                    g2.drawString(text, x + 24, y + 60);

                    currentDialog = text2;
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

        String text;

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

        if(gp.language == 1)
            text = "[ESC] Back";
        else
            text = "[ESC] Indietro";
        g2.drawString(text, x + 24, y + 60);

        g2.drawString(text, x + 24, y + 60);

        //DISEGNO MONETE GIOCATORE
        x = gp.tileSize*12;
        drawSubWindow(x, y, width, height);

        if(gp.language == 1)
            text = "Coins: ";
        else
            text = "Monete: ";
        g2.drawString(text, x + 24, y + 60);

        g2.drawString(text + gp.player.coin, x + 24, y + 60);

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
            String text2 = "" + price;
            x = alignRightText(text2, gp.tileSize * 18 - 20);
            g2.drawString(text2, x, y + 32);

            //VENDITA
            if(gp.keyH.enterPressed){

                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;

                    if(gp.language == 1)
                        text2 = "You can't sell an equipped object!";
                    else
                        text2 = "Non puoi vendere un oggetto equipaggiato!";
                    g2.drawString(text, x + 24, y + 60);

                    currentDialog = text2;
                }
                else{
                    gp.player.inventory.remove(itemIndex);
                    gp.player.coin += price;
                }
            }
        }
    }

    public void drawSleepScreen(){

        counter++;

        if(counter < 120){
            gp.eManager.lighting.filterAlpha += 0.01f;
            if(gp.eManager.lighting.filterAlpha > 1f)
                gp.eManager.lighting.filterAlpha = 1f;
        }

        if(counter >= 120){
            gp.eManager.lighting.filterAlpha -= 0.01f;
            if(gp.eManager.lighting.filterAlpha <= 0f) {
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.eManager.lighting.dayCounter = 0;
                gp.gameState = gp.playState;
            }
        }
    }

    public int cont = 0, min = 0, hours = 8;
    String smin = null, shours = null;

    public void drawClock(){

        if(cont < 60) {
            cont++;
        }
        else {
            cont = 0;
            min++;
        }

        if(min >= 60){
            min = 0;
            hours++;
        }

        if(hours >= 24)
            hours = 0;

        if(min <= 9){
            smin = "0" + min;
        }
        else
            smin = "" + min;
        if(hours <= 9){
            shours = "0" + hours;
        }
        else
            shours = "" + hours;
        g2.drawString(shours + " : " + smin, gp.tileSize * 18 + 32, gp.tileSize * 5 - 8);

    }

    public boolean twoHours = false, threeHours = false;

    public void changeDayMusic(){

        String text = "";

        if(hours >= 8 && hours < 9 || hours >= 13 && hours < 14 || hours >= 21 && hours < 22){
            twoHours = false;
            threeHours = false;
            gp.keyH.changeMusic(2);
        }

        if(hours >= 9 && hours < 10 || hours >= 12 && hours < 13 || hours >= 20 && hours < 21){
            twoHours = false;
            threeHours = false;
            gp.keyH.changeMusic(3);
        }

        if(hours >= 10 && hours < 12){
            twoHours = true;
            threeHours = false;
            gp.keyH.changeMusic(4);
        }

        if(hours >= 17 && hours < 20){
            twoHours = false;
            threeHours = true;
            gp.keyH.changeMusic(4);
        }

        if(hours >= 14 && hours < 16){
            twoHours = true;
            threeHours = false;
            gp.keyH.changeMusic(5);
        }

        if(hours >= 16 && hours < 17){
            twoHours = false;
            threeHours = false;
            gp.keyH.changeMusic(6);
        }

        if(hours >= 22){
            twoHours = false;
            threeHours = false;
            gp.keyH.changeMusic(7);
        }
        g2.drawString(text, gp.tileSize * 16 + 32, gp.tileSize * 5 - 8);
    }

    public void changeDayText(){

        String text = "";

        if(hours >= 8 && hours < 9 || hours >= 13 && hours < 14 || hours >= 21 && hours < 22){

            if(gp.language == 1)
                text = "Rollcall";
            else
                text = "Appello";
        }

        if(hours >= 9 && hours < 10 || hours >= 12 && hours < 13 || hours >= 20 && hours < 21){

            if(gp.language == 1) {
                if (hours == 9)
                    text = "Breakfast";
                else if(hours == 12)
                    text = "Lunch";
                else if(hours == 20)
                    text = "Dinner";
            }
            else {
                if (hours == 9)
                    text = "Colazione";
                else if (hours == 12)
                    text = "Pranzo";
                else if (hours == 20)
                    text = "Cena";
            }
        }

        if(hours >= 10 && hours < 12 || hours >= 17 && hours < 20){

            if(gp.language == 1)
                text = "Free Time";
            else
                text = "Tempo Libero";
        }

        if(hours >= 14 && hours < 16){

            if(gp.language == 1)
                text = "Workout";
            else
                text = "Allenamento";
        }

        if(hours >= 16 && hours < 17){

            if(gp.language == 1)
                text = "Shower";
            else
                text = "Doccia";
        }

        if(hours >= 22 || hours < 8){

            if(gp.language == 1)
                text = "Lights Out";
            else
                text = "Luci Spente";

        }
        g2.drawString(text, gp.tileSize * 16 - 8, gp.tileSize * 5 - 8);
    }
}
