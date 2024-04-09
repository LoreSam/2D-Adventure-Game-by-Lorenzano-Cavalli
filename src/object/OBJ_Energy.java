package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Energy extends Entity {

    GamePanel gp;

    public OBJ_Energy(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUp;
        name = "Energy";
        value = 1;
        down1 = setup("/objects/energy_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/energy_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/energy_blank", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity){
        gp.playSoundEffect(2);
        gp.ui.addMessage("Energia +"+value);
        entity.life += value;
    }
}
