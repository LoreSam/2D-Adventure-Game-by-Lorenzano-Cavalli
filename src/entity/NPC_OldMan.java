package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{

    public static final String npcName = "Old Man";

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        name = npcName;

        dialogueSet = 0;

        getNPCImage();
        setDialogue();
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

    public void setDialogue(){

        dialogues[0][0] = "Benvenuto in prigione! \n(ENTER per passare al prossimo dialogo...)";
        dialogues[0][1] = "Prima di cominciare, credo che tu abbia bisogno \ndi qualche dritta per scappare...";
        dialogues[0][2] = "Per muoverti utilizza i tasti WASD";
        dialogues[0][3] = "Con il tasto E puoi aprire l'inventario \ne con il tasto C il menù di crafting.";
        dialogues[0][4] = "Con il tasto F interagisci con i vari elementi \nsulla mappa.";
        dialogues[0][5] = "Se premi SPAZIO puoi picchiare i neg.. \nehm gli altri detenuti e utilizzare gli utensili.";
        dialogues[0][6] = "Direi che possiamo cominciare! \nBuona fu.. ehm, detenzione...";
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
