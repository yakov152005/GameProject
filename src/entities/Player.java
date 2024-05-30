package entities;
import Finals.Final;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity{
    private  BufferedImage[][] allAnimation;
    private int animationTick, animationIndex,animationSpeed = 15;;
    private int playerAction = IDLE;
    private boolean left,up,right,down;
    private boolean moving = false, attacking = false, attackingJump = false, attackingJump2 = false,
    hit = false, jump = false;
    private float playerSpeed = 2.0f;


    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    @Override
    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(allAnimation[playerAction][animationIndex],(int)x,(int)y,Final.WIDTH_CAPTAIN*2,Final.HEIGHT_CAPTAIN*2,null);

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

    private void setAnimation() {
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

        if (left && !right){
            x -= playerSpeed;
            moving = true;
        }else if (right && !left){
            x += playerSpeed;
            moving = true;
        }

        if (up && !down){
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }

    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            BufferedImage captainClownNose = ImageIO.read(is);

            this.allAnimation = new BufferedImage[9][6];
            for (int i = 0; i < allAnimation.length; i++) {
                for (int j = 0; j < allAnimation[i].length; j++) {
                    allAnimation[i][j] = captainClownNose.getSubimage
                            (j * Final.WIDTH_CAPTAIN, i * Final.HEIGHT_CAPTAIN, Final.WIDTH_CAPTAIN, Final.HEIGHT_CAPTAIN);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (IllegalArgumentException e){
            System.out.println("The File NOT FOUND!");
        }finally {
            try {
                is.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
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
