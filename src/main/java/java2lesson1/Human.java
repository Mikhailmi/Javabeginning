package java2lesson1;

public class Human implements Run, Jump, Participants {



    private int humanDistance, humanJump;

    public Human(int humanDistance, int humanJump) {
        this.humanDistance = humanJump;
        this.humanDistance = humanJump;
    }

    public int humanDistance() {
        return humanDistance;
    }

    public int humanJump() {
        return humanJump;
    }




    @Override
    public void run() {
        System.out.println("Человек бежит");
    }

    @Override
        public void jump() {
            System.out.println("Человек прыгает");

    }

    @Override
    public void treadmillRun() {

        System.out.println("Человек успешно пробежал по беговой дорожке");

    }

    @Override
    public void wallJump() {
        System.out.println("Человек успешно перепрыгнул через стену");
    }



};

