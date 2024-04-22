package monster;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Energy;
import object.OBJ_Heart;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Dogs extends Entity {

    GamePanel gp;
    public MON_Dogs(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Dog";
        speed = 1;
        maxLife = 5;
        life = maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attack = 5;
        defense = 0;
        exp = 2;

        projectile = new OBJ_Rock(gp);

        getImage(gp);
    }

    public void update(){
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (!onPath && tileDistance < 5){
            int i = new Random().nextInt(100)+1;
            if (i > 50){
                onPath = true;
            }
        }
        if (onPath && tileDistance > 20){
            onPath = false;
        }
    }

    public void getImage(GamePanel gp){
        /*down1 = setup("/dogs/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/dogs/greenslime_down_2", gp.tileSize, gp.tileSize);
        up1 = setup("/dogs/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/dogs/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/dogs/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/dogs/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/dogs/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/dogs/greenslime_down_2", gp.tileSize, gp.tileSize);*/

        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){
        if (onPath){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
            int i = new Random().nextInt(200)+1;
            if(i > 197 && !projectile.alive && shotAvailableCounter == 30){
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotAvailableCounter = 0;
            }
        }
        else {
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
    }
    @Override
    public void damageReaction(){
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true;

    }
    public void checkDrop(){
        int i = new Random().nextInt(100)+1;
        if (i < 50){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100){
            dropItem(new OBJ_Energy(gp));
        }
    }
}
