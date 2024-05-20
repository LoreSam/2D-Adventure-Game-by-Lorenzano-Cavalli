package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Scissors extends Entity {

    public static final String objName = "Forbici";
    String text;

    public OBJ_Scissors(GamePanel gp) {
        super(gp);
        type = type_scissors;
        name = objName;
        down1 = setup("/objects/scissors", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        if (gp.language == 1) {
            text = "[Scissors] \nCan break fences rust";
        }
        else {
            text = "[Forbici] \nPuò rompere le recinzioni arrugginite";
        }
        description = text;
        price = 75;
    }
}
