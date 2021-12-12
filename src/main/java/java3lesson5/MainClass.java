package java3lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    static CountDownLatch startLine = new CountDownLatch(CARS_COUNT + 1);
    static CyclicBarrier roadStage = new CyclicBarrier(CARS_COUNT);
    static Semaphore tunnel = new Semaphore(CARS_COUNT/2,true);
    static CountDownLatch finishLine = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) {


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        while (startLine.getCount() > 0) // Проверка на старте
            try {
                Thread.sleep(100);
                if (startLine.getCount() == 1) {
                    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                    startLine.countDown();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        while (finishLine.getCount() > 0) //Проверка на финише
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }



}

