package lesson6;

public class Dog extends Animal {

    private static int dogRun = 500;
    private static int dogSwim = 10;

    public Dog(String name) {
        super(name);
    }

    @Override
    public void run(int obstacleLength) {

        if (obstacleLength>dogRun){
        System.out.print("Dog " + name + " run " + dogRun + " and is tired. He need rest.");
        System.out.println();
    } else {
        System.out.print("Dog " + name + " run " + obstacleLength);
        System.out.println();
    }
}

    @Override
    public void swim(int obstacleLength) {

        if (obstacleLength>dogSwim){
            System.out.print("Dog " + name + " swam " + dogSwim + " and is tired. He need rest.");
            System.out.println();
        } else {
            System.out.print("Dog " + name + " swam " + obstacleLength);
            System.out.println();
        }
    }




    }



