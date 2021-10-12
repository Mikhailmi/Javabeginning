package java2lesson1;

public class Robot implements Run, Jump, Participants {

    private int robotDistance, robotJump;

    public Robot(int robotDistance, int robotJump) {
        this.robotDistance = robotDistance;
        this.robotJump = robotJump;
    }

    @Override
    public void run() {
        System.out.println("Робот бежит");
    }

    @Override
    public void jump() {
        System.out.println("Робот прыгает");

    }

    @Override
    public void treadmillRun() {
        System.out.println("Робот успешно пробежал по беговой дорожке");

    }

    @Override
    public void wallJump() {
        System.out.println("Робот успешно перепрыгнул через стену");

    }


    public int robotDistance() {
        return robotDistance;
    }

    public int robotJump() {
        return robotJump;
    }


};