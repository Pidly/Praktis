package Characters;

import Game.Combat;

import java.util.List;

public abstract class Ability {
    protected String name;
    protected long castTime;

    abstract public void useAbility(List<? extends Character> targets);
    abstract public boolean offensiveMove();
    abstract public int numberOfHits();
    abstract public boolean allTargets();
}
