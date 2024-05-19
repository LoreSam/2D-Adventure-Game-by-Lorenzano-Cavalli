package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Scissors extends Entity {

    public static final String objName = "Forbici";

    public OBJ_Scissors(GamePanel gp) {
        super(gp);
        type = type_scissors;
        name = objName;
        down1 = setup("/objects/scissors", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Piccone] \n Piccone del porcodio";
        price = 75;
    }
}
