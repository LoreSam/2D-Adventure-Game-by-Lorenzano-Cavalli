package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventReactDefaultX, EventReactDefaultY;
    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventReactDefaultX = eventRect.x;
        EventReactDefaultY = eventRect.y;
    }
    public void checkEvent (){
//        if (hit(27, 16, "right") == true){
  //          damagePit(gp.dialogueState);
    //    }
        if (hit(23, 12, "up") == true){
            healtingPool(gp.dialogueState);
        }
        if (hit(27, 16, "right") == true){
            teleport(gp.dialogueState);
        }
    }
    public boolean hit(int eventcCol, int eventRow, String reqDirection){
        boolean hit=false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventcCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow* gp.tileSize + eventRect.y;
        if(gp.player.solidArea.intersects(eventRect)){
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventReactDefaultX;
        eventRect.y = EventReactDefaultY;
        return hit;
    }
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialog = "sei cadito in un a trappola coglione";
        gp.player.life -=1;
    }
    public void healtingPool(int gameState){
        if (gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.ui.currentDialog = "hai bevuto ubriacone \n hai regennato tutta la vita";
            gp.player.life = gp.player.maxLife;
        }
    }
    public void teleport(int gameState){
        gp.gameState= gameState;
        gp.ui.currentDialog = "sei stato trasportato";
        gp.player.worldX = gp.tileSize*37;
        gp.player.worldY = gp.tileSize*10;
    }
}
