package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    public int shotAvailableCounter = 0;
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
    public BufferedImage image, image2, image3, image4, image5;
    public String name;
    public boolean collision = false;
    int dialogueIndex = 0;
    public boolean onPath = false;

    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickUp = 7;

    //stato del personaggio
    public int maxLife;
    public int life;
    public int maxEnergy;
    public int energy;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    //ATTRIBUTI OGGETTI
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;

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
    public void use(Entity entity){}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for (int i = 0; i<gp.obj[1].length; i++){
            if (gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor(){
        Color color = null;
        return color;
    }

    public int getParticleSize(){
        int size = 0; //6 PIXEL
        return size;
    }

    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }

    public int getParticleMaxlife(){
        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxlife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);

        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public void checkCollision(){
        collision = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if (type == type_monster && contactPlayer){
            damagePlayer(attack);
        }
    }

    public void update(){
        setAction();
        checkCollision();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer){
            damagePlayer(attack);
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
        if (spriteCounter > 24) {
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

        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    }

    public void damagePlayer(int attack){
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
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            if (dying){
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
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

        int i = 10;

        if (dyingCounter <= i){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i*2){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i*2 && dyingCounter <= i*3){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i*3 && dyingCounter <= i*4){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i*4 && dyingCounter <= i*5){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i*5 && dyingCounter <= i*6){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i*6 && dyingCounter <= i*7){
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i*7 && dyingCounter <= i*8){
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i*8){
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alpha){
        if (dyingCounter > 35 && dyingCounter <= 40){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        }
    }
    public void searchPath(int goalCol, int goalRow){
        int startCol = (worldX + solidArea.x)/gp.tileSize, startRow = (worldY + solidArea.y)/gp.tileSize;
        gp.pFinder.setNode(startCol, startRow, goalCol, goalRow);
        if (gp.pFinder.search()){
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextX + gp.tileSize){
                if (enLeftX > nextX){
                    direction = "right";
                }
                if (enLeftX > nextX){
                    direction = "left";
                }
            }
            else  if (enTopY > nextY && enLeftX > nextX){
                direction = "up";
                checkCollision();
                if (collisionOn){
                    direction = "left";
                }
            }
            else  if (enTopY > nextY && enLeftX < nextX){
                direction = "up";
                checkCollision();
                if (collisionOn){
                    direction = "right";
                }
            }
            else  if (enTopY < nextY && enLeftX > nextX){
                direction = "down";
                checkCollision();
                if (collisionOn){
                    direction = "left";
                }
            }
            else  if (enTopY < nextY && enLeftX < nextX){
                direction = "down";
                checkCollision();
                if (collisionOn){
                    direction = "right";
                }
            }
        }
        /* TODO da attivare se si vuole raggiungere una posizione se si vuole seguire il player no
        int nextCol = gp.pFinder.pathList.get(0).col , nextRow = gp.pFinder.pathList.get(0).row;
        if (nextCol == goalCol && nextRow == goalRow){
            onPath = false;
        }*/
    }
}
