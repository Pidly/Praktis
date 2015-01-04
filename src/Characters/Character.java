package Characters;

import Game.BattleItems.StatusEffect;

import java.util.ArrayList;
import java.util.Iterator;
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
    protected int maxClassResource = 100;

    protected List<Ability> abilities = new ArrayList<Ability>();

    String name;

    public Stats stats;

    private List<StatusEffect> statusEffects;

    public Character(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        statusEffects = new ArrayList<StatusEffect>();
    }

    public int getHp(){
        return hp;
    }

    abstract public void draw();
    abstract public void update();
    abstract public void takeDamage(int damage);

    public void updateStatusEffect(){
        Iterator<StatusEffect> statusIterator = statusEffects.iterator();
        while(statusIterator.hasNext()){
            StatusEffect statusEffect = statusIterator.next();
            if(statusEffect.ready()){
                statusEffect.update(this);
            }
            if(statusEffect.finished())
                statusIterator.remove();
        }
    }

    public List<StatusEffect> getStatusEffects(){
        return statusEffects;
    }

    public void addStatusEffect(StatusEffect statusEffect){
        statusEffects.add(statusEffect);
    }

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

        if(currentClassResource > maxClassResource)
            currentClassResource = maxClassResource;

        stats.setCurrentResourceProgressX(currentClassResource);
    }

}
