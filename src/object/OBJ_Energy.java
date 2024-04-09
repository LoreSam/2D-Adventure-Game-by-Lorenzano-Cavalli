package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Energy extends Entity {

    GamePanel gp;

    public OBJ_Energy(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Energy";
        image = setup("/objects/energy_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/energy_blank", gp.tileSize, gp.tileSize);
    }
}
