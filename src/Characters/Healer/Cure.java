package Characters.Healer;

import Characters.Ability;
import Characters.Character;
import Game.Combat;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 10/26/14
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
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
