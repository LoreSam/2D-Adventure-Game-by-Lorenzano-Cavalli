package main;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile.Map;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable{

    //IMPOSTAZIONI SCHERMO
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //960 PIXEL
    public final int screenHeight = tileSize * maxScreenRow; //576 PIXEL

    //FULLSCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //IMPOSTAZIONI MONDO
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int maxMap = 2; //ci servono normale e sottoterra
    public int currentMap = 0;

    int FPS = 60;

    //SISTEMA
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Map map = new Map(this);
    SaveLoad saveLoad = new SaveLoad(this);
    Thread gameThread;
    public PathFinder pFinder = new PathFinder(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);

    //ENTITÀ ED OGGETTI
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][20];
    public InteractiveTile[][] iTile = new InteractiveTile[maxMap][100];
    public Entity projectile[][] = new Entity[maxMap][20];
    //public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //STATO DI GIOCO
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        eManager.setup();

        playMusic(0);
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if(fullScreenOn)
            setFullscreen();
    }

    public void resetGame(boolean restart){

        player.setDefaultPosition();
        player.restoreStatistics();
        player.resetCounter();
        aSetter.setNPC();
        aSetter.setMonster();

        if(restart) {
            player.setDefaultValues();
            aSetter.setObject();
            aSetter.setInteractiveTile();
        }
    }

    public void setFullscreen(){

        //OTTENGO RISOLUZIONE SCHERMO LOCALE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //OTTENGO LARGHEZZA E ALTEZZA DELLO SCHERMO
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
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
                drawToTempScreen(); //DISEGNA SUL PANNELLO TEMPORANEO
                drawToScreen(); //DISEGNO EFFETTIVO
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
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null)
                    npc[currentMap][i].update();
            }

            //MOSTRI
            for(int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive && !monster[currentMap][i].dying)
                        monster[currentMap][i].update();
                    if (!monster[currentMap][i].alive ){
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }

            //PROIETTILI
            for(int i = 0; i < projectile[1].length; i++){
                if(projectile[currentMap][i] != null) {
                    if (projectile[currentMap][i].alive)
                        projectile[currentMap][i].update();
                    if (!projectile[currentMap][i].alive){
                        projectile[currentMap][i] = null;
                    }
                }
            }

            //PARTICELLE
            for(int i = 0; i < particleList.size(); i++){
                if(particleList.get(i) != null) {
                    if (particleList.get(i).alive)
                        particleList.get(i).update();
                    if (!particleList.get(i).alive){
                        particleList.remove(i);
                    }
                }
            }

            //TILE INTERATTIVI
            for (int i = 0; i < iTile[1].length; i++){
                if (iTile[currentMap][i] != null){
                    iTile[currentMap][i].update();
                }
            }

            eManager.update();
        }
        if(gameState == pauseState){
            //TODO in futuro
        }
    }

    public void drawToTempScreen(){
        //TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }

        //MAPPA
        else if(gameState == mapState){
            map.drawFullMapScreen(g2);
        }

        //COSA DISEGNAMO DOPO IL TITLE SCREEN
        else {
            //TILE
            tileM.draw(g2);

            //TILE INTERATTIVI
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            //AGGIUNTA ENTITA ALLA LISTA
            entityList.add(player);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            //AGGIUNTA OGGETTI ALLA LISTA
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            //AGGIUNTA CANI ALLA LISTA
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            //AGGIUNTA PROIETTILI ALLA LISTA
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }

            //AGGIUNTA PARTICELLE ALLA LISTA
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
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
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            //SVUOTO LISTA ENTITA
            entityList.clear();

            //ENVIRONMENT
            eManager.draw(g2);

            //MINIMAPPA
            map.drawMiniMap(g2);

            //UI
            ui.draw(g2);
        }
    }

    public void drawToScreen(){

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i){
        music.setMusic(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        se.setSound(i);
        se.play();
    }
}
