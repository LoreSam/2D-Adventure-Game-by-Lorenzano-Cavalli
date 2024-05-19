package tile_interactive;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Rock;

import java.awt.*;

public class IT_Grate extends InteractiveTile{

    GamePanel gp;
    Graphics2D g2;
    int col, row;

    public IT_Grate(GamePanel gp, int col, int row) {

        super(gp, col, row);

        if(col == 9 || col == 79)
            down1 = setup("/tiles_interactive/gratavb", gp.tileSize, gp.tileSize);
        else if(row == 6 || row == 92)
            down1 = setup("/tiles_interactive/grataob", gp.tileSize, gp.tileSize);

        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        this.col = col;
        this.row = row;

        destructible = true;
        life = 20;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        if (entity.currentWeapon.type == type_scissors){
            isCorrectItem = true;
            drawGrateBar();
        }
        return isCorrectItem;
    }

    public void playSE(){
        //gp.playSoundEffect(12);
    }

    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = null;
        return tile;
    }

    public Color getParticleColor(){
        Color color = new Color(200, 120, 80);
        return color;
    }

    public int getParticleSize(){
        int size = 6; //6 PIXEL
        return size;
    }

    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }

    public int getParticleMaxlife(){
        int maxLife = 20;
        return maxLife;
    }

    public void checkDrop(){

        //dropItem(new OBJ_Rock(gp));
    }

    public void drawGrateBar(){

        /*if (life < 20) {
            System.out.println("cxiao");
            double oneScale = (double) gp.tileSize/maxLife;
            double hpBarValue = oneScale*life;
            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(col-1, row-16, gp.tileSize+2, 12);
            g2.setColor(new Color(255, 0 ,30));
            if(hpBarValue > 0)
                g2.fillRect(col, row - 15, (int)hpBarValue, 10);
        }*/
    }
}
