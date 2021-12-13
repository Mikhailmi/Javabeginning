package java3lesson1;

public class FruitApp {


    public static void main(String[] args) {
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();

        Box<Apple> box1 = new Box<Apple>(apple1, apple2, apple3);
        Box<Orange> box2 = new Box<Orange>(orange1, orange2);

        System.out.println(box1.compare(box2));

        System.out.println(box2.getWeight());

        Box<Orange> box3 = new Box<Orange>();
        box2.relocateFruit(box3);
        System.out.println(box2.getWeight());
        System.out.println(box3.getWeight());

    }
}
