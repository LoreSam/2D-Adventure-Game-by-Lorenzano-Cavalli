package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
    GamePanel gp;

    public static final String objName = "Cuore";

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickUp;
        name = objName;
        value = 2;
        down1 = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity){
        gp.playSoundEffect(2);
        gp.ui.addMessage("Vita +"+value);
        entity.life += value;
    }
}
