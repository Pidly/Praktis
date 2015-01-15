package Game.BattleItems;

import Characters.*;
import Characters.Character;
import Characters.Healer.Healer;

import Display.BattleDisplay;
import Game.InputHandler;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class Battle implements InputHandler {
    List<Player> players;
    List<Enemy> enemies;

    public Map<Integer, Integer> abilities = new HashMap<Integer, Integer>();

    private BattleDisplay battleDisplay;

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

    public Battle(List<Player> players, List<Enemy> enemies, Healer healer, BattleDisplay battleDisplay){
        this.players = players;
        this.enemies = enemies;
        this.healer = healer;
        this.battleDisplay = battleDisplay;

        abilities.put(Keyboard.KEY_A, 0);
        abilities.put(Keyboard.KEY_W, 1);
        abilities.put(Keyboard.KEY_D, 2);
        abilities.put(Keyboard.KEY_S, 3);
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
        if(selectingTarget == true){
            targetedCharacter++;
            if(activeAbility.offensiveMove()) {
                if (targetedCharacter >= enemies.size()) {
                    targetedCharacter = 0;
                }
            }else{
                if(targetedCharacter >= players.size()){
                    targetedCharacter = 0;
                }
            }

        }
    }

    @Override
    public void down() {
        if(selectingTarget == true){
            targetedCharacter--;
            if(activeAbility.offensiveMove()) {
                if (targetedCharacter < 0) {
                    targetedCharacter = enemies.size() - 1;
                }
            }else{
                if(targetedCharacter < 0){
                    targetedCharacter = players.size() - 1;
                }
            }
        }
    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void confirm(int keyCode) {
        for(int i = 0; i < players.size(); i++){
            if(selectingTarget && players.get(i).isSelected() && players.get(i).ready()){
                if(activeAbility.offensiveMove()) {
                    updateEnemies();

                    selectingTarget = false;
                    targetedCharacters = new ArrayList<Character>();
                    targetedCharacters.add(enemies.get(targetedCharacter));
                    activeAbility.useAbility(targetedCharacters);

                    player.resetTimer();
                    Player.activePlayer = false;
                    player.setSelected(false);

                    updateEnemies();
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

                activeAbility = abilities.get(this.abilities.get(keyCode));

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
            }
        }
    }

    public Character getActiveCharacter(){
        Character character = null;

        for(Player player : players){
            if(player.ready() && player.isSelected()){
                character = player;
            }
        }
        return character;
    }

    @Override
    public void cancel() {

    }

    //-------------------------------------------//
    public Character getTarget(){
        if(selectingTarget){
            if(activeAbility.offensiveMove()){
                return enemies.get(targetedCharacter);
            }else{
                return players.get(targetedCharacter);
            }
        }else{
            return null;
        }
    }

    public void draw(){
        battleDisplay.draw(this);
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

    private void updateEnemies(){
        Iterator<Enemy> enemyIterator = enemies.iterator();

        Enemy tempEnemy;

        while(enemyIterator.hasNext()){
            tempEnemy = enemyIterator.next();
            if(tempEnemy.getHp() <= 0){
                enemyIterator.remove();
            }
        }

        System.out.println("Size: " + enemies.size());
    }
}
