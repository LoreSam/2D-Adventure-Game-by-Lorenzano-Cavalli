package tile_interactive;

import main.GamePanel;

public class IT_Tapirulant extends InteractiveTile{

    GamePanel gp;

    public IT_Tapirulant(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        collision = true;

        down1 = setup("/tiles_interactive/tapirulant", gp.tileSize, gp.tileSize);
    }
}
