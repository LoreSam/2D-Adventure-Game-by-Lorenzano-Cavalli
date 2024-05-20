package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_Bed extends InteractiveTile{

    GamePanel gp;

    public IT_Bed(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.type = type_bed;
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        down1 = setup("/tiles_interactive/bed", gp.tileSize, gp.tileSize * 2);
    }

    public boolean sleep(){

        gp.gameState = gp.sleepState;
        gp.player.life = gp.player.maxLife;
        gp.player.energy = gp.player.maxEnergy;
        gp.player.coin += 25;
        gp.ui.hours = 8;
        return false;
    }
}
