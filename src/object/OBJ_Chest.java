package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {

    public static final String objName = "Cassa";

    GamePanel gp;
    boolean opened;

    public OBJ_Chest(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;

        name = objName;
        down1 = setup("/objects/chest", gp.tileSize, gp.tileSize);
    }

    /*public void interact(){

        gp.gameState = gp.dialogueState;

        if(!opened){

            gp.playSoundEffect(3);

            StringBuilder sb = new StringBuilder();
            sb.append("Apri la cassa e trovi " + loot + "!");

            if(gp.player.inventory.size() == gp.player.maxInventorySize)
                sb.append("\nIl tuo inventario è pieno!");
            else{
                sb.append("Ottieni " + loot.name + "!");
                gp.player.inventory.add(loot);
                opened = true;
            }
            gp.ui.currentDialog = sb.toString();
        }
        else{
            gp.ui.currentDialog = "La cassa è vuota";
        }
    }*/

    public void setLoot(Entity loot){
        this.loot = loot;
    }
}
