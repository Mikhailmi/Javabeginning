package java2lesson1;

public class TestClass  {



    public static void main(String[] args) {

      // первое задание

        Human h1 = new Human (0, 0);
        h1.run();
        h1.jump();

        Cat c1 = new Cat(0, 0);
        c1.run();
        c1.jump();

        Robot r1 = new Robot(0, 0);
        r1.run();
        r1.jump();

        // второе задание

        Treadmill t1 = new Treadmill(0);
        Wall w1 = new Wall(0);
        if (t1 instanceof Treadmill) {
            h1.treadmillRun();
            c1.treadmillRun();
            r1.treadmillRun();
        }

        if (w1 instanceof  Wall) {
            h1.wallJump();
            c1.wallJump();
            r1.wallJump();
        }


        // третье задание

        System.out.println("");
        System.out.println("Третье задание");

       Obstacles[] obstacles1 = new Obstacles[] {t1, w1};
       Participants[] participants1 = new Participants[] {h1, c1, r1};
       for (int j = 0; j < obstacles1.length; j++) {
           if (obstacles1[j] == t1) {
               for (int i = 0; i < participants1.length; i++) {
                   participants1[i].treadmillRun();
               }
           } else {
               for (int i = 0; i < participants1.length; i++) {
                   participants1[i].wallJump();
               }
           }
       }

       // четвертое задание

        System.out.println("");
        System.out.println("Четвертое задание");


        Human h2 = new Human (12, 9);
        Cat c2 = new Cat(12, 10);
        Robot r2 = new Robot(15, 10);
        Treadmill t2 = new Treadmill(15);
        Wall w2 = new Wall(11);

        // человек преодолевает препятствия

        if (h2.humanDistance()  < t2.distance()) {
            System.out.println("Человек не справился с беговой дорожкой");
        } else if ((h2.humanDistance() >= t2.distance() & (h2.humanJump() < w2.height()))) {
            System.out.println("Человек пробежал по беговой дорожке, но не перепрыгнул через стену");
        } else {
            System.out.println("Человек пробежал по беговой дорожке и перепрыгнул через стену");

        }

        // кот преодолевает препятствия

        if (c2.catDistance()  < t2.distance()) {
            System.out.println("Кот не справился с беговой дорожкой");
        } else if ((c2.catDistance() >= t2.distance() & (c2.catJump() < w2.height()))) {
            System.out.println("Кот пробежал по беговой дорожке, но не перепрыгнул через стену");
        } else {
            System.out.println("Кот пробежал по беговой дорожке и перепрыгнул через стену");

        }

        // робот преодолевает препятствия

        if (r2.robotDistance()  < t2.distance()) {
            System.out.println("Робот не справился с беговой дорожкой");
        } else if ((r2.robotDistance() >= t2.distance() & (r2.robotJump() < w2.height()))) {
            System.out.println("Робот пробежал по беговой дорожке, но не перепрыгнул через стену");
        } else {
            System.out.println("Робот пробежал по беговой дорожке и перепрыгнул через стену");

        }














       }




}
