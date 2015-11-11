package Assignment3;

import becker.robots.*;

import static dit948.Random.*;

import java.awt.Color;
/**
 * Created by Lawrence on 11/11/2015.
 */

public class UserRobot extends Robot implements Runnable{

    private int direction;

    public UserRobot(City c, int st, int av, Direction d) {
        super(c, st, av, d);
    }

    public void executeMove (Direction direction){
        if (direction == this.getDirection())this.move();

        while (direction != this.getDirection())this.turnLeft();
    }

    public void takeOnMe () {
        if (this.canPickThing())
            System.out.println("Execute Victory!");
    }
    @Override
    public void run() {

    }
}
