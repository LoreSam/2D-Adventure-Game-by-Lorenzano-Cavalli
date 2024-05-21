package entity;

import main.GamePanel;
import object.*;

public class NPC_Merchant extends Entity{

    public NPC_Merchant(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getNPCImage();
        setDialog();
        setItems();
    }

    public void getNPCImage(){
        down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    public void setDialog(){
        if (gp.language == 1)
            dialogues[0][0] = "Ha ha, you found me.\nI've got some good stuff.\nWant some?";
        else
            dialogues[0][0]="Ah ah, mi hai trovato.\nHo della roba buona\nNe vuoi un po'?";
    }

    public void setItems(){

        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sock(gp));
        inventory.add(new OBJ_Pickaxe(gp));
        inventory.add(new OBJ_Stick(gp));
        inventory.add(new OBJ_Scotch(gp));
    }

    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
