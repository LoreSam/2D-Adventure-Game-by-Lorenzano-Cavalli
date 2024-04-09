package monster;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
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

    public void getImage(GamePanel gp){
        down1 = setup("/dogs/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/dogs/greenslime_down_2", gp.tileSize, gp.tileSize);
        up1 = setup("/dogs/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/dogs/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/dogs/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/dogs/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/dogs/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/dogs/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){
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

        int i = new Random().nextInt(100)+1;
        if(i > 99 && !projectile.alive && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }
    @Override
    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;

    }
}
