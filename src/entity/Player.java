package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX, screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);

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
        speed = 4;
        direction = "down";
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
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

            //CONTROLLO COLLISIONI NPC
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CONTROLLO COLLISIONI OGGETTI
            int objectIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            //CONTROLLO COLLISIONI MOSTRI
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //CONTROLLO EVENTI
            gp.eHandler.checkEvent();
            gp.keyH.enterPressed = false;

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
            if (spriteCounter > 10) { //FIXATO PORCODIO
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(invincible == true){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpObject(int index){
        if(index != 999){
            /*String objectName = gp.obj[index].name;
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
            }*/
        }
    }

    public void interactNPC(int index){
        if(index != 999){
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
        }
    }

    public void contactMonster(int index){
        if(index != 999){
            if(invincible == false){
                life--;
                invincible = true;
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
