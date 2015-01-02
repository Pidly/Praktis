package Display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class ScreenDisplay {
    public int screenWidth;
    public int screenHeight;

    public int playableScreenWidth;
    public int playableScreenHeight;

    public int leftScreenPosition;
    public int rightScreenPosition;
    public int topScreenPosition;
    public int bottomScreenPosition;

    private DisplayMode displayMode;
    private BattleDisplay battleDisplay;

    public static int tileSize;

    public ScreenDisplay(){
        displayMode = Display.getDesktopDisplayMode();

        screenWidth = displayMode.getWidth();
        screenHeight = displayMode.getHeight();
        //displayMode = new DisplayMode(screenWidth, screenHeight);

        float aspectRatio = (float)screenWidth / (float)screenHeight;

        System.out.println("Initial Aspect Ratio: " + aspectRatio + "\n");

        if(aspectRatio > 1.6){
            double newAspectRatio = ((float)screenHeight * 1.6) / screenWidth;
            System.out.println("New Ratio: " + newAspectRatio);
            playableScreenWidth = (int)(newAspectRatio * screenWidth);
            playableScreenHeight = screenHeight;

            int difference = screenWidth - playableScreenWidth;

            leftScreenPosition = difference / 2;
            rightScreenPosition = leftScreenPosition + playableScreenWidth;
            topScreenPosition = screenHeight;
            bottomScreenPosition = 0;
        }
        else if(aspectRatio < 1.6){
            //double newAspectRatio = ((float)screenHeight * 1.6) / screenWidth;
            double newAspectRatio = ((float)screenWidth / (1.6 * screenHeight));
            System.out.println("New Ratio: " + newAspectRatio);
            //playableScreenHeight = (int)(((float)screenWidth*newAspectRatio) / 1.6);
            playableScreenHeight = (int)(screenHeight * newAspectRatio);
            playableScreenWidth = screenWidth;

            int difference = screenHeight - playableScreenHeight;

            leftScreenPosition = 0;
            rightScreenPosition = screenWidth;
            bottomScreenPosition = difference/2;
            topScreenPosition = bottomScreenPosition + playableScreenHeight;
        }

        float newAspectRatio = (float)playableScreenWidth / (float)playableScreenHeight;

        System.out.println("Width: " + screenWidth + " Height: " + screenHeight + " Aspect Ratio: " + aspectRatio + " New Aspect Ratio: " + newAspectRatio);

        tileSize = playableScreenHeight / 8;

        battleDisplay = new BattleDisplay(this);
    }

    public BattleDisplay getBattleDisplay(){
        return battleDisplay;
    }

    public static void main(String args[]){
        ScreenDisplay screenDisplay = new ScreenDisplay();
        screenDisplay.start();
    }

    public DisplayMode getDisplayMode(){
        return displayMode;
    }

    public void start(){

        try {
            Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, screenWidth, 0, screenHeight, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        while(!Display.isCloseRequested()){
            Display.update();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            GL11.glColor3f(0.7f, 0.7f, 0.9f);
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(0, screenHeight);
                GL11.glVertex2f(screenWidth, screenHeight);
                GL11.glVertex2f(screenWidth, 0);
                GL11.glVertex2f(0, 0);
            GL11.glEnd();

            GL11.glColor3f(0.4f, 0.4f, 0.8f);
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(leftScreenPosition, topScreenPosition);
                GL11.glVertex2f(rightScreenPosition, topScreenPosition);
                GL11.glVertex2f(rightScreenPosition, bottomScreenPosition);
                GL11.glVertex2f(leftScreenPosition, bottomScreenPosition);
            GL11.glEnd();

            battleDisplay.draw();
        }

        Display.destroy();
    }
}
