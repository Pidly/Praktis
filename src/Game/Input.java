package Game;

import org.lwjgl.input.Keyboard;

public class Input {
    private static boolean exitGame = false;

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
                if(Keyboard.getEventKey() == Keyboard.KEY_A){
                    inputHandler.confirm();
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
