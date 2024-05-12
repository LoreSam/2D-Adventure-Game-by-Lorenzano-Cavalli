package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Pozione Rossa";
        value = 5;
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[Pozione]\nTi cura di " + value + ".";
        price = 50;
    }

    public void setDialogue(){

        dialogues[0][0] = "Hai bevuto " + name + "!\nHai recuperato " + value + ".";
    }

    public void use(Entity entity){

        startDialogue(this, 0);
        gp.gameState = gp.dialogueState;
        entity.life += value;

        if (gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }

        gp.playSoundEffect(3);
    }
}
