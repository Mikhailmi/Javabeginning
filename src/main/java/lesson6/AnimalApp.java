package lesson6;

public class AnimalApp {

    public static void main(String[] args) {

        int numDogs = 0, numCats = 0, numAnimals = 0;

            Dog dog = new Dog("Братан");
            numDogs = numDogs + 1;
            dog.run(10);
            dog.swim(7);

            Cat cat = new Cat("Пушистик");
            numCats = numCats + 1;
            cat.run(50);
            cat.swim(100);


            numAnimals = numDogs + numCats;

        System.out.println("Всего создано: " + numAnimals + " животных. Из них: " + numDogs + " собак и " + numCats + " кошек.");
    }



}
