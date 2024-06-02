package main;
import entities.Player;
import levels.LevelManager;

import java.awt.*;

import static finals.Final.*;

public class Game implements  Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private  Thread gameLoopThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelManager;

    public Game(){
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        initClasses();
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        try {
            levelManager = new LevelManager(this);
            player = new Player(200, 200,(int)(WIDTH_CAPTAIN * SCALE), (int)( HEIGHT_CAPTAIN * SCALE));
            player.loadLevelData(levelManager.getLevelOne().getLevelData());
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
        levelManager.update();
    }

    public void render(Graphics g){
        try {
            levelManager.draw(g);
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
