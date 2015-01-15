package Game;

import org.lwjgl.input.Keyboard;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class Input {
    private static boolean exitGame = false;

    int rightAbility = Keyboard.KEY_B;

    public static synchronized boolean getExitState(){
        return exitGame;
    }

    public static synchronized void setExitState(boolean exit){
        exitGame = exit;
    }

    public static void getInput(InputHandler inputHandler){
        while(Keyboard.next()){
            if(Keyboard.getEventKeyState()){
                if(Keyboard.getEventKey() == Keyboard.KEY_A){
                    //Maybe add stuff here later.
                }
            }else{
                int keyCode = Keyboard.getEventKey();
                if(keyCode == Keyboard.KEY_A || keyCode == Keyboard.KEY_W){
                    inputHandler.confirm(keyCode);
                }
                else if(Keyboard.getEventKey() == Keyboard.KEY_B) {
                    setExitState(true);
                }
                else if(Keyboard.getEventKey() == Keyboard.KEY_DOWN){
                    inputHandler.down();
                }
                else if(Keyboard.getEventKey() == Keyboard.KEY_UP){
                    inputHandler.up();
                }
            }
        }
        /*
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            inputHandler.confirm();
        }
        */
    }

}
