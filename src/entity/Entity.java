package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Entity {

    GamePanel gp;

    public int worldX, worldY;
    public int speed;
    boolean hpBarOn = false;
    int hpBarCounter = 0;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down";
    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter = 0 ;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean attacking = false;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public String dialogues[] = new String[20];
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    int dialogueIndex = 0;
    public int type;

    //stato del personaggio
    public int maxLife;
    public int life;
    public int level;
    public int strenght;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;

    //ATTRIBUTI OGGETTI
    public int attackValue;
    public int defenseValue;
    public String description = "";

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){

    }

    public void damageReaction(){

    }

    public void speak(){
        if (dialogues[dialogueIndex] == null ) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialog = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer){
            if(!gp.player.invincible){
                gp.playSoundEffect(7);
                int damage = attack - gp.player.defense;
                if (damage < 0 ) {
                    damage = 0;
                }
                gp.player.life -= damage;
                gp.player.invincible = true;
            }
        }

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
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(invincible){
            invincibleCounter++;
            if (invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

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

            //barra vita mostri
            if (type == 2 && hpBarOn) {
                double oneScale = (double) gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;
                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
                g2.setColor(new Color(255, 0 ,30));
                if(hpBarValue > 0)
                    g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                hpBarCounter++;
                if (hpBarCounter >= 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible)
            {
                hpBarOn = true;
                hpBarCounter = 0;
            }

            if (dying){
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    public BufferedImage setup(String imagePath, int w, int h){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, w, h);
        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        if (dyingCounter <= 5){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > 5 && dyingCounter <= 10){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > 10 && dyingCounter <= 15){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > 15 && dyingCounter <= 20){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > 20 && dyingCounter <= 25){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > 25 && dyingCounter <= 30){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > 30 && dyingCounter <= 35){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > 35 && dyingCounter <= 40){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > 40){
            alive = false;
            dying = false;
        }
    }
    public  void changeAlpha(Graphics2D g2, float alpha){
        if (dyingCounter > 35 && dyingCounter <= 40){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        }
    }
}
