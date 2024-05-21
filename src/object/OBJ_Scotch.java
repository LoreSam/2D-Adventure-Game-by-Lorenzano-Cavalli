package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Scotch extends Entity {
    GamePanel gp;
    public static final String objName = "Scotch";

    public OBJ_Scotch(GamePanel gp) {

        super(gp);
        this.gp = gp;

        name = objName;
        down1 = setup("/objects/scotch", gp.tileSize, gp.tileSize);
        price = 250;

    }
}
