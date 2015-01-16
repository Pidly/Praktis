package Game.BattleItems;

import Characters.*;
import Characters.Caster.Caster;
import Characters.Character;
import Characters.Healer.Healer;

import Characters.Warrior.Warrior;
import Display.BattleDisplay;
import Display.ScreenDisplay;
import Game.InputHandler;
import Game.Scene;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class Battle implements Scene {
    List<Player> players;
    List<Enemy> enemies;

    public static final String SCENE_NAME = "BATTLE_SCENE";
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

    public Battle(BattleDisplay battleDisplay){
        this.battleDisplay = battleDisplay;

        initPlayers();
        initEnemies();

        abilities.put(Keyboard.KEY_A, 0);
        abilities.put(Keyboard.KEY_W, 1);
        abilities.put(Keyboard.KEY_D, 2);
        abilities.put(Keyboard.KEY_S, 3);
    }

    private void initPlayers(){
        players = new ArrayList<Player>();

        int pX = battleDisplay.px;
        int pY = battleDisplay.py;
        int pW = ScreenDisplay.tileSize;
        int pH = ScreenDisplay.tileSize;


        Warrior warrior = new Warrior(pX, pY, pW, pH);
        Healer healer = new Healer(battleDisplay.p2x, battleDisplay.p2y, ScreenDisplay.tileSize, ScreenDisplay.tileSize);
        Caster caster = new Caster(battleDisplay.p3x, battleDisplay.p3y, ScreenDisplay.tileSize, ScreenDisplay.tileSize);

        players.add(caster);
        players.add(healer);
        players.add(warrior);
    }

    private void initEnemies(){
        enemies = new ArrayList<Enemy>();

        Enemy enemy = new Enemy(ScreenDisplay.tileSize + battleDisplay.getLeftDisplay(), ScreenDisplay.tileSize*2 + battleDisplay.getBottomDisplay(), ScreenDisplay.tileSize, ScreenDisplay.tileSize, players);
        Enemy enemy2 = new Enemy(ScreenDisplay.tileSize + battleDisplay.getLeftDisplay(), ScreenDisplay.tileSize*4 + battleDisplay.getBottomDisplay(), ScreenDisplay.tileSize, ScreenDisplay.tileSize, players);

        enemies.add(enemy);
        enemies.add(enemy2);
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

    @Override
    public void update() {
        draw();

        for(Enemy enemy1 : enemies){
            enemy1.update();
            enemy1.draw();
            enemy1.updateStatusEffect();
        }

        for(Player player1 : players){
            player1.update();
            player1.draw();
            player1.updateStatusEffect();
        }
    }

    public void draw(){
        battleDisplay.draw(this);
    }

    @Override
    public String getSceneName() {
        return Battle.SCENE_NAME;
    }

    @Override
    public boolean sceneOver() {
        boolean gameOver = false;
        int deadCharacters = 0;
        if(enemies.size() <= 0){
            gameOver = true;
        }

        for(Player player : players){
            if(player.getHp() == 0){
                deadCharacters++;
            }
        }

        if(deadCharacters == 3){
            gameOver = true;
        }

        return gameOver;
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
    }
}
