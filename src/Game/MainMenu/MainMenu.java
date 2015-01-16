package Game.MainMenu;

import Display.MainScreenDisplay;
import Game.BattleItems.Battle;
import Game.Input;
import Game.InputHandler;
import Game.Scene;

import java.util.ArrayList;
import java.util.List;

public class MainMenu implements Scene{
    MainScreenDisplay mainScreenDisplay;
    private boolean transitionToBattle = false;
    public final static String sceneName = "MAIN_MENU";
    List<Scene> sceneList;

    public MainMenu(MainScreenDisplay mainScreenDisplay){
        this.mainScreenDisplay = mainScreenDisplay;
        sceneList = new ArrayList<Scene>();
    }


    @Override
    public void update() {
        draw();
    }

    @Override
    public void draw(){
        mainScreenDisplay.draw();
    }

    @Override
    public String getSceneName() {
        return MainMenu.sceneName;
    }

    @Override
    public boolean sceneOver() {
        return transitionToBattle;
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
    public void confirm(int keyCode) {
        transitionToBattle = true;
    }

    @Override
    public void cancel() {
        Input.setExitState(true);
    }
}
