package java2lesson5;

public class Streams {


    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;
    float[] arr = new float[SIZE];

    public static void sizeArray() {
        Thread thread = new Thread(() -> {
            float[] arr = new float[SIZE];
            for (int i = 0; i < SIZE; i++) {
                arr[i] = 1;
            }
            long a = System.currentTimeMillis();

            for (int i = 0; i < SIZE; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }

            System.out.println("Пробный общий поток " + (System.currentTimeMillis() - a));

        });
        thread.start();


    }

    public static void halfArray() throws InterruptedException {
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();


        float [] arrLeft = new float [HALF];
        System.arraycopy(arr, 0, arrLeft, 0, HALF);
        float [] arrRight = new float [HALF];
        System.arraycopy(arr, HALF, arrRight, 0, HALF);


        Thread thread1 = new Thread(() -> {
            long a = System.currentTimeMillis();
            for (int i = 0; i < HALF; i++) {
                   arrLeft[i] = (float) (arrLeft[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
               }
             System.out.println("Первый поток " + (System.currentTimeMillis() - a));

            });
            thread1.start();
            thread1.join();

        Thread thread2 = new Thread(()->{
            long a = System.currentTimeMillis();
              for (int i = 0; i < HALF; i++) {
                   arrRight[i] = (float) (arrRight[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
              }
            System.out.println("Второй поток " + (System.currentTimeMillis() - a));

            });
            thread2.start();
            thread2.join();

        System.arraycopy(arrLeft, 0, arr, 0, HALF);
        System.arraycopy(arrRight, 0, arr, HALF, HALF);

        System.out.println("Время работы двух потоков: " + (System.currentTimeMillis() - startTime) + " ms.");

    };



    public static void main(String[] args) throws InterruptedException {
        sizeArray();
        halfArray();

    }
    }

