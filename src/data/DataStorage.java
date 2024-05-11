package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    //STATISTICHE GIOCATORE
    int maxLife;
    int defaultSpeed;
    int life;
    int maxEnergy;
    int energy;
    int ammo;
    int level;
    int strength;
    int dexterity;
    int attack;
    int defense;
    int exp;
    int nextLevelExp;
    int coin;

    //INVENTARIO
    ArrayList<String> itemNames = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSLot;

    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];
}
