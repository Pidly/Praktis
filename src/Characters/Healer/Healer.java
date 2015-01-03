package Characters.Healer;

import Characters.Ability;
import Characters.Character;
import Characters.Player;
import Characters.Stats;
import Game.Combat;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 10/26/14
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Healer extends Player {

    public Healer(int x, int y, int width, int height){
        super(x, y, width, height);

        abilities = new ArrayList<Ability>();
        abilities.add(new HealerPrimaryAbility(this));
        //abilities.add(new Cure());

        str = 10;
        spirit = 20;

        this.currentHp = 20;

        hp = 30;

        stats = new Stats(hp, 25, width, height, this.x, this.y, this.currentHp, maxClassResource, currentClassResource);
    }

    @Override
    public void draw() {
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glColor4f(0.2f, 1.0f, 0.5f, 1.0f);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + width, y);
        GL11.glVertex2f(x + width, y - height);
        GL11.glVertex2f(x, y - height);

        GL11.glEnd();

        stats.draw();
    }
    /*
        GL11.glVertex2f(px, py);
        GL11.glVertex2f(px + ScreenDisplay.tileSize, py);
        GL11.glVertex2f(px + ScreenDisplay.tileSize, py - ScreenDisplay.tileSize);
        GL11.glVertex2f(px, py - ScreenDisplay.tileSize);
        GL11.glEnd();
     */

    @Override
    public void update() {
        if(this.currentHp < 0)
            this.currentHp = 0;

        stats.upDate(0.008f, this.currentHp);

        checkIfSelected();
    }
}
