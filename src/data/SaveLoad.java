package data;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName){

        Entity obj = null;

        switch(itemName){

            case "Ascia": obj = new OBJ_Axe(gp);
                break;
            case "Stivali": obj = new OBJ_Boots(gp);
                break;
            case "Cassa": obj = new OBJ_Chest(gp);
                break;
            case "Moneta di bronzo": obj = new OBJ_Coin_Bronze(gp);
                break;
            case "Chiave": obj = new OBJ_Key(gp);
                break;
            case "Lanterna": obj = new OBJ_Lantern(gp);
                break;
            case "Pozione Rossa": obj = new OBJ_Potion_Red(gp);
                break;
            case "Scudo blu": obj = new OBJ_Shield_Blue(gp);
                break;
            case "Scudo di legno": obj = new OBJ_Shield_Wood(gp);
                break;
            case "Spada normale": obj = new OBJ_Sword_Normal(gp);
                break;
        }

        return obj;
    }

    public void save() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.defaultSpeed = gp.player.defaultSpeed;
            ds.life = gp.player.life;
            ds.maxEnergy = gp.player.maxEnergy;
            ds.energy = gp.player.energy;
            ds.ammo = gp.player.ammo;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.attack = gp.player.attack;
            ds.defense = gp.player.defense;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            for(int i = 0; i < gp.player.inventory.size(); i++){
                ds.itemNames.add(gp.player.inventory.get(i).name);
            }

            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSLot = gp.player.getCurrentShieldSlot();

            ds.mapObjectNames = new String[gp.mapState][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.mapState][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.mapState][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.mapState][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.mapState][gp.obj[1].length];

            for(int i = 0; i < gp.maxMap; i++){

                for(int j = 0; j < gp.obj[1].length; j++){

                    if(gp.obj[i][j] == null){
                        ds.mapObjectNames[i][j] = "NA";
                    }
                    else{
                        ds.mapObjectNames[i][j] = gp.obj[i][j].name;
                        ds.mapObjectWorldX[i][j] = gp.obj[i][j].worldX;
                        ds.mapObjectWorldY[i][j] = gp.obj[i][j].worldY;
                        if(gp.obj[i][j].loot != null){
                            ds.mapObjectLootNames[i][j] = gp.obj[i][j].loot.name;
                        }
                        ds.mapObjectOpened[i][j] = gp.obj[i][j].opened;
                    }
                }
            }

            oos.writeObject(ds);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.defaultSpeed = ds.defaultSpeed;
            gp.player.life = ds.life;
            gp.player.maxEnergy = ds.maxEnergy;
            gp.player.energy = ds.energy;
            gp.player.ammo = ds.ammo;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.attack = ds.attack;
            gp.player.defense = ds.defense;
            gp.player.exp = ds.exp;
            gp.player.coin = ds.coin;
            gp.player.nextLevelExp = ds.nextLevelExp;

            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++){
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
            }

            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSLot);
            gp.player.getDefense();
            gp.player.getAttackImage();

            for(int i = 0; i < gp.maxMap; i++){

                for(int j = 0; j < gp.obj[1].length; j++){

                    if(ds.mapObjectNames[i][j].equals("NA")){
                        gp.obj[i][j] = null;
                    }
                    else{
                        gp.obj[i][j] = getObject(ds.mapObjectNames[i][j]);
                        gp.obj[i][j].worldX = ds.mapObjectWorldX[i][j];
                        gp.obj[i][j].worldY = ds.mapObjectWorldY[i][j];
                        if(ds.mapObjectLootNames[i][j] != null){
                            gp.obj[i][j].loot = getObject(ds.mapObjectLootNames[i][j]);
                        }
                        gp.obj[i][j].opened = ds.mapObjectOpened[i][j];
                        if(gp.obj[i][j].opened){
                            //TODO per interazione con chest
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
