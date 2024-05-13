package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity {

    public static final String objName = "Piccone";

    public OBJ_Pickaxe(GamePanel gp) {
        super(gp);
        type = type_pickaxe;
        name = objName;
        down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Piccone] \n Piccone del porcodio";
        price = 75;
    }
}
