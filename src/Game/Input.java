package Game;

import org.lwjgl.input.Keyboard;

public class Input {

    public static void getInput(InputHandler inputHandler){
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            inputHandler.confirm();
        }
    }

}
