package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sock extends Entity {

    public static final String objName = "Spada normale";

    public OBJ_Sock(GamePanel gp) {
        super(gp);
        type = type_sword;
        name = objName;
        down1 = setup("/objects/sock", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nUn vecchio calzino.";
        price = 104;
    }
}
