package tile_interactive;

import main.GamePanel;

import java.util.Random;

public class IT_Tapirulan extends InteractiveTile{

    GamePanel gp;

    public IT_Tapirulan(GamePanel gp, int col, int row) {
        super(gp, col, row);

        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        type = type_training;

        collision = true;

        down1 = setup("/tiles_interactive/tapirulant", gp.tileSize, gp.tileSize);
    }

    int cont = 0;

    @Override
    public void training(){

        Random random = new Random();
        int i = random.nextInt(3)+1;

        if(gp.player.energy > 0 && gp.player.speed < 5){

            switch(i) {
                case 1:
                    gp.player.energy--;
                    gp.player.speed_cont++;
                    cont++;
                    break;
                case 2:
                    gp.player.energy -= 2;
                    if(gp.player.energy < 0)
                        gp.player.energy = 0;
                    gp.player.speed_cont++;
                    cont++;
                    break;
                case 3:
                    gp.player.energy -= 3;
                    if(gp.player.energy < 0)
                        gp.player.energy = 0;
                    gp.player.speed_cont++;
                    cont++;
                    break;
            }
        }

        if(cont >= 25){
            gp.player.speed++;
            cont = 0;
        }
    }
}
