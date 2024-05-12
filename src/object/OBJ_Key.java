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
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n Apre una porta.";
    }
}
