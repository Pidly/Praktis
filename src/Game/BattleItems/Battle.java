package Game.BattleItems;

import Characters.Ability;
import Characters.Enemy;
import Characters.Healer.Healer;
import Characters.Player;

import Game.InputHandler;

import java.util.List;

public class Battle implements InputHandler {
    List<Player> players;
    List<Enemy> enemies;


    Player player;
    Enemy enemy;
    Healer healer;

    public Battle(List<Player> players, List<Enemy> enemies){
        this.players = players;
        this.enemies = enemies;
    }

    public Battle(List<Player> players, List<Enemy> enemies, Healer healer){
        this.players = players;
        this.enemies = enemies;
        this.healer = healer;
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
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).ready() && players.get(i).isSelected()){
                List<Ability> abilities = players.get(i).getAbilities();
                abilities.get(0).useAbility(enemies);
                players.get(i).resetTimer();
                Player.activePlayer = false;
                players.get(i).setSelected(false);
            }
        }
    }

    @Override
    public void cancel() {

    }

    public List<Player> getPlayers(){
        return players;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    private void attackMob(){

    }

}
