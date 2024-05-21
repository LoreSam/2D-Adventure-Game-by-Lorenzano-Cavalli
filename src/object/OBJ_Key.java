package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import javax.management.openmbean.InvalidOpenTypeException;
import java.io.IOException;

public class OBJ_Key extends Entity {

    public static final String objName = "Chiave";

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/chiave_g", gp.tileSize, gp.tileSize);
        if (gp.language == 1)
            description = "[" + name + "]\nOpen every door.";
        else
            description = "[" + name + "]\nApre una porta.";
        price = 50;
    }
}
