package Characters;

import Game.Combat;
import org.lwjgl.opengl.GL11;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 10/18/14
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Enemy extends Character implements Combat {
    Stats mobStats;

    public Enemy(int x, int y, int width, int height){
        super(x,y,width,height);

        str = 10;
        spirit = 5;

        this.currentHp = 40;

        mobStats = new Stats(currentHp, 0, width, height, x, y, this.currentHp);
    }

    @Override
    public void draw() {
        if(currentHp > 0){
            GL11.glColor4f(1.0f, 0.4f, 0.3f, 1.0f);

            GL11.glBegin(GL11.GL_QUADS);

            GL11.glVertex2f(x, y);
            GL11.glVertex2f(x+width, y);
            GL11.glVertex2f(x+width, y+height);
            GL11.glVertex2f(x, y+height);

            GL11.glEnd();

            mobStats.draw();
        }
    }

    @Override
    public void update() {
        if(this.currentHp < 0)
            this.currentHp = 0;

        mobStats.upDate(0.0035f, this.currentHp);

        if(mobStats.ready){
            mobStats.currentProgress = 0;
            mobStats.ready = false;
            attack();
        }
    }

    public void attack(){

    }

    @Override
    public void takeDamage(int damage) {
        this.currentHp -= damage;

        if(this.currentHp < 0)
            this.currentHp = 0;
    }

    @Override
    public int dealDamage() {
        return str;
    }
}
