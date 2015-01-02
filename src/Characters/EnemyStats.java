package Characters;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 11/8/14
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class EnemyStats extends Stats {
    public EnemyStats(int maxHp, int maxMp, int width, int height, int playerX, int playerY, int currentHp) {
        super(maxHp, maxMp, width, height, playerX, playerY, currentHp, 0, 0);
    }

    @Override
    public void draw(){
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glColor4f(0.5f, 0.2f, .3f, 1.0f);

        //Drawing progress bar
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2f(progressX, progressY);
        GL11.glVertex2f(progressX + width*currentProgress, progressY);
        GL11.glVertex2f(progressX + width*currentProgress, progressY + height);
        GL11.glVertex2f(progressX, progressY + height);

        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        Color.white.bind();
        font.drawString(hpX, hpY, "HP: " + this.currentHp + "/" + this.maxHp, Color.white);
    }
}
