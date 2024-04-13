package tile_interactive;

import main.GamePanel;

public class IT_Cesso extends InteractiveTile{

    GamePanel gp;

    public IT_Cesso(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        down1 = setup("/tiles_interactive/cesso", gp.tileSize, gp.tileSize);
    }
}
