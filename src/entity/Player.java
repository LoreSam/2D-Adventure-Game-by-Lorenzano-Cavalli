package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX, screenY;
    public int hasKey = 0; //numero di chiavi che il giocatore ha

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 24;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 2;
        direction = "down";
    }

    public void getPlayerImage(){
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void update(){
        if(keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            //CONTROLLO COLLISIONI TILE
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CONTROLLO COLLISIONI OGGETTI
            int objectIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter < 12) { //anche > 0, è comunque da fixare
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
            }
            spriteCounter = 0;
        }
    }

    public void pickUpObject(int index){
        if(index != 999){
            String objectName = gp.obj[index].name;
            switch(objectName){
                case "Key":
                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.obj[index] = null;
                    gp.ui.showMessage("Hai ottenuto una chiave!");
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.playSoundEffect(3);
                        gp.obj[index] = null;
                        hasKey--;
                        gp.ui.showMessage("Hai aperto una porta!");
                    }
                    else{
                        gp.ui.showMessage("Ti serve una chiave!");
                    }
                    break;
                case "Boots":
                    gp.playSoundEffect(2);
                    speed += 1;
                    gp.obj[index] = null;
                    gp.ui.showMessage("Più veloce!");
                    break;
                case "Chest":
                    gp.ui.gameDone = true;
                    gp.stopMusic();
                    gp.playSoundEffect(4);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){
        /*g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);*/

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1)
                    image = up1;
                if(spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if(spriteNum == 1)
                    image = down1;
                if(spriteNum == 2)
                    image = down2;
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                if(spriteNum == 2)
                    image = left2;
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;
                if(spriteNum == 2)
                    image = right2;
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
