package main;
import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;

import java.awt.*;

public class Game implements  Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private  Thread gameLoopThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Playing playing;
    private Menu menu;

    public Game(){
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        initClasses();
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        this.menu = new Menu(this);
        this.playing = new Playing(this);
    }
    private void startGameLoop(){
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }
    public void updates() {
        switch (GameState.state){
            case MENU:
                menu.update();
                break;

            case PLAYING:
                playing.update();
                break;

            default:
                break;
        }
    }
    public void render(Graphics g){
        switch (GameState.state){
            case MENU:
                menu.draw(g);
                break;

            case PLAYING:
                playing.draw(g);
                break;
                default:
                break;
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
        if (GameState.state == GameState.PLAYING){
            playing.getPlayer().resetDirBoolean();
        }
    }
    public Menu getMenu(){
        return menu;
    }
    public Playing getPlaying(){
        return playing;
    }
}
