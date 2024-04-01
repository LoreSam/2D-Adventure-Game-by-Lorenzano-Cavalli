package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

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

    int FPS = 144;

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
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    //STATO
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

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
            player.update();
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null)
                    npc[i].update();
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

            //OGGETTI
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null)
                    obj[i].draw(g2, this);
            }

            //NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null)
                    npc[i].draw(g2);
            }

            //GIOCATORE
            player.draw(g2);

            //UI
            ui.draw(g2);

            g2.dispose();
        }
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        se.setFile(i);
        se.play();
    }
}
