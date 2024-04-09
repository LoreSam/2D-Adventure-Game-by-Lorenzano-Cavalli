package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //IMPOSTAZIONI SCHERMO
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //IMPOSTAZIONI MONDO
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

    //SISTEMA
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    //ENTITÀ ED OGGETTI
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[20];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //STATO DI GIOCO
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1 ) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000)
            {
                //System.out.println("FPS: " + drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }

    public void update(){
        if(gameState == playState) {
            //GIOCATORE
            player.update();

            //NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null)
                    npc[i].update();
            }

            //MOSTRI
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null) {
                    if (monster[i].alive && !monster[i].dying)
                        monster[i].update();
                    if (!monster[i].alive ){
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }

            //PROIETTILI
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null) {
                    if (projectileList.get(i).alive)
                        projectileList.get(i).update();
                    if (!projectileList.get(i).alive){
                        projectileList.remove(i);
                    }
                }
            }

        }
        if(gameState == pauseState){
            //TODO in futuro
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }

        //COSA DISEGNAMO DOPO IL TITLE SCREEN
        else{
            //TILE
            tileM.draw(g2);

            //AGGIUNTA ENTITA ALLA LISTA
            entityList.add(player);
            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    entityList.add(npc[i]);
                }
            }

            //AGGIUNTA OGGETTI ALLA LISTA
            for (int i = 0; i < obj.length; i++){
                if (obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            //AGGIUNTA CANI ALLA LISTA
            for (int i = 0; i < monster.length; i++){
                if (monster[i] != null){
                    entityList.add(monster[i]);
                }
            }

            //AGGIUNTA PROIETTILI ALLA LISTA
            for (int i = 0; i < projectileList.size(); i++){
                if (projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }

            //ORDINAMENTO
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //DISEGNO ENTITA
            for(int i = 0; i<entityList.size(); i++){
                entityList.get(i).draw(g2);
            }

            //SVUOTO LISTA ENTITA
            entityList.clear();

            //UI
            ui.draw(g2);
        }
    }

    public void playMusic(int i){
        music.setFile(i);
        /*music.play();
        music.loop();*/
    }

    public void stopMusic(){
        //music.stop();
    }

    public void playSoundEffect(int i){
        se.setFile(i);
        se.play();
    }
}
