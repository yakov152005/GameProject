package entities;
import finals.Final;
import utilz.HelpMethods;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity{
    private  BufferedImage[][] allAnimation;
    private int animationTick, animationIndex,animationSpeed = 15;;
    private int playerAction = IDLE;
    private boolean left,up,right,down;
    private boolean moving = false, attacking = false, attackingJump = false, attackingJump2 = false,
    hit = false, jump = false;
    private float playerSpeed = 2.0f;
    private  int[][] levelData;
    private float xDrawOffSet = 21*Final.SCALE;
    private float yDrawOffSet = 4*Final.SCALE;


    public Player(float x,float y,int width,int height) {
        super(x, y,width,height);
        loadAnimations();
        initHitBox(x,y,20*Final.SCALE,28*Final.SCALE);
    }


    @Override
    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(allAnimation[playerAction][animationIndex],(int)(hitBox.x - xDrawOffSet),(int)(hitBox.y - yDrawOffSet),
                Final.WIDTH_CAPTAIN*2,Final.HEIGHT_CAPTAIN*2,null);
        drawHitBox(g);
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)){
                animationIndex = 0;
                attacking = false;
                attackingJump = false;
            }
        }
    }

    private void setAnimation() {//מתקפות ותזוזות
        int startAnimation = playerAction;



        if (moving){
            playerAction = RUNNING;
        }else {
            playerAction = IDLE;
        }

        if (jump){
            playerAction = JUMP;
        }
        if (hit){
            playerAction = HIT;
        }
        if (attacking){
            playerAction = ATTACK_1;
        }
        if (attackingJump){
            playerAction = ATTACK_JUMP_1;
        }
        if (attackingJump2){
            playerAction = ATTACK_JUMP_2;
        }

        if (startAnimation != playerAction){
            resatAnimationTick();
        }
    }

    private void resatAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updatePosition() {//  בשביל שאם לוחצים על שני מקשים בו זמנית אז לא יזוז השחקן ומאפשר לזוז באלכסון
        moving = false;
        if (!left && !right && !up && !down){
            return;
        }

        float xSpeed = 0, ySpeed = 0;

        if (left && !right){
            xSpeed =- playerSpeed;
        }else if (right && !left){
            xSpeed = playerSpeed;
        }

        if (up && !down){
            ySpeed =- playerSpeed;
        } else if (down && !up) {
            ySpeed = playerSpeed;
        }

//        if (HelpMethods.canMoveHere(x+xSpeed,y+ySpeed,width,height,levelData)){
//            this.x += xSpeed;
//            this.y += ySpeed;
//            moving = true;
//        }
        if (HelpMethods.canMoveHere(hitBox.x+xSpeed,hitBox.y+ySpeed,hitBox.width,hitBox.height,levelData)){
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
            moving = true;
        }

    }


    private void loadAnimations() {
            BufferedImage captainClownNose = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
            this.allAnimation = new BufferedImage[9][6];
            for (int i = 0; i < allAnimation.length; i++) {
                for (int j = 0; j < allAnimation[i].length; j++) {
                    allAnimation[i][j] = captainClownNose.getSubimage
                            (j * Final.WIDTH_CAPTAIN, i * Final.HEIGHT_CAPTAIN, Final.WIDTH_CAPTAIN, Final.HEIGHT_CAPTAIN);
                }
            }
    }

    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
    }

    public boolean isLeft() {
        return left;
    }

    public void resetDirBoolean(){
        left = false;
        right =false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }
    public void setAttackingJump(boolean attackingJump){
        this.attackingJump = attackingJump;
    }
    public void setAttackingJump2(boolean attackingJump2){
        this.attackingJump2 = attackingJump2;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
