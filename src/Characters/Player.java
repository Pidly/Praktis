package Characters;

import Game.Combat;
import org.lwjgl.opengl.GL11;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 10/11/14
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Character implements Combat {
    Stats playerDisplay;

    public Player(int x, int y, int width, int height){
        super(x, y, width, height);

        str = 20;
        spirit = 10;

        this.currentHp = 40;
        /*
        this.x = Main.screenx - Main.screenx/8;
        this.y = Main.screeny/2;
        this.width = Main.screenx/16;
        this.height = Main.screeny/8;
         */
        hp = 50;

        playerDisplay = new Stats(50, 25, width, height, this.x, this.y, this.currentHp);
    }

    @Override
    public void draw() {
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glColor4f(0.2f, 0.5f, 1.0f, 1.0f);

        GL11.glBegin(GL11.GL_QUADS);


        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x+width, y);
        GL11.glVertex2f(x+width, y+height);
        GL11.glVertex2f(x, y+height);

        GL11.glEnd();

        playerDisplay.draw();
    }

    public void attack(Combat target){
        if(playerDisplay.ready){
            target.takeDamage(dealDamage());
            playerDisplay.resetProgressBar();
        }
    }

    @Override
    public void update() {
        if(this.currentHp < 0)
            this.currentHp = 0;

        playerDisplay.upDate(0.01f, this.currentHp);

    }

    @Override
    public void takeDamage(int damage) {
        currentHp -= damage;

        if(currentHp < 0){
            currentHp = 0;
        }
    }

    @Override
    public int dealDamage() {
        return str;
    }
}
