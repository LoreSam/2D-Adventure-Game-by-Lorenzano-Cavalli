package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_Door extends InteractiveTile {

    GamePanel gp;

    public IT_Door(GamePanel gp, int col, int row) {

        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        type = type_obstacle;
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    /*@Override
    public void interact(){

        if(collision){
            gp.obj[gp.currentMap][0] = null;
            collision = false;
        }
        else{
            /*gp.obj[gp.currentMap][0] = new IT_Door(gp, 18, 18);
            gp.aSetter.setObject();
            collision = true;
        }
    }*/

    @Override
    public InteractiveTile doorOpened(){
        InteractiveTile tile = new IT_DoorO(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    @Override
    public InteractiveTile doorClosed() {
        InteractiveTile tile = new IT_Door(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
