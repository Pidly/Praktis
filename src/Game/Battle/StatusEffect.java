package Game.Battle;

import Characters.Character;

public interface StatusEffect {
    public void update(Character character);
    public boolean ready();
    public boolean finished();
    public void draw();
    public String statusEffectName();
    public void resetStatus();
}
