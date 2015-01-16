package Game;

import Display.ScreenDisplay;
import Game.BattleItems.Battle;
import Game.MainMenu.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class SceneHandler {
    List<Scene> sceneList;
    ScreenDisplay screenDisplay;
    Battle battle;
    MainMenu mainMenu;
    Scene currentScene;

    public SceneHandler(ScreenDisplay screenDisplay){
        this.screenDisplay = screenDisplay;
        sceneList = new ArrayList<Scene>();
        mainMenu = new MainMenu(screenDisplay.getMainScreenDisplay());
        currentScene = mainMenu;
    }

    public Scene getFirstScene(){
        return mainMenu;
    }

    public void update(){
        currentScene.update();
        if(currentScene.sceneOver()){
            if(currentScene.getSceneName().equals(MainMenu.sceneName)){
                currentScene = new Battle(screenDisplay.getBattleDisplay());
            }else{
                currentScene = new MainMenu(screenDisplay.getMainScreenDisplay());
            }
        }
        Input.getInput(currentScene);
    }
}
