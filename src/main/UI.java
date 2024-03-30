package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font font, congratulazioniB;
    BufferedImage keyImage;

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
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if(gameDone){

            g2.setFont(font);
            g2.setColor(Color.WHITE);

            String text;
            int textLenght;
            int x, y;

            text = "Hai trovato il tesoro!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Tempo in gioco: " + dFormat.format(playTime) + "!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            g2.setFont(congratulazioniB);
            g2.setColor(Color.YELLOW);

            text = "Congratulazioni!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }
        else{
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            //TEMPO
            playTime += (double) 1/144; //il denominatore DEVE essere = al n di FPS
            g2.drawString("Tempo: " + dFormat.format(playTime), gp.tileSize*11, 65);

            //MESSAGGIO
            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(20f));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*2);

                messageCounter++;
                if(messageCounter > 240) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
