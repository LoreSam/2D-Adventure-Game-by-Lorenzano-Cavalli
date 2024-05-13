package entity;

import main.GamePanel;
import object.OBJ_Door_Iron;
import tile_interactive.IT_Metal_Plate;
import tile_interactive.InteractiveTile;

import java.util.ArrayList;
import java.util.Random;

public class NPC_TheRock extends Entity{
    public static final String npcName = "The Rock";

    public NPC_TheRock(GamePanel gp) {
        super(gp);
        name = npcName;
        direction = "down";
        speed = 4;

        dialogueSet = -1;

        getNPCImage();
        setDialog();
    }

    public void getNPCImage(){
        up1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
    }

    public void setDialog(){

        dialogues[0][0]="è una palla gigante!";
    }

    public void setAction(){}
    public void update(){}
    public void speak(){

        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        //TODO in base alla condizione, settare il set di dialoghi che si vuole
        if(dialogues[dialogueSet][0] == null){

            dialogueSet = 0;
        }
    }
    public void move (String d){
        this.direction = d;

        checkCollision();
        if (!collisionOn){
            switch (direction){
                case "up": worldY -= speed;
                    break;
                case "down": worldY += speed;
                    break;
                case "left": worldX -= speed;
                    break;
                case "right": worldX += speed;
                    break;
            }
        }
        detectPlate();
    }
    public void detectPlate(){
        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();

        //creazioen lista pedane
        for (int i = 0; i < gp.iTile[1].length; i++){
            if ((gp.iTile[gp.currentMap][1] != null) &&gp.iTile[gp.currentMap][1].name != null && gp.iTile[gp.currentMap][1].name.equals(IT_Metal_Plate.itName)) {
                plateList.add(gp.iTile[gp.currentMap][i]);
            }
        }

        //creazioen lista roccie
        for (int i = 0; i < gp.npc[1].length; i++){
            if (gp.npc[gp.currentMap][1] != null && gp.iTile[gp.currentMap][1].name.equals(NPC_TheRock.npcName)){
                rockList.add(gp.npc[gp.currentMap][i]);
            }
        }
        int counter = 0;

        //scan delle pedane
        for (int i = 0; i < plateList.size(); i++){
            int xDistance = Math.abs(worldX - plateList.get(i).worldX);
            int yDistance = Math.abs(worldY - plateList.get(i).worldY);
            int distance = Math.max(xDistance, yDistance);

            if (distance < 8){
                if (linkedEntity == null) {
                    linkedEntity = plateList.get(i);
                    gp.playSoundEffect(13);
                }
            }
            else {
                if (linkedEntity == plateList.get(i)) {
                    linkedEntity = null;
                }
            }
        }

        for (int i = 0; i < rockList.size(); i++){
            if(rockList.get(i).linkedEntity !=  null){
                counter++;
            }
        }
        if (counter == rockList.size()){
            for (int i = 0; i < gp.obj[1].length; i++){
                if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)){
                    gp.obj[gp.currentMap][i] = null;
                    gp.playSoundEffect(14);
                }
            }
        }
    }
}
