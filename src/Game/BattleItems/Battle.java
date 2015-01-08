package Game.BattleItems;

import Characters.*;
import Characters.Character;
import Characters.Healer.Healer;

import Game.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class Battle implements InputHandler {
    List<Player> players;
    List<Enemy> enemies;


    Player player;
    Enemy enemy;
    Healer healer;

    private long readyToSelect = 0;

    List<Character> targetedCharacters;
    int targetedCharacter = 0;

    Ability activeAbility;

    private boolean selectingTarget = false;

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
            if(selectingTarget && players.get(i).isSelected() && players.get(i).ready()){
                if(activeAbility.offensiveMove()) {
                    selectingTarget = false;
                    targetedCharacters = new ArrayList<Character>();
                    targetedCharacters.add(enemies.get(targetedCharacter));
                    activeAbility.useAbility(targetedCharacters);

                    player.resetTimer();
                    Player.activePlayer = false;
                    player.setSelected(false);
                }else{
                    selectingTarget = false;
                    targetedCharacters = new ArrayList<Character>();
                    targetedCharacters.add(players.get(targetedCharacter));
                    activeAbility.useAbility(targetedCharacters);

                    player.resetTimer();
                    Player.activePlayer = false;
                    player.setSelected(false);
                }
            }
            else if(players.get(i).ready() && players.get(i).isSelected() && !selectingTarget){
                List<Ability> abilities = players.get(i).getAbilities();

                activeAbility = abilities.get(0);

                if(activeAbility.allTargets()){
                    if(activeAbility.offensiveMove()){
                        activeAbility.useAbility(enemies);
                        players.get(i).resetTimer();
                        Player.activePlayer = false;
                        players.get(i).setSelected(false);
                    }else{
                        activeAbility.useAbility(players);
                        players.get(i).resetTimer();
                        Player.activePlayer = false;
                        players.get(i).setSelected(false);
                    }
                }else{
                    selectingTarget = true;
                    targetedCharacter = 0;
                    player = players.get(i);
                }

                /*
                abilities.get(0).useAbility(enemies);
                players.get(i).resetTimer();
                Player.activePlayer = false;
                players.get(i).setSelected(false);
                */

            }
        }
    }

    @Override
    public void cancel() {

    }

    public boolean isSelectingTarget(){
        return selectingTarget;
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
