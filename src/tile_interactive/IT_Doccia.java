package tile_interactive;

import main.GamePanel;

public class IT_Doccia extends InteractiveTile{

    GamePanel gp;

    public IT_Doccia(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        if(row == 6)
            down1 = setup("/tiles_interactive/doccia2", gp.tileSize, gp.tileSize);
        else if(row == 8)
            down1 = setup("/tiles_interactive/doccia1", gp.tileSize, gp.tileSize);
    }
}
