package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity {

    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Scudo blu";
        down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\n Fatto di s****a blu.";
    }
}
