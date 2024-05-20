package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX, screenY;
    public boolean lightUpdated = false;
    public int speed_cont = 0;

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

/*        attackArea.width = 36;
        attackArea.height = 36;*/

        setDefaultValues();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize * 54;
        worldY = gp.tileSize * 13;
        defaultSpeed = 6; //TODO SPEED A 3
        speed = defaultSpeed;
        direction = "down";

        level = 1;
        maxLife = 100;
        life = maxLife;
        strength = 10; // + FORZA = + DANNO CAUSATO
        //dexterity = 1; // + DESTREZZA HA = - DANNO RICEVUTO (CAMBIA NOME POI PORCOIDIO)
        exp = 0;
        nextLevelExp = 5;
        coin = 500;
        maxEnergy = 100;
        energy = maxEnergy;
        ammo = 10;
        currentWeapon = new OBJ_Sword_Normal(gp);
        //currentWeapon = new OBJ_Axe(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        currentLight = new OBJ_Lantern(gp);
        //projectile = new OBJ_Rock(gp);
        attack = getAttack();
        defense = getDefense();

        getImage();
        getAttackImage();
        //getGuardImage();
        setItems();
        setDialogue();
    }

    public void setDefaultPosition(){

        worldX = gp.tileSize * 53;
        worldY = gp.tileSize * 12;
        direction = "down";
    }

    public void restoreStatistics(){

        life = maxLife;
        energy = maxEnergy;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
    }

    public void setItems(){

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Scissors(gp));
        inventory.add(new OBJ_Stick(gp));
        inventory.add(new OBJ_Rock(gp));
    }

    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentWeapon){
                currentWeaponSlot = i;
            }
        }

        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot(){
        int curretShieldSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentShield){
                curretShieldSlot = i;
            }
        }

        return curretShieldSlot;
    }

    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getImage(){
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage(){

        attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
    }

    /*public void getGuardImage(){

        guardUp = setup("/player/boy_guard_up", gp.tileSize, gp.tileSize);
        guardDown = setup("/player/boy_guard_down", gp.tileSize, gp.tileSize);
        guardLeft = setup("/player/boy_guard_left", gp.tileSize, gp.tileSize);
        guardRight = setup("/player/boy_guard_right", gp.tileSize, gp.tileSize);
    }*/

    public void update(){

        if(knockBack){

            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkObject(this, true);
            gp.cChecker.checkEntity(this, gp.monster);
            gp.cChecker.checkEntity(this, gp.iTile);

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
        else if(keyH.guardKeyPressed){
            guarding = true;
            guardCounter++;
        }
        else if(keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed || keyH.spacePressed) {

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

            //CONTROLLO COLLISIONI OGGETTI INTERATTIVI
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            contactDoor(iTileIndex);
            sleep(iTileIndex);


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
            guarding = false;
            guardCounter = 0;
            //gp.keyH.spacePressed = false;

            spriteCounter++;
            if (spriteCounter > 12) { //FIXATO PORCODIO
                eat(iTileIndex);
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            trainingCounter++;
            if(trainingCounter > 60){
                training(iTileIndex);
                trainingCounter = 0;
            }
        }

        /*spriteCounter++;
        if (spriteCounter > 12) { //se messo qua abbiamo il continuo aggiornamento del personaggio
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }*/


        /*if(gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)){
            //IMPOSTA COORDINATE, DIREZIONE E UTENTE DI DEFAULT
            projectile.set(worldX, worldY, direction, true, this);

            //SOTTRAZIONE COSTI
            projectile.subtractResource(this);

            //AGGIUNTA ALLA LISTA
            for(int i = 0; i < gp.projectile[1].length; i++){

                if(gp.projectile[gp.currentMap][i] == null){
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;

            //gp.playSoundEffect(11);
        }*/

        if(invincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }

        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if (energy > maxEnergy){
            energy = maxEnergy;
        }
        if (life > maxLife){
            life = maxLife;
        }

        if(life <= 0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSoundEffect(7);
        }
    }

    public void sleep(int i){
        if(i != 999){
            if(gp.keyH.interactKeyPressed && gp.iTile[gp.currentMap][i].type == type_bed){
                gp.iTile[gp.currentMap][i].sleep();
            }
        }
    }

    public void eat(int i){

        if(i != 999){
            if(gp.iTile[gp.currentMap][i].type == type_eatable && gp.keyH.interactKeyPressed) {
                gp.iTile[gp.currentMap][i].eat();
            }
        }
    }

    public void training(int i){

        if(i != 999){
            if(gp.iTile[gp.currentMap][i].type == type_training && gp.keyH.interactKeyPressed) {
                gp.iTile[gp.currentMap][i].training();
            }
        }
    }

    public void pickUpObject(int i){

        if(i != 999){

            //prendi solo oggetti
            if (gp.obj[gp.currentMap][i].type == type_pickUp) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }

            //oggetti nell'inventario
            else {
                String text;

                if(inventory.size() != maxInventorySize){

                    inventory.add(gp.obj[gp.currentMap][i]);
                    //gp.playSoundEffect(1);
                    text = "Hai ottenuto 1 " + gp.obj[gp.currentMap][i].name + "!";

                }
                else {
                    text = "Spazi esauriti!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
            gp.playSoundEffect(0);
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

    public void contactDoor(int i){

        //OSTACOLI
        if(i != 999){
            if (gp.iTile[gp.currentMap][i].collision && gp.iTile[gp.currentMap][i].type == type_obstacle){

                System.out.println("putt ana de armas");
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].doorOpened();
            }
        }
    }

    public void interactNPC(int index) {

        if (gp.keyH.enterPressed && index != 999) {
            gp.npc[gp.currentMap][index].speak();
        }
        else if(gp.keyH.spacePressed){
            gp.playSoundEffect(6);
            attacking = true;
        }
        //gp.npc[gp.currentMap][index].move(direction);
    }

    public void contactMonster(int index){
        if(index != 999){
            if(!invincible && !gp.monster[gp.currentMap][index].dying){
                int damage = gp.monster[gp.currentMap][index].attack - defense;
                if (damage < 1) {
                    damage = 1;
                }
                life -= damage;
                invincible = true;
                transparent = true;
            }
        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower){

        if (i != 999){

            if (!gp.monster[gp.currentMap][i].invincible){

                gp.playSoundEffect(2);

                if(knockBackCounter > 0)
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);

                if(gp.monster[gp.currentMap][i].offBalance){
                    attack *= 5;
                }

                int damage = attack - gp.monster[gp.currentMap][i].defense;

                if (damage < 0 ) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " danno!");
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();
                if (gp.monster[gp.currentMap][i].life <= 0){
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("mostro ucciso " + gp.monster[gp.currentMap][i].name);
                    gp.ui.addMessage("Exp " + gp.monster[gp.currentMap][i].exp);
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void damageInteractiveTile(int i){
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this) && !gp.iTile[gp.currentMap][i].invincible){
            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            //GENERA PARTICELLE
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].life <= 0) {
                gp.iTile[gp.currentMap][i].checkDrop(); //se volgiamo droppare qualcosa quando miniamo
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();

            }
        }
    }

    public void damageProjectile(int i){

        if(i != 999){
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp(){

        if (exp >= nextLevelExp){

            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife +=2;
            strength++;
            dexterity++;

            attack = getAttack();
            defense =  getDefense();

            //gp.playSoundEffect(8);
            gp.gameState = gp.dialogueState;

            setDialogue();
            startDialogue(this, 0);
        }
    }

    public void setDialogue(){

        dialogues[0][0] = "Ora sei livello "+level +" adesso \nsei piu forte";
    }

    public void selectItem(){

        int itemIndex = gp.ui.getItemIndexSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_pickaxe || selectedItem.type == type_scissors){
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }
            if (selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }

            /*if(selectedItem.type == type_light){
                if(currentLight == selectedItem){
                    currentLight = null;
                }
                else{
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }*/
            if (selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }

            /*if(selectedItem.type == type_stick){
                inventory.add(gp.obj[gp.currentMap][itemIndex]);
                selectedItem.inside_inventory = true;
            }*/
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
                if (guarding){
                    image = guardUp;
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
                if (guarding){
                    image = guardDown;
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
                if (guarding){
                    image = guardLeft;
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
                if (guarding){
                    image = guardRight;
                }
                break;
        }

        if(transparent)
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));

        g2.drawImage(image, tempScreenX, tempScreenY, null); //TECNICAMENTE NEL DRAW ANDREBBERO GP.TILESIZE, PERO FUNZIONA ANCHE COSI
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //g2.drawImage(image, screenX, screenY, null);
    }
}
