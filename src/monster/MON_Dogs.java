package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Dogs extends Entity {

    public MON_Dogs(GamePanel gp) {
        super(gp);

        type = 2;
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
    }
}
