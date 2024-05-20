package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {

    GamePanel gp;

    public static final String objName = "Moneta";

    public OBJ_Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUp;
        name = objName;
        value = 1;
        down1 = setup("/objects/coin", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity){
        String text;
        gp.playSoundEffect(2);
        if(gp.language == 1)
            text = "Coin";
        else
            text = "Moneta";
        gp.ui.addMessage(text+value);
        gp.player.coin += value;
    }
}
