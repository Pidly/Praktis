package Characters;

import Game.Main;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {

    protected int hp;
    protected int mp;

    protected int str;
    protected int spirit;

    protected int currentHp;

    protected int x, y;
    protected int width, height;

    protected int currentClassResource = 0;
    protected int maxClassResource = 0;

    protected List<Ability> abilities = new ArrayList<Ability>();

    String name;

    public Stats stats;

    public Character(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getHp(){
        return hp;
    }

    abstract public void draw();
    abstract public void update();
    abstract public void takeDamage(int damage);

    public void resetTimer(){
        stats.resetProgressBar();
    }

    public List<Ability> getAbilities(){
        return abilities;
    }

    public void healDamage(int healAmount){
        currentHp += healAmount;

        if(currentHp > hp)
            currentHp = hp;
    }

    public boolean ready(){
        return stats.ready;
    }

    public void increaseCurrentResourceBy(int amount){
        currentClassResource += amount;
    }

}
