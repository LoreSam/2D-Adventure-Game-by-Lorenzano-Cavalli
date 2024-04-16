package tile_interactive;

import main.GamePanel;

public class IT_Panca extends InteractiveTile{

    GamePanel gp;

    public IT_Panca(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        down1 = setup("/tiles_interactive/panca", gp.tileSize, gp.tileSize);
    }
}
