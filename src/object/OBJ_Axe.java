package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public static final String objName = "Ascia";

    GamePanel gp;

    public OBJ_Axe(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_axe;
        name = objName;
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        if (gp.language ==1 )
            description = "[Woodcutter's Axe] \nA little bit rusty.";
        else
            description = "[Ascia taglialegna] \nUn po' arrugginita.";
    }
}
