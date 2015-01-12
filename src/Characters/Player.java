package Characters;

import Game.Combat;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 10/11/14
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Player extends Character implements Combat {

    public static boolean activePlayer = false;
    private boolean selected = false;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        enemy = false;
    }

    @Override
    public void takeDamage(int damage) {
        currentHp -= damage;

        if(currentHp < 0){
            currentHp = 0;
        }
    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    @Override
    public int dealDamage() {
        return str;
    }

    public void checkIfSelected(){
        if(ready()){
            if(!Player.activePlayer){
                activePlayer = true;
                setSelected(true);
            }
        }
    }
}
