package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void playSE(){}

    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = null;
        return tile;
    }

    public InteractiveTile doorOpened(){
        InteractiveTile tile = null;
        return tile;
    }

    public InteractiveTile doorClosed(){
        InteractiveTile tile = null;
        return tile;
    }

    public boolean sleep(){
        return false;
    }

    public void eat(){

    }

    public void update() {
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 20){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        //nell'if il *2 serve per renderizzare senza far sparire gli oggetti grandi 2 tile

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize*2 > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize*2 < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize*2 > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize*2 < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(down1, screenX, screenY, null); //TECNICAMENTE NEL DRAW ANDREBBERO GP.TILESIZE, PERO FUNZIONA ANCHE COSI
        }
    }
}
