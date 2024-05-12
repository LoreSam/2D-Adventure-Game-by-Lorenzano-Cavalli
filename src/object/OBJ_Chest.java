package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {

    public static final String objName = "Cassa";


    public OBJ_Chest(GamePanel gp) {

        super(gp);

        name = objName;
        down1 = setup("/objects/chest", gp.tileSize, gp.tileSize);
    }

    public void setLoot(Entity loot){
        this.loot = loot;
    }
}
