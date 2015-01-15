package Display;

import Characters.*;
import Characters.Character;
import Game.BattleItems.Battle;
import org.lwjgl.opengl.GL11;

public class BattleDisplay {
    private int leftDisplay;
    private int rightDisplay;
    private int topDisplay;
    private int bottomDisplay;

    private int screenWidth;
    private int screenHeight;

    private int partyScreenXpos;

    public int px, py;
    public int p2x, p2y;
    public int p3x, p3y;

    BattleDisplay(ScreenDisplay screenDisplay){
        bottomDisplay = screenDisplay.bottomScreenPosition;
        topDisplay = screenDisplay.topScreenPosition;
        rightDisplay = screenDisplay.rightScreenPosition;
        leftDisplay = screenDisplay.leftScreenPosition;

        screenWidth = screenDisplay.playableScreenWidth;
        screenHeight = screenDisplay.playableScreenHeight;

        partyScreenXpos = rightDisplay - (screenWidth / 4);

        setCharacterPositions();
    }

    public void draw(Battle battle){
        GL11.glColor3f(0.1f, 0.1f, 0.3f);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2f(leftDisplay, topDisplay);
        GL11.glVertex2f(rightDisplay, topDisplay);
        GL11.glVertex2f(rightDisplay, bottomDisplay);
        GL11.glVertex2f(leftDisplay, bottomDisplay);
        GL11.glEnd();

        GL11.glColor3f(0.5f, 0.8f, 0.5f);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2f(px, py);
        GL11.glVertex2f(px + ScreenDisplay.tileSize, py);
        GL11.glVertex2f(px + ScreenDisplay.tileSize, py - ScreenDisplay.tileSize);
        GL11.glVertex2f(px, py - ScreenDisplay.tileSize);
        GL11.glEnd();

        GL11.glColor3f(0.5f, 0.8f, 0.5f);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2f(p2x, p2y);
        GL11.glVertex2f(p2x + ScreenDisplay.tileSize, p2y);
        GL11.glVertex2f(p2x + ScreenDisplay.tileSize, p2y - ScreenDisplay.tileSize);
        GL11.glVertex2f(p2x, p2y - ScreenDisplay.tileSize);
        GL11.glEnd();

        GL11.glColor3f(0.5f, 0.8f, 0.5f);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2f(p3x, p3y);
        GL11.glVertex2f(p3x + ScreenDisplay.tileSize, p3y);
        GL11.glVertex2f(p3x + ScreenDisplay.tileSize, p3y - ScreenDisplay.tileSize);
        GL11.glVertex2f(p3x, p3y - ScreenDisplay.tileSize);
        GL11.glEnd();

        Character character = battle.getTarget();

        if(character != null){
            if(character.isEnemy()) {
                int x, y, width, height;
                width = character.getWidth() / 4;
                height = character.getHeight() / 4;
                x = character.getX() + character.getWidth() + character.getWidth() / 4;
                y = character.getY() - character.getHeight() / 2;

                GL11.glColor3f(1.0f, 1.0f, 1.0f);

                GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(x, y);
                GL11.glVertex2f(x + width, y);
                GL11.glVertex2f(x + width, y - height);
                GL11.glVertex2f(x, y - height);
                GL11.glEnd();
            }else{
                int x, y, width, height;

                width = character.getWidth() / 4;
                height = character.getWidth() / 4;

                x = character.getX() - character.getWidth()/2;
                y = character.getY() - character.getHeight() / 2;

                GL11.glColor3f(1.0f, 1.0f, 1.0f);

                GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(x, y);
                GL11.glVertex2f(x + width, y);
                GL11.glVertex2f(x + width, y - height);
                GL11.glVertex2f(x, y - height);
                GL11.glEnd();
            }
        }

        Character selectedPlayer = battle.getActiveCharacter();
        if(selectedPlayer != null){
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);

            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(selectedPlayer.getX() - 5, selectedPlayer.getY() + 5);
            GL11.glVertex2f(selectedPlayer.getX() + selectedPlayer.getWidth() + 5, selectedPlayer.getY() + 5);
            GL11.glVertex2f(selectedPlayer.getX() + selectedPlayer.getWidth() + 5, selectedPlayer.getY() - selectedPlayer.getHeight() - 5);
            GL11.glVertex2f(selectedPlayer.getX() - 5, selectedPlayer.getY() - selectedPlayer.getHeight() - 5);
            GL11.glEnd();
        }
    }

    private void setCharacterPositions(){
        int yOffset = bottomDisplay;
        py = (int)(screenHeight * .33) - screenHeight/12 + yOffset; //- (ScreenDisplay.tileSize/2);
        px = partyScreenXpos;


        p2y = (int)(screenHeight * .66) - screenHeight/12 + yOffset;// - (ScreenDisplay.tileSize/2);
        p2x = partyScreenXpos;

        p3y = screenHeight - screenHeight/7 + yOffset; //- (ScreenDisplay.tileSize/2);
        p3x = partyScreenXpos;
    }

    public int getLeftDisplay() {
        return leftDisplay;
    }

    public int getRightDisplay() {
        return rightDisplay;
    }

    public int getTopDisplay() {
        return topDisplay;
    }

    public int getBottomDisplay() {
        return bottomDisplay;
    }
}
