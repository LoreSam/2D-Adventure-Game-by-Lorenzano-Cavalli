package tile_interactive;

import main.GamePanel;

public class IT_FoodTable extends InteractiveTile{

    GamePanel gp;

    public IT_FoodTable(GamePanel gp, int col, int row) {

        super(gp, col, row);

        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        type = type_getEnergy;

        name = "Tavolo";

        if(col == 20 || col == 27)
            down1 = setup("/tiles_interactive/tavolo_sx", gp.tileSize, gp.tileSize);
        else if(col == 21 || col == 28)
            down1 = setup("/tiles_interactive/tavolo_cen", gp.tileSize, gp.tileSize);
        else if(col == 22 || col == 29)
            down1 = setup("/tiles_interactive/tavolo_dx", gp.tileSize, gp.tileSize);
    }

    @Override
    public void getEnergy(){

        if(gp.player.energy < 100) {
            gp.player.energy++;
        }

    }

}
