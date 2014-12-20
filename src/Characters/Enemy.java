package Characters;

import Game.Combat;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 10/18/14
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Enemy extends Character implements Combat {

    AttackAbility attackAbility;
    List<Ability> abilities = new ArrayList<Ability>();

    public Enemy(int x, int y, int width, int height){
        super(x,y,width,height);

        attackAbility = new AttackAbility();

        abilities.add(attackAbility);

        str = 10;
        spirit = 5;

        this.currentHp = 40;
        hp = 40;

        stats = new Stats(hp, 0, width, height, x, y, this.currentHp);
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

            stats.draw();
        }
    }

    @Override
    public void update() {
        if(this.currentHp < 0)
            this.currentHp = 0;

        stats.upDate(0.0035f, this.currentHp);

        if(stats.ready){
            stats.currentProgress = 0;
            stats.ready = false;
            attack();
        }
    }

    @Override
    public void resetTimer() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Ability> getAbilities() {
        return abilities;
    }

    private void attack(){

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
