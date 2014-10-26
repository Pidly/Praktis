package Characters;

import Characters.Player;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;

import java.awt.Font;

public class Stats {
    int progressX,progressY;
    int currentProgressX;
    int hpX, hpY;

    int width, height;

    final int maxHp;
    private int currentHp;
    final int maxMp;

    float minProgress;
    float maxProgress;

    float currentProgress;

    boolean ready = false;

    Font awtFont = new Font("Times New Roman", Font.BOLD, 24);

    private TrueTypeFont font;

    Stats(int maxHp, int maxMp, int width, int height, int playerX, int playerY, int currentHp){
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        this.currentHp = maxHp;

        minProgress = 0;
        this.currentProgress = 0;
        maxProgress = 100;

        progressX = playerX - width/5;
        progressY = playerY + height + (height/3);

        this.width = width + (width/3);
        this.height = height / 6;

        this.font = new TrueTypeFont(awtFont, true);

        hpX = progressX;
        hpY = progressY - this.height*2;

        //Working on getting a moving progress bar.
        currentProgressX = progressX;
    }

    public void upDate(float updateRate, int currentHp){

        this.currentHp = currentHp;

        if(this.currentProgress > 1){
            this.currentProgress = 1;
            ready = true;
        }else if(!ready){
            this.currentProgress += updateRate;
        }
    }

    public void resetProgressBar(){
        this.currentProgress = 0;
        ready = false;
    }

    public void draw(){
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glColor4f(0.5f, 0.2f, .3f, 1.0f);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2f(progressX, progressY);
        GL11.glVertex2f(progressX + width*currentProgress, progressY);
        GL11.glVertex2f(progressX + width*currentProgress, progressY+height);
        GL11.glVertex2f(progressX, progressY+height);

        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        Color.white.bind();
        font.drawString(hpX, hpY, "HP: " + this.currentHp + "/" + this.maxHp, Color.white);
    }

}
