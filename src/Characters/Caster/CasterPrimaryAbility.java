package Characters.Caster;

import Characters.*;
import Characters.Character;
import Game.BattleItems.StatusEffect;

import java.util.List;

public class CasterPrimaryAbility extends Ability implements StatusEffect{
    Character character;

    private double tickTime;

    private static final double TICK_INTERVAL = 500;

    private final String CASTER_PRIMARY = "caster_primary";

    private final int TOTAL_TICKS = 10;
    private final int MANA_TICK_POTENCY;
    private final int HP_TICK_POTENCY;

    private int currentTicks = 0;

    CasterPrimaryAbility(Character character){
        this.character = character;
        tickTime = System.currentTimeMillis();

        double oneThirdMana = character.getMaxClassResource() / 3;
        MANA_TICK_POTENCY = (int)(oneThirdMana / 10);

        double oneThirdHp = character.getMaxHp() / 3;
        HP_TICK_POTENCY = (int)(oneThirdHp / 10);
    }

    @Override
    public void useAbility(List<? extends Character> targets) {
        boolean hasStatusEffect = false;
        List<StatusEffect> statusEffects = character.getStatusEffects();

        for(StatusEffect statusEffect : statusEffects){
            if(statusEffect.statusEffectName().equals(CASTER_PRIMARY)){
                hasStatusEffect = true;
                statusEffect.resetStatus();
            }
        }
        if(!hasStatusEffect)
            character.addStatusEffect(new CasterPrimaryAbility(character));
    }

    @Override
    public boolean offensiveMove() {
        return false;
    }

    @Override
    public int numberOfHits() {
        return 1;
    }

    @Override
    public boolean allTargets() {
        return true;
    }

    @Override
    public void update(Character character) {
        currentTicks++;

        character.takeDamage(HP_TICK_POTENCY);
        character.increaseCurrentResourceBy(MANA_TICK_POTENCY);

        tickTime = System.currentTimeMillis() + TICK_INTERVAL;
    }

    @Override
    public boolean ready() {
        double currentTime = System.currentTimeMillis();

        return (currentTime > tickTime);


    }

    @Override
    public boolean finished() {
        boolean finished = false;
        if(currentTicks >= TOTAL_TICKS){
            finished = true;
        }

        return finished;
    }

    @Override
    public void draw() {

    }

    @Override
    public String statusEffectName() {
        return CASTER_PRIMARY;
    }

    @Override
    public void resetStatus() {
        currentTicks = 0;
    }
}
