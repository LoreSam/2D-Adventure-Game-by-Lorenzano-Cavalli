package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Stick extends Entity {

    GamePanel gp;

    public static final String objName = "Stick";

    public OBJ_Stick(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_stick;
        name = objName;
        down1 = setup("/objects/stick", gp.tileSize, gp.tileSize);
        if (gp.language == 1)
            description = "[" + name + "]\nRod";
        else
            description = "[" + name + "]\n Bastone";
        //stickIn = false;
    }
}