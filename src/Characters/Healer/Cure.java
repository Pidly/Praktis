package Characters.Healer;

import Characters.Ability;
import Characters.Character;
import Game.Combat;


import java.util.List;

public class Cure extends Ability {

    @Override
    public void useAbility(List<? extends Character> targets) {
        for(Character target : targets){
            target.healDamage(1);
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
