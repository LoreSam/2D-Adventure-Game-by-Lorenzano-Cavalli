package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public static final String objName = "Ascia";

    public OBJ_Axe(GamePanel gp) {
        super(gp);
        type = type_axe;
        name = objName;
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Ascia taglialegna] \n un po arrugginita \ncome i freni di Samu";
    }
}
