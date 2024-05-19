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
        down1 = setup("/tiles/original/void", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Ascia taglialegna] \n un po arrugginita \ncome i freni di Samu";
    }

    public void visible(){

        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        //TODO muro "circolare", renderizzabile, che contiene gli oggetti invisibili (sostituiti dall'immagine)
    }
}
