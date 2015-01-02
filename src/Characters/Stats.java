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

    int resourceProgressX, resourceProgressY;
    int currentResourceProgress;

    final int maxHp;
    protected int currentHp;
    final int maxMp;

    float minProgress;
    float maxProgress;

    float currentProgress;

    boolean ready = false;

    public Font awtFont = new Font("Times New Roman", Font.BOLD, 24);

    protected TrueTypeFont font;

    protected int maxResource;
    protected int currentResource;

    public Stats(int maxHp, int maxMp, int width, int height, int playerX, int playerY, int currentHp, int maxResource, int currentResource){
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        this.currentHp = maxHp;

        minProgress = 0;
        this.currentProgress = 0;
        maxProgress = 100;

        progressX = playerX - width/5;
        progressY = playerY + height/2; //+ (height/3);


        this.width = width + (width/3);
        this.height = height / 6;

        resourceProgressX = progressX;
        resourceProgressY = progressY + this.height;

        this.font = new TrueTypeFont(awtFont, true);

        hpX = progressX;
        hpY = progressY - this.height*2;

        currentProgressX = progressX;

        this.currentResource = currentResource;
        this.maxResource = maxResource;
    }

    public void upDate(float updateRate, int currentHp){

        this.currentHp = currentHp;

        if(this.currentProgress > 1){
            this.currentProgress = 1;
            ready = true;
        }else if(!ready){
            this.currentProgress += updateRate;
        }

        if(maxResource != 0)
            currentResourceProgress = currentResource / maxResource;
    }

    public void setCurrentResourceProgressX(int resource){

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

        GL11.glColor3f(0.8f, 0.8f, 0.1f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(resourceProgressX, resourceProgressY);
        GL11.glVertex2f(resourceProgressX + width*currentResourceProgress, progressY);
        GL11.glVertex2f(resourceProgressX + width*currentResourceProgress, progressY+height);
        GL11.glVertex2f(resourceProgressX, progressY+height);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        Color.white.bind();
        font.drawString(hpX, hpY, "HP: " + this.currentHp + "/" + this.maxHp, Color.white);
    }

}
