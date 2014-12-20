package Game;

import org.lwjgl.input.Keyboard;

public class Input {

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
            }
        }
        /*
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            inputHandler.confirm();
        }
        */
    }

}
