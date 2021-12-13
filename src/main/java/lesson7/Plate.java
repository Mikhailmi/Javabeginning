package lesson7;

public class Plate {
    private int food;
    public Plate(int food) {
        this.food = food;
    }
    public void decreaseFood(int n) {
        if (food < n) {
            System.out.println("Еды не хватило. Кот голодный.");
        } else {
            food -= n;
        }
    }

    public void info() {
        System.out.println("plate: " + food);
    }

    public void increaseFood(int n) {
        food += n;

    }
}
