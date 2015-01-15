package Characters.Healer;

import Characters.Ability;
import Characters.Character;

import java.util.List;

public class Cure extends Ability {
    int resourceCost;

    Character character;
    public Cure(Character character){
        this.character = character;
        resourceCost = 20;
    }
    @Override
    public void useAbility(List<? extends Character> targets) {
        if(character.getCurrentClassResource() > resourceCost) {
            character.increaseCurrentResourceBy(-resourceCost);
            for (Character target : targets) {
                target.healDamage(20);
            }
        }
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
        return false;
    }
}
