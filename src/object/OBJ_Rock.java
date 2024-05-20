package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Rock extends Projectile {
    GamePanel gp;

    public static final String objName = "Roccia";

    public OBJ_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_rock;
        name = objName;
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        //rockIn = false;
        down1 = setup("/objects/rock", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user){
        boolean haveResoure = false;
        if(user.ammo >= useCost)
            haveResoure = true;
        return haveResoure;
    }

    public void subtractResource(Entity user){
        user.ammo -= useCost;
    }
}
