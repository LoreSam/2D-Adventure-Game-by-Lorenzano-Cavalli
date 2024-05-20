package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Energy extends Entity {

    GamePanel gp;

    public static final String objName = "Energia";


    public OBJ_Energy(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUp;
        name = objName;
        value = 1;
        down1 = setup("/objects/energy_50", gp.tileSize, gp.tileSize);
        image = setup("/objects/energy_100", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/energy_75", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/energy_50", gp.tileSize, gp.tileSize);
        image4 = setup("/objects/energy_25", gp.tileSize, gp.tileSize);
        image5 = setup("/objects/energy_0", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity){
        String text;
        gp.playSoundEffect(2);
        if (gp.language == 1)
            text = "Energy +";
        else
            text = "Energia +";
        gp.ui.addMessage(text+value);
        entity.energy += value;
    }
}
