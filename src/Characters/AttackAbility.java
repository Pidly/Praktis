package Characters;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 10/28/14
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class AttackAbility extends Ability {
    Character character;

    public AttackAbility(Character character){
        this.character = character;
    }

    @Override
    public void useAbility(List<? extends Character> targets) {
        targets.get(0).takeDamage(5);
    }

    @Override
    public boolean offensiveMove() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int numberOfHits() {
        return 1;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean allTargets() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
