package Assignment3;

import becker.robots.*;

import static dit948.Random.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Lawrence on 11/11/2015.
 */

public class UserRobot extends Robot implements Runnable{

    private int direction;
    private Double stdSpeed = 2.0;
    private Stack<Direction> actionList = new Stack<>();

    public UserRobot(City c, int st, int av, Direction d) {
        super(c, st, av, d);
    }

    public void addAction(Direction direction){
        actionList.push(direction);
    }

    public void executeMove (Direction direction){
        if (direction == this.getDirection())this.move();
        this.setSpeed(10);
        while (direction != this.getDirection())this.turnLeft();
        this.setSpeed(stdSpeed);
    }

    public void takeOnMe () {
        if (this.canPickThing())
            System.out.println("Execute Victory!");
    }
    @Override
    public void run() {
        while (true) {
            if (!actionList.empty())
                executeMove(actionList.pop());
        }
    }
}
