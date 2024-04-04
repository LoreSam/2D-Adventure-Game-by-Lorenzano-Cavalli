package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed;
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
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 1) {
                        //TODO carica partita
                    }
                    if (gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
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
                        cambiaMusica(1);
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
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            if(code == KeyEvent.VK_SPACE){
                spacePressed = true;
            }
        }
        //stato di pausa
        else if (gp.gameState == gp.pauseState)
        {
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
            }
        }
        ///stato di dialogo
        else if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
    }

    public void cambiaMusica(int i){
        gp.stopMusic();
        gp.playMusic(i);
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
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //NON UTILIZZATO
    }
}
