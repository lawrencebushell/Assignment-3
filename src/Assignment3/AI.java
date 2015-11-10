package Assignment3;

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;

import java.awt.*;

import static dit948.Random.randomInt;

/**
 * Created by Haxxflaxx on 2015-11-10.
 */
public class AI extends Robot implements Runnable {

    private static double stdSpeed = 10;

    public AI(City city, int i, int i1, Direction direction) {
        super(city, i, i1, direction);
        this.setColor(Color.red);
    }

    public void randomMove() {
        setSpeed(stdSpeed);
        int nrTurns = randomInt(4);
        System.out.println(getSpeed());
        double speed = getSpeed();
        if (nrTurns > 0)
            setSpeed(nrTurns*speed);
        for (int i = 0; i < nrTurns; i++) {
            turnLeft();
            System.out.println("Turn left");
        }
        System.out.println("Turn done");
        setSpeed(speed);

        move();
    }

    public void run() {
        while (true){
            randomMove();

        }
    }
}
