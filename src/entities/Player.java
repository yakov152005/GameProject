package entities;
import finals.Final;
import utilz.HelpMethods;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static finals.Final.SCALE;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

public class Player extends Entity{
    private  BufferedImage[][] allAnimation;
    private int animationTick, animationIndex,animationSpeed = 15;;
    private int playerAction = IDLE;
    private boolean left,up,right,down,moving , attacking , attackingJump , attackingJump2, hit ,jump ;
    private float playerSpeed = 2.0f;
    private  int[][] levelData;
    private float xDrawOffSet = 21* SCALE;
    private float yDrawOffSet = 4* SCALE;

    //Jumping // Gravity
    private float airSpeed = 0f;
    private float gravity = 0.04F * SCALE;
    private float jumpSpeed = - 2.25f * SCALE; // y is negative cuse they need 'y' down and this player is up
    private float fallSpeedAfterCollision = 0.05f *  SCALE; //במקרה שהשחקן פוגע בגג אז מגדירים ערך חדש למהירות
    private boolean inAir = false;

    public Player(float x,float y,int width,int height) {
        super(x, y,width,height);
        loadAnimations();
        initHitBox(x,y,20* SCALE,27* SCALE);
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
//        drawHitBox(g);
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

        if (inAir){
            if (airSpeed < 0 ){
                playerAction = JUMP;
            }else {
                playerAction = FALLING;
            }
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


        if (jump){
            jump();
        }
        if (!left && !right && !inAir){
            return;
        }
        float xSpeed = 0;

        if (left){
            xSpeed -= playerSpeed;
        }
        if (right){
            xSpeed += playerSpeed;
        }
        if (!inAir){
            if (!isEntityOnFloor(hitBox,levelData)){
                inAir = true;
            }
        }


        if (inAir){
            if (canMoveHere(hitBox.x,hitBox.y + airSpeed,hitBox.width,hitBox.height,levelData)) {
                hitBox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            }else {
                hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox,airSpeed);
                if (airSpeed > 0){
                    resetInAir();
                }else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        }else {
            updateXPos(xSpeed);
        }
        moving = true;
    }


    private void jump() {
        if (inAir){
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (HelpMethods.canMoveHere(hitBox.x+xSpeed,hitBox.y,hitBox.width,hitBox.height,levelData)){
            hitBox.x += xSpeed;
        }else {
            hitBox.x = getEntityXPosNextToWall(hitBox,xSpeed);
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
        if (!isEntityOnFloor(hitBox,levelData)){
            inAir = true;
        }
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
