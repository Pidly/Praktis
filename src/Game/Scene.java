package Game;

public interface Scene extends InputHandler {
    public void update();
    public void draw();
    public String getSceneName();
    public boolean sceneOver();
}
