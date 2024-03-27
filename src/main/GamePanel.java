package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable{

    //IMPOSTAZIONI SCHERMO
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    //posizione di partenza del personaggio
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

/*     @Override
   public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime()+drawInterval;
        while (gameThread != null){

            //update: aggiorniamo le informazioni del gioco(posizione personaggio)
            update();

            //draw: ridisegnamo la grafica
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0)
                {
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    primo metodo per fare gli fps
    */
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long correntTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            correntTime = System.nanoTime();

            delta += (correntTime - lastTime) / drawInterval;
            timer += (correntTime - lastTime);
            lastTime = correntTime;

            if (delta >= 1 ) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000)
            {
                System.out.println("FPS: "+drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}
