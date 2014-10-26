package Characters;

import Game.Main;

public abstract class Character {
    int hp;
    int mp;

    int str;
    int spirit;

    int maxhp, maxmp;
    int currentHp;

    int speed;

    int x, y;
    int width, height;

    int test;

    String name;

    Character(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getHp(){
        return hp;
    }

    abstract void draw();
    abstract void update();
}
