package main;

import entity.Entity;
import object.*;

public class EntityGenerator {
    GamePanel gp;

    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }
    public Entity getObject(String itemName){

        Entity obj = null;

        switch(itemName){

            case OBJ_Axe.objName: obj = new OBJ_Axe(gp);
                break;
            case OBJ_Chest.objName: obj = new OBJ_Chest(gp);
                break;
            case OBJ_Coin.objName: obj = new OBJ_Coin(gp);
                break;
            case OBJ_Scotch.objName: obj = new OBJ_Scotch(gp);
                break;
            case OBJ_Heart.objName: obj = new OBJ_Heart(gp);
                break;
            case OBJ_Key.objName: obj = new OBJ_Key(gp);
                break;
            case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp);
                break;
            case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(gp);
                break;
            case OBJ_Scissors.objName: obj = new OBJ_Scissors(gp);
                break;
            case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(gp);
                break;
            case OBJ_Rock.objName: obj = new OBJ_Rock(gp);
                break;
            case OBJ_Sock.objName: obj = new OBJ_Sock(gp);
                break;
            case OBJ_Stick.objName: obj = new OBJ_Stick(gp);
                break;
        }

        return obj;
    }
}
