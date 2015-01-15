package Game;

/**
 * Created with IntelliJ IDEA.
 * User: devinsmythe
 * Date: 10/18/14
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface InputHandler {
    public void up();
    public void down();
    public void left();
    public void right();

    public void confirm(int keyCode);
    public void cancel();
}
