package java2lesson1;

public class Wall implements Obstacles {


    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public int height() {
        return height;
    }

}
