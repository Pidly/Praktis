package Display;

import org.lwjgl.opengl.GL11;

public class MainScreenDisplay {
    ScreenDisplay screenDisplay;

    private int bottomDisplay;
    private int topDisplay;
    private int rightDisplay;
    private int leftDisplay;

    private int screenWidth;
    private int screenHeight;

    public MainScreenDisplay(ScreenDisplay screenDisplay){
        this.screenDisplay = screenDisplay;

        bottomDisplay = screenDisplay.bottomScreenPosition;
        topDisplay = screenDisplay.topScreenPosition;
        rightDisplay = screenDisplay.rightScreenPosition;
        leftDisplay = screenDisplay.leftScreenPosition;

        screenWidth = screenDisplay.playableScreenWidth;
        screenHeight = screenDisplay.playableScreenHeight;
    }

    public void draw(){
        GL11.glColor3f(0.7f, 0.1f, 0.7f);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(leftDisplay, topDisplay);
        GL11.glVertex2f(rightDisplay, topDisplay);
        GL11.glVertex2f(rightDisplay, bottomDisplay);
        GL11.glVertex2f(leftDisplay, bottomDisplay);
        GL11.glEnd();
    }

    public void drawTitle(){

    }

    public void drawStart(){

    }

    public void drawQuit(){

    }
}
