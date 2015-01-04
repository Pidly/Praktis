package Characters.Healer;


import Characters.*;
import Characters.Character;
import Game.BattleItems.StatusEffect;

import java.util.List;

public class HealerPrimaryAbility extends Ability implements StatusEffect{
    Character character;

    public static final String HEALER_PRIMARY = "healer_primary";

    private int maxNumberOfTicks = 5;
    private int currentTicks = 0;

    private int tickPotency = 10;

    private double tickTime;

    HealerPrimaryAbility(Character character){
        this.character = character;
        tickTime = System.currentTimeMillis();
    }

    @Override
    public void useAbility(List<? extends Character> targets) {
        boolean hasStatusEffect = false;
        List<StatusEffect> statusEffects = character.getStatusEffects();

        for(StatusEffect statusEffect : statusEffects){
            if(statusEffect.statusEffectName().equals(HEALER_PRIMARY)){
                hasStatusEffect = true;
                statusEffect.resetStatus();
            }
        }
        if(!hasStatusEffect)
            character.addStatusEffect(new HealerPrimaryAbility(character));

    }

    @Override
    public boolean offensiveMove() {
        return false;
    }

    @Override
    public int numberOfHits() {
        return 0;
    }

    @Override
    public boolean allTargets() {
        return false;
    }

    @Override
    public void update(Character character) {
        character.increaseCurrentResourceBy(tickPotency);
        currentTicks++;
        tickTime = System.currentTimeMillis() + 1000;
    }

    @Override
    public boolean ready() {
        double currentTime = System.currentTimeMillis();
        boolean ready = false;

        if(tickTime < currentTime){
            ready = true;
        }
        return ready;
    }

    @Override
    public boolean finished() {
        boolean finished = false;

        if(currentTicks >= maxNumberOfTicks)
            finished = true;

        return finished;
    }

    @Override
    public void draw() {

    }

    @Override
    public String statusEffectName() {
        return HealerPrimaryAbility.HEALER_PRIMARY;
    }

    @Override
    public void resetStatus() {
        currentTicks = 0;
    }
}
