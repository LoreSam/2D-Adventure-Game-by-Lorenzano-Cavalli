package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Guard extends Entity{

    public static final String npcName = "Guardia";

    public NPC_Guard(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        name = npcName;

        dialogueSet = 0;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage(){
        up1 = setup("/npc/guard_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/guard_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/guard_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/guard_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/guard_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/guard_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/guard_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/guard_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        if (gp.language == 1){
            dialogues[0][0] = "Welcome to prison! \n(PRESS ENTER to proceed to the next dialogue...)";
            dialogues[0][1] = "Before we start, I think you need \nsome tips to escape...";
            dialogues[0][2] = "Use the WASD keys to move";
            dialogues[0][3] = "Press E to open the inventory \nand C to open the crafting menu.";
            dialogues[0][4] = "Inside the inventories \nuse the directional arrows to move,\nand the key ENTER to select.";
            dialogues[0][5] = "Crafting: \n  [Axe: Stick + Rock]\n  [Pickaxe: Scotch]\n  [Scissors: Axe + Pickaxe]";
            dialogues[0][6] = "Press F to interact with various \nelements on the map.\n(Bed, Cafeteria, Gym, Shower)";
            dialogues[0][7] = "Press SPACE to hit the nig.. \nuhm other prisoners, and use tools.";
            dialogues[0][8] = "I think we may start! \nHave fun esc... ehm detention...";

            dialogues[1][0] = "Congratulations!\nYou made it out of here!)";
            dialogues[1][1] = "Be careful, it's not over yet...";
            dialogues[1][2] = "...commit any more crimes, and you're going straight back in!";
            dialogues[1][3] = "You have been for this time...";
            dialogues[1][4] = "Thanks for playing our game!";
        }
        else {
            dialogues[0][0] = "Benvenuto in prigione! \n(ENTER per passare al prossimo dialogo...)";
            dialogues[0][1] = "Prima di cominciare, credo che tu abbia bisogno \ndi qualche dritta per scappare...";
            dialogues[0][2] = "Per muoverti utilizza i tasti WASD";
            dialogues[0][3] = "Con il tasto E puoi aprire l'inventario \ne con il tasto C il menù di crafting.";
            dialogues[0][4] = "All'interno degli inventari \nusa le frecce direzionali per muoverti,\ne il tasto ENTER per selezionare.";
            dialogues[0][5] = "Crafting: \n  [Ascia: Stick + Roccia]\n  [Piccone: Scotch]\n  [Forbici: Ascia + Piccone]";
            dialogues[0][6] = "Con il tasto F interagisci con i vari elementi \nsulla mappa.\n(Letto, Mensa, Palestra, Doccia)";
            dialogues[0][7] = "Se premi SPAZIO puoi picchiare i neg.. \nehm gli altri detenuti e utilizzare gli utensili.";
            dialogues[0][8] = "Direi che possiamo cominciare! \nBuona fu.. ehm, detenzione...";

            dialogues[1][0] = "Complimenti!\nSei riuscito a scappare!)";
            dialogues[1][1] = "Ma vedi che non è finita qui...";
            dialogues[1][2] = "...commetti altri reati, e torni subito dentro!";
            dialogues[1][3] = "Per questa volta sei stato fortunato...";
            dialogues[1][4] = "Grazie per aver giocato al nostro gioco!";
        }
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
    }
}