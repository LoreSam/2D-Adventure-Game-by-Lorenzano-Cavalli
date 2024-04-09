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
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialog = "Hai bevuto " + name + "!\nHai recuperato " + value + ".";
        entity.life += value;
        if (gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSoundEffect(3);
    }
}
