package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Energy;
import object.OBJ_Heart;

import java.util.Random;

public class NPC_Prisoner extends Entity {

    GamePanel gp;

    public NPC_Prisoner(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Prigioniero";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 100;
        life = maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attack = 5;
        defense = 0;
        knockBackPower = 5;

        getImage(gp);
    }

    public void getImage(GamePanel gp){

        up1 = setup("/npc/prisoner_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/prisoner_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/prisoner_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/prisoner_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/prisoner_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/prisoner_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/prisoner_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/prisoner_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){

        if(onPath){

            checkStopChasing(gp.player, 15, 100);

            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            /*int i = new Random().nextInt(200)+1;
            if(i > 197 && !projectile.alive && shotAvailableCounter == 30){
                projectile.set(worldX, worldY, direction, true, this);

                for(int j = 0; j < gp.projectile[1].length; j++){
                    if(gp.projectile[gp.currentMap][j] == null){
                        gp.projectile[gp.currentMap][j] = projectile;
                        break;
                    }
                }

                shotAvailableCounter = 0;
            }*/
        }
        else{

            checkStartChasing(gp.player, 5, 100);

            getRandomDirection();
        }

        if(!attacking){
            checkAttack(30, gp.tileSize*4, gp.tileSize);
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
            dropItem(new OBJ_Coin(gp));
        }
        if (i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100){
            dropItem(new OBJ_Energy(gp));
        }
    }
}
