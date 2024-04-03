package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
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
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getPlayerAttackImage(){
        attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);

       /*try {
            attackUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_attack_up_1.png")));
            attackUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_attack_up_2.png")));
            attackDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_attack_down_1.png")));
            attackDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_attack_down_2.png")));
            attackLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_attack_left_1.png")));
            attackLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_attack_left_2.png")));
            attackRight1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_attack_right_1.png")));
            attackRight2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_attack_right_2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }*/
    }

    public void update(){
        if (attacking){
            attacking();
        }

        else if(keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
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


            if (!collisionOn && !keyH.enterPressed) {
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
            gp.keyH.enterPressed = false;
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

    public void attacking(){
        spriteCounter++;
        if (spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <=25){
            spriteNum = 2;
            int currentWorldX = worldX, currentWorldY = worldY;
            int solidAreaWidth = solidArea.width, solidAreaHeight = solidArea.height;

            switch (direction){
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
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

    public void interactNPC(int index) {
        if (gp.keyH.enterPressed) {
            if (index != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
            else {
                attacking = true;
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

    public void damageMonster(int i){
        if (i != 999){
            if (!gp.monster[i].invincible){
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                if (gp.monster[i].life <= 0){
                    gp.monster[i] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX, tempScreenY = screenY;
        switch(direction){
            case "up":
                if (!attacking) {
                    if (spriteNum == 1)
                        image = up1;
                    if (spriteNum == 2)
                        image = up2;
                }
                if (attacking){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1)
                        image = attackUp1;
                    if (spriteNum == 2)
                        image = attackUp2;
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1)
                        image = down1;
                    if (spriteNum == 2)
                        image = down2;
                }
                if (attacking)
                {
                    tempScreenY = screenY + gp.tileSize;
                    if (spriteNum == 1)
                        image = attackDown1;
                    if (spriteNum == 2)
                        image = attackDown2;
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
                }
                if (attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1)
                        image = attackLeft1;
                    if (spriteNum == 2)
                        image = attackLeft2;
                }
                break;
            case "right":
                if (!attacking) {
                    if(spriteNum == 1)
                        image = right1;
                    if(spriteNum == 2)
                        image = right2;
                }
                if (attacking){
                    tempScreenX = screenX + gp.tileSize;
                    if(spriteNum == 1)
                        image = attackRight1;
                    if(spriteNum == 2)
                        image = attackRight2;
                }
                break;
        }
        g2.drawImage(image, tempScreenX, tempScreenY, gp.tileSize, gp.tileSize, null);
    }
}
