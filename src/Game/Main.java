package Game;

import Characters.Caster.Caster;
import Characters.Enemy;
import Characters.Healer.Healer;
import Characters.Player;
import Characters.Warrior.Warrior;
import Game.BattleItems.Battle;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;

import Display.ScreenDisplay;
import Display.BattleDisplay;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public final static int FRAME_RATE = 17;

    int x = 300;
    int y = 0;

    int width = 200;

    private boolean antiAlias = true;

    Font font = new Font("Times New Roman", Font.BOLD, 24);
    private TrueTypeFont ttFont;

    Main(){

    }

    public void start(){
        ScreenDisplay screenDisplay = null;
        try {
            screenDisplay = new ScreenDisplay();

            Display.setDisplayMode(screenDisplay.getDisplayMode());

            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        BattleDisplay battleDisplay = screenDisplay.getBattleDisplay();

        int pX = battleDisplay.px;
        int pY = battleDisplay.py;
        int pW = ScreenDisplay.tileSize;
        int pH = ScreenDisplay.tileSize;


        Warrior warrior = new Warrior(pX, pY, pW, pH);

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glShadeModel(GL11.GL_SMOOTH);

        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        GL11.glClearDepth(1);

        GL11.glEnable(GL11.GL_BLEND);

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glViewport(0, 0, screenDisplay.screenWidth, screenDisplay.screenHeight);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, screenDisplay.screenWidth, screenDisplay.screenHeight, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        ttFont = new TrueTypeFont(font, antiAlias);
        // init openGL here

        List<Player> players = new ArrayList<Player>();
        List<Enemy> enemies = new ArrayList<Enemy>();
        Healer healer = new Healer(battleDisplay.p2x, battleDisplay.p2y, ScreenDisplay.tileSize, ScreenDisplay.tileSize);

        Caster caster = new Caster(battleDisplay.p3x, battleDisplay.p3y, ScreenDisplay.tileSize, ScreenDisplay.tileSize);

        players.add(warrior);
        players.add(healer);
        players.add(caster);

        Enemy enemy = new Enemy(ScreenDisplay.tileSize + battleDisplay.getLeftDisplay(), ScreenDisplay.tileSize*2 + battleDisplay.getBottomDisplay(), ScreenDisplay.tileSize, ScreenDisplay.tileSize, players);
        Enemy enemy2 = new Enemy(ScreenDisplay.tileSize + battleDisplay.getLeftDisplay(), ScreenDisplay.tileSize*4 + battleDisplay.getBottomDisplay(), ScreenDisplay.tileSize, ScreenDisplay.tileSize, players);

        enemies.add(enemy);
        enemies.add(enemy2);

        Battle battle = new Battle(players, enemies, healer);

        while(!Display.isCloseRequested() && !Input.getExitState()){

            long currentMillis = System.currentTimeMillis();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


            GL11.glDisable(GL11.GL_TEXTURE_2D);

            battleDisplay.draw();

            for(Enemy enemy1 : enemies){
                enemy1.draw();
                enemy1.update();
                enemy1.updateStatusEffect();
            }

            for(Player player1 : players){
                player1.draw();
                player1.update();
                player1.updateStatusEffect();
            }

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
