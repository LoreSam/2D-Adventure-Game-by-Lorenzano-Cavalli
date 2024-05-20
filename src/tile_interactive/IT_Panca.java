package tile_interactive;

import main.GamePanel;

import java.util.Random;

public class IT_Panca extends InteractiveTile{

    GamePanel gp;

    public IT_Panca(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        type = type_training;

        collision = true;

        down1 = setup("/tiles_interactive/panca", gp.tileSize, gp.tileSize);
    }

    @Override
    public void training(){

        Random random = new Random();
        int i = random.nextInt(4)+1;

        if(gp.player.energy > 0){

            switch(i) {
                case 1:
                    gp.player.energy -= 2;
                    if(gp.player.energy < 0)
                        gp.player.energy = 0;
                    gp.player.strength++;
                    break;
                case 2:
                    gp.player.energy -= 3;
                    if(gp.player.energy < 0)
                        gp.player.energy = 0;
                    gp.player.strength++;
                    break;
                case 3:
                    gp.player.energy -= 4;
                    if(gp.player.energy < 0)
                        gp.player.energy = 0;
                    gp.player.strength += 2;
                    break;
                case 4:
                    gp.player.energy -= 5;
                    if(gp.player.energy < 0)
                        gp.player.energy = 0;
                    gp.player.strength += 3;
                    break;
            }
        }

    }
}
