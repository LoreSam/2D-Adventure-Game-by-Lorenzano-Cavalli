package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        dialogueSet = -1;

        getNPCImage();
        setDialog();
    }

    public void getNPCImage(){
        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialog(){

        dialogues[0][0]="Ciao!";
        dialogues[0][1]="Quindi sei venuto su quest'isola \nper trovare il tesoro?";
        dialogues[0][2]="Un tempo ero un bravo mago";
        dialogues[0][3]="Buona fortuna!";

        dialogues[1][0]="Ricorda:";
        dialogues[1][1]="Se hai paura di qualcosa\nstudiala!";
        dialogues[1][2]="I consigli migliori";
        dialogues[1][3]="Diocane!";
    }

    public void setAction(){
        if (onPath){
            //int goalCol = 12, goalRow = 9;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
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
    public void speak(){

        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        //TODO in base alla condizione, settare il set di dialoghi che si vuole
        if(dialogues[dialogueSet][0] == null){

            dialogueSet = 0;
        }

        onPath = true;
    }
}
