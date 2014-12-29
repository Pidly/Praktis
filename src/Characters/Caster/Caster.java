package Characters.Caster;


import Characters.AttackAbility;
import Characters.Player;
import Characters.Stats;
import org.lwjgl.opengl.GL11;

public class Caster extends Player {
    private boolean selected = false;
    AttackAbility attackAbility;

    public Caster(int x, int y, int width, int height) {
        super(x, y, width, height);

        attackAbility = new AttackAbility();

        abilities.add(attackAbility);

        str = 20;
        spirit = 10;

        this.currentHp = 40;

        hp = 50;
        mp = 20;
        stats = new Stats(hp, mp, width, height, this.x, this.y, this.currentHp);
    }

    @Override
    public void draw() {
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glColor4f(0.2f, 0.5f, 1.0f, 1.0f);

        GL11.glBegin(GL11.GL_QUADS);


        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x+width, y);
        GL11.glVertex2f(x+width, y - height);
        GL11.glVertex2f(x, y - height);

        GL11.glEnd();

        stats.draw();
    }

    @Override
    public void update() {
        if(this.currentHp < 0)
            this.currentHp = 0;

        stats.upDate(0.01f, this.currentHp);

        checkIfSelected();
    }
}
