
package lesson6;

public abstract class Animal {

    String name;

    public Animal(String name) {
        this.name = name;

    }


    public void run(int obstacleLength) {
        System.out.println("run " + obstacleLength);
    }

    public void swim(int obstacleLength) {
        System.out.println("swim " + obstacleLength);
    }

}
