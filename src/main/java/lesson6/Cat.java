package lesson6;


public class Cat extends Animal {


    private static int catRun = 200;

    public Cat(String name) {
        super(name);
    }


    @Override
    public void run(int obstacleLength) {
        if (obstacleLength > catRun) {
            System.out.print("Cat " + name + " run " + catRun + " and is tired. He need rest.");
            System.out.println();
        } else {
            System.out.print("Cat " + name + " run " + obstacleLength);
            System.out.println();
        }
    }


    @Override
    public void swim(int obstacleLength) {
        System.out.println("Cat can't swim");

    }
}



