package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {
    GamePanel gp;

    public static final String objName = "Moneta di bronzo";


    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUp;
        name = objName;
        value = 1;
        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity){
        gp.playSoundEffect(2);
        gp.ui.addMessage("Moneta +"+value);
        gp.player.coin += value;
    }
}
