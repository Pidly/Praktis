package Characters.Warrior;

import Characters.*;
import Characters.Character;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 11/10/14
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class WarriorPrimaryAbility extends Ability {
    Character character;

    public WarriorPrimaryAbility(Character character){
        this.character = character;
    }

    @Override
    public void useAbility(List<? extends Character> targets) {
       targets.get(0).takeDamage(calculateDamage());

       character.increaseCurrentResourceBy(10);
    }

    @Override
    public boolean offensiveMove() {
        return true;
    }

    @Override
    public int numberOfHits() {
        return 1;
    }

    @Override
    public boolean allTargets() {
        return false;
    }

    private int calculateDamage(){
        return 10;
    }
}
