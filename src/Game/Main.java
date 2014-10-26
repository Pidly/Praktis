package Game;

import Characters.Enemy;
import Characters.Player;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //1280, 720

    public final static int FRAME_RATE = 17;
    public static int screenx = 1920;
    public static int screeny = 1080;
    int x = 300;
    int y = 0;

    int width = 200;

    private boolean antiAlias = true;

    Font font = new Font("Times New Roman", Font.BOLD, 24);
    private TrueTypeFont ttFont;

    Main(){

    }

    public void start(){
        try {
            Display.setDisplayMode(new DisplayMode(screenx, screeny));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        int pX = Main.screenx - Main.screenx/8;
        int pY = Main.screeny/2;
        int pW = Main.screenx/16;
        int pH = Main.screeny/8;

        /*
        this.x = Main.screenx - Main.screenx/8;
        this.y = Main.screeny/2;
        this.width = Main.screenx/16;
        this.height = Main.screeny/8;

        Player player = new Player(pX, pY, screeny/7, screeny/7);
         */

        Player player = new Player(pX, pY, pW, pH);
        Enemy enemy = new Enemy(200, 500, 100, 100);

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glShadeModel(GL11.GL_SMOOTH);

        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        GL11.glClearDepth(1);

        GL11.glEnable(GL11.GL_BLEND);

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glViewport(0,0,screenx,screeny);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, screenx, screeny, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        ttFont = new TrueTypeFont(font, antiAlias);
        // init openGL here

        List<Player> players = new ArrayList<Player>();
        List<Enemy> enemies = new ArrayList<Enemy>();

        players.add(player);
        enemies.add(enemy);

        Battle battle = new Battle(player, enemy);

        while(!Display.isCloseRequested()){

            long currentMillis = System.currentTimeMillis();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


            GL11.glDisable(GL11.GL_TEXTURE_2D);
            enemy.draw();
            enemy.update();

            player.draw();
            player.update();


            Input.getInput(battle);

            Display.update();

            try {
                Thread.sleep(getLoopSleeptime(currentMillis));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Display.destroy();
    }

    private long getLoopSleeptime(long milliseconds){
        long millis = System.currentTimeMillis();

        millis = millis - milliseconds;
        millis = FRAME_RATE - millis;

        if(millis < 0)
            millis = 0;

        return millis;
    }

    public static void main(String args[]){
        Main main = new Main();
        main.start();
    }
}
