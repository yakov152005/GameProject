package main;

import entities.Player;

import java.awt.*;

public class Game implements  Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private  Thread gameLoopThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;

    public Game(){
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        initClasses();
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        try {
            player = new Player(200, 200);
        }catch (NullPointerException e){
            System.out.println(" ");
        }catch (Exception e ){
            System.out.println();
        }
    }

    private void startGameLoop(){
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    public void updates() {
        player.update();
    }

    public void render(Graphics g){
        try {
            player.render(g);
        }catch (NullPointerException e){
            System.out.println();
        }
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;


        long previousTime = System.nanoTime();

        int  frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU  = 0;
        double deltaF = 0;

        while (true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                updates();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + "| UPS: " + updates );
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost(){
        player.resetDirBoolean();
    }

    public Player getPlayer(){
        return player;
    }
}
