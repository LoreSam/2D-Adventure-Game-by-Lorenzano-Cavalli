package tile_interactive;

import main.GamePanel;

public class IT_Bed extends InteractiveTile{

    GamePanel gp;

    public IT_Bed(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        down1 = setup("/tiles_interactive/bed", gp.tileSize, gp.tileSize * 2);
    }
}
