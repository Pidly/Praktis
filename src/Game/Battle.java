package Game;

import Characters.Enemy;
import Characters.Player;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class Battle implements InputHandler{
    List<Player> players;
    Player player;
    Enemy enemy;

    Battle(List<Player> players, List<Enemy> enemies){
        this.players = players;
    }

    Battle(Player player){
        this.player = player;
    }

    Battle(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void confirm() {
        player.attack(enemy);
    }

    @Override
    public void cancel() {

    }

    private void attackMob(){

    }

}
