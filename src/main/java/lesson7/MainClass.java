package lesson7;

public class MainClass {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Барсик", 5);
        Cat cat2 = new Cat("Пушистик", 6);
        Cat cat3 = new Cat("Котяра", 7);
        Cat cat4 = new Cat("Бандит", 10);
        Plate plate = new Plate(50);
        plate.info();
        cat1.eat(plate);
        cat2.eat(plate);
        cat3.eat(plate);
        cat4.eat(plate);
        plate.info();
        plate.increaseFood(20);
        plate.info();
    }
}