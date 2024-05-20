package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {

    public static final String objName = "Lanterna";

    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        type = type_light;
        name = objName;
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
        if(gp.language == 1)
            description ="[Lantern]\nIt lights up the word surraunding you";
        else
            description = "[Lanterna]\nIllumina il mondo attorno a te";
        price = 200;
        lightRadius = 450;
    }
}
