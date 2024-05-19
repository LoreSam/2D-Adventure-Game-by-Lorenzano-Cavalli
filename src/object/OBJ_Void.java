package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Void extends Entity {

    public static final String objName = "Vuoto";

    GamePanel gp;

    public OBJ_Void(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_axe;
        name = objName;
        down1 = setup("/tiles/original/void", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
    }
}
