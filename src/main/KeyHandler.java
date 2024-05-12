package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, shotKeyPressed, guardKeyPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //SCHERMATA DEL TITOLO
        if(gp.gameState == gp.titleState) {

            if (gp.ui.titleScreenState == 0) {
                titleState(code);
            }
            else if(gp.ui.titleScreenState == 1){
                if (code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0)
                        gp.ui.commandNum = 3;
                }
                if (code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3)
                        gp.ui.commandNum = 0;
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        System.out.println("Perfettino Pelatino");
                        gp.gameState = gp.playState;
                        cambiaMusica(2);
                    }
                    if (gp.ui.commandNum == 1) {
                        System.out.println("Cancro");
                        gp.gameState = gp.playState;
                        cambiaMusica(1);
                    }
                    if (gp.ui.commandNum == 2) {
                        System.out.println("Labbro pazzo");
                        gp.gameState = gp.playState;
                        cambiaMusica(1);
                    }
                    if (gp.ui.commandNum == 3){
                        System.out.println("Sei tornato indietro");
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
        }

        //stato di gioco
        else if (gp.gameState == gp.playState){
            playState(code);
        }

        //stato di pausa
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }

        //stato di dialogo
        else if (gp.gameState == gp.dialogueState){
            dialogState(code);
        }

        //STATO INVENTARIO
        else if (gp.gameState == gp.characterState){
            characterState(code);
        }

        //STATO OPZIONI
        else if (gp.gameState == gp.optionsState){
            optionState(code);
        }

        //STATO GAMEOVER
        else if (gp.gameState == gp.gameOverState){
            gameOverState(code);
        }

        //STATO SCAMBIO
        else if (gp.gameState == gp.tradeState){
            tradeState(code);
        }

        //STATO MAPPA
        else if (gp.gameState == gp.mapState){
            mapState(code);
        }
    }

    public void titleState(int code){
        if (code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0)
                gp.ui.commandNum = 2;
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2)
                gp.ui.commandNum = 0;
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                cambiaMusica(1);
            }
            if (gp.ui.commandNum == 1) {
                gp.saveLoad.load();
                gp.gameState = gp.playState;
                cambiaMusica(1);
            }
            if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int code){

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }

        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_M){
            gp.gameState = gp.mapState;
        }
        if(code == KeyEvent.VK_Q){
            guardKeyPressed = true;
        }

        hotBar(code);
    }

    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }

    public void dialogState(int code){
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }

    public void mapState(int code){

        if(code == KeyEvent.VK_M){
            gp.gameState = gp.mapState;
        }
    }

    public void characterState(int code){

        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }

        if (code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }

        playerInventory(code);
    }

    public void cambiaMusica(int i){
        gp.stopMusic();
        gp.playMusic(i);
    }

    public void optionState(int code){

        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }

        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch(gp.ui.subState){
            case 0:
                maxCommandNum = 5;
                break;
            case 3:
                maxCommandNum = 1;
                break;
        }

        if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            gp.playSoundEffect(8);
            if(gp.ui.commandNum < 0)
                gp.ui.commandNum = maxCommandNum;
        }

        if(code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            gp.playSoundEffect(8);
            if(gp.ui.commandNum > maxCommandNum)
                gp.ui.commandNum = 0;
        }

        if(code == KeyEvent.VK_LEFT){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSoundEffect(8);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0){
                    gp.se.volumeScale--;
                    gp.playSoundEffect(8);
                }
            }
        }

        if(code == KeyEvent.VK_RIGHT){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 10){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSoundEffect(8);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale < 10){
                    gp.se.volumeScale++;
                    gp.playSoundEffect(8);
                }
            }
        }
    }

    public void gameOverState(int code){

        if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0)
                gp.ui.commandNum = 1;
            gp.playSoundEffect(8);
        }

        if(code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1)
                gp.ui.commandNum = 0;
            gp.playSoundEffect(8);
        }

        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.resetGame(false);
                gp.playMusic(1);
            }
            else if(gp.ui.commandNum == 1){
                gp.ui.titleScreenState = 0;
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
    }

    public void tradeState(int code){

        if(code == KeyEvent.VK_ENTER)
            enterPressed = true;

        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0)
                    gp.ui.commandNum = 2;
            }
            gp.playSoundEffect(8);
            if(code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2)
                    gp.ui.commandNum = 0;
            }
            gp.playSoundEffect(8);
        }

        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE)
                gp.ui.subState = 0;
        }

        if(gp.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE)
                gp.ui.subState = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
        if(code == KeyEvent.VK_Q){
            guardKeyPressed = false;
        }
    }

    public void hotBar(int code){

        if(code == KeyEvent.VK_LEFT && gp.ui.playerSlotCol != 0){
            gp.ui.playerSlotCol--;
            gp.playSoundEffect(8);
        }

        if(code == KeyEvent.VK_RIGHT && gp.ui.playerSlotCol != 4){ //COLONNE INVENTARIO
            gp.ui.playerSlotCol++;
            gp.playSoundEffect(8);
        }
    }

    public void playerInventory(int code){

        if(code == KeyEvent.VK_UP && gp.ui.playerSlotRow != 0){
            gp.ui.playerSlotRow--;
            gp.playSoundEffect(8);
        }

        if(code == KeyEvent.VK_LEFT && gp.ui.playerSlotCol != 0){
            gp.ui.playerSlotCol--;
            gp.playSoundEffect(8);
        }

        if(code == KeyEvent.VK_DOWN && gp.ui.playerSlotRow != 4){ //RIGHE INVENTARIO
            gp.ui.playerSlotRow++;
            gp.playSoundEffect(8);
        }

        if(code == KeyEvent.VK_RIGHT && gp.ui.playerSlotCol != 4){ //COLONNE INVENTARIO
            gp.ui.playerSlotCol++;
            gp.playSoundEffect(8);
        }
    }

    public void npcInventory(int code){

        if(code == KeyEvent.VK_UP && gp.ui.npcSlotRow != 0){
            gp.ui.npcSlotRow--;
            gp.playSoundEffect(8);
        }

        if(code == KeyEvent.VK_LEFT && gp.ui.npcSlotCol != 0){
            gp.ui.npcSlotCol--;
            gp.playSoundEffect(8);
        }

        if(code == KeyEvent.VK_DOWN && gp.ui.npcSlotRow != 3){ //RIGHE INVENTARIO
            gp.ui.npcSlotRow++;
            gp.playSoundEffect(8);
        }

        if(code == KeyEvent.VK_RIGHT && gp.ui.npcSlotCol != 4){ //COLONNE INVENTARIO
            gp.ui.npcSlotCol++;
            gp.playSoundEffect(8);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //NON UTILIZZATO
    }
    /*
    public void move8(){ //TODO movimento diagonale
        try {
            while (true) {
                if (leftPressed && upPressed) {
                    lbl.setBounds(lbl.getX() - 3, lbl.getY() - 3, 20, 20);
                } else if (left && down) {
                    lbl.setBounds(lbl.getX() - 3, lbl.getY() + 3, 20, 20);
                } else if (right && up) {
                    lbl.setBounds(lbl.getX() + 3, lbl.getY() - 3, 20, 20);
                } else if (right && down) {
                    lbl.setBounds(lbl.getX() + 3, lbl.getY() + 3, 20, 20);
                } else if (left) {
                    lbl.setBounds(lbl.getX() - 3, lbl.getY(), 20, 20);
                } else if (up) {
                    lbl.setBounds(lbl.getX(), lbl.getY() - 3, 20, 20);
                } else if (right) {
                    lbl.setBounds(lbl.getX() + 3, lbl.getY(), 20, 20);
                } else if (down) {
                    lbl.setBounds(lbl.getX(), lbl.getY() + 3, 20, 20);
                }

                Thread.sleep(30);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }*/
}
