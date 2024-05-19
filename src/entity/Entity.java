package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Entity {

    GamePanel gp;

    public int worldX, worldY;
    public int speed;
    boolean hpBarOn = false;
    int hpBarCounter = 0;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;
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
    public String dialogues[][] = new String[20][20];
    public Entity attacker;
    public BufferedImage image, image2, image3, image4, image5;
    public String name;
    public boolean collision = false;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean onPath = false;
    public boolean knockBack = false;
    int knockBackCounter = 0;
    public int knockBackPower = 0;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public int guardCounter = 0;
    int offBalanceCounter = 0;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened;
    public Entity linkedEntity;
    public boolean sleep = false;
    public boolean stickIn = false;
    public boolean rockIn = false;

    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickUp = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_bed = 10;
    public final int type_pickaxe = 11;
    public final int type_stick = 12;
    public final int type_rock = 13;
    public final int type_scissors = 14;

    //stato del personaggio
    public int maxLife;
    public int defaultSpeed;
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
    public Entity currentLight;
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
    public int lightRadius;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void resetCounter(){

        spriteCounter = 0;
        actionLockCounter = 0;
        offBalanceCounter = 0;
        guardCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        invincibleCounter = 0;
        knockBackCounter = 0;
        shotAvailableCounter = 0;
    }

    public void setLoot(Entity loot){

    }

    public void drawGrateBar(){

    }

    public int getXDistance(Entity target){

        int xDistance = Math.abs(worldX - gp.player.worldX);
        return xDistance;
    }

    public int getYDistance(Entity target){

        int yDistance = Math.abs(worldY - gp.player.worldY);
        return yDistance;
    }

    public int getTileDistance(Entity target){

        int tileDistance = (getXDistance(target) + getYDistance(target))/gp.tileSize;
        return tileDistance;
    }

    public int getGoalCol(Entity target){

        int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
        return goalCol;
    }

    public int getGoalRow(Entity target){

        int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
        return goalRow;
    }

    public void setAction(){

    }

    public void damageReaction(){

    }

    public void move (String direction){

    }

    public void speak(){}

    public void facePlayer(){

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

    public void startDialogue(Entity entity, int setNum){

        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }

    public void interact(){

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

        if(knockBack){

            checkCollision();

            if(collisionOn){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else {
                switch (knockBackDirection){
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

            knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        else if(attacking){
            attacking();
        }
        else{
            setAction();
            checkCollision();

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

        if(offBalance){
            offBalanceCounter++;
            if(offBalanceCounter > 60){
                offBalance = false;
                offBalanceCounter = 0;
            }
        }
    }

    public void checkAttack(int rate, int straight, int horizontal){

        boolean targetInRange = false;
        int xDis = getXDistance(gp.player);
        int yDis = getYDistance(gp.player);

        switch(direction){
            case "up":
                if(gp.player.worldY < worldY && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "down":
                if(gp.player.worldY > worldY && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "left":
                if(gp.player.worldX < worldX && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "right":
                if(gp.player.worldX > worldX && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
        }

        if(targetInRange){

            int i = new Random().nextInt(rate);
            if(i == 0){
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    public void checkStartChasing(Entity target, int distance, int rate){

        if(getTileDistance(target) < distance){
            int i = new Random().nextInt(rate);
            if(i == 0)
                onPath = true;
        }
    }

    public void checkStopChasing(Entity target, int distance, int rate){

        if(getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if(i == 0)
                onPath = false;
        }
    }

    public void getRandomDirection(){

        actionLockCounter++;

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100) + 1; //numero random

            if(i <= 25)
                direction = "up";
            if(i > 25 && i <= 50)
                direction = "down";
            if(i > 50 && i <= 75)
                direction = "left";
            if(i > 75 && i <= 100)
                direction = "right";

            actionLockCounter = 0;
        }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower){

        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public String getOppositeDirection(String direction){

        String oppositeDirection = "";

        switch(direction){
            case "up":
                oppositeDirection = "down";
                break;
            case "down":
                oppositeDirection = "up";
                break;
            case "left":
                oppositeDirection = "right";
                break;
            case "right":
                oppositeDirection = "left";
                break;
        }

        return oppositeDirection;
    }

    public void attacking(){

        spriteCounter++;

        if (spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 30){
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

            if(type == type_monster){

                if(gp.cChecker.checkPlayer(this)){
                    damagePlayer(attack);
                }
            }
            else{
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, 5);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 30){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damagePlayer(int attack){

        if(!gp.player.invincible){

            int damage = attack - gp.player.defense;

            String canGuardDirection = getOppositeDirection(direction);

            if(gp.player.guarding && gp.player.direction.equals(canGuardDirection)){

                if(gp.player.guardCounter < 10){
                    damage = 0;
                    gp.playSoundEffect(3);
                    setKnockBack(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter -= 60;
                }
                else{
                    damage /= 3;
                    gp.playSoundEffect(4);
                }
            }
            else{
                gp.playSoundEffect(6);
                if(damage < 1)
                    damage = 1;
            }

            if(damage != 0){
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower);
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
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

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            int tempScreenX = screenX;
            int tempScreenY = screenY;

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
                        //tempScreenY = screenY + gp.tileSize;
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
                        //tempScreenX = screenX - gp.tileSize*2;
                        if(spriteNum == 1)
                            image = attackRight1;
                        if(spriteNum == 2)
                            image = attackRight2;
                    }
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

            g2.drawImage(image, tempScreenX, tempScreenY, null); //TECNICAMENTE NEL DRAW ANDREBBERO GP.TILESIZE, PERO FUNZIONA ANCHE COSI
            changeAlpha(g2, 1F);
        }
    }
}
