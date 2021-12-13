package java2lesson1;

public class Cat implements Run, Jump, Participants {

    @Override
    public void run() {
        System.out.println("Кот бежит");
    }

    @Override
    public void jump() {
        System.out.println("Кот прыгает");

    }

   @Override
    public void treadmillRun() {
        System.out.println("Кот успешно пробежал по беговой дорожке");
    }

    @Override
    public void wallJump() {
        System.out.println("Кот успешно перепрыгнул через стену");
    }

      private int catDistance, catJump;

        public Cat(int catDistance, int catJump) {
            this.catDistance = catDistance;
            this.catJump = catJump;
        }

    public int catDistance() {
        return catDistance;
    }

    public int catJump() {
        return catJump;
    }

};

