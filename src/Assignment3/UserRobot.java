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

    private int direction; //current direction
    private Double stdSpeed = 2.0; //default speed
    private Stack<Direction> actionList = new Stack<>();

    //constructor
    public UserRobot(City c, int st, int av, Direction d) {
        super(c, st, av, d);
        this.setColor(Color.blue);
    }

    //remember issued action
    public void addAction(Direction direction){
        actionList.push(direction);
    }

    public void executeMove (Direction direction){
        if (direction == this.getDirection() && this.frontIsClear())this.move();
        this.setSpeed(10);
        while (direction != this.getDirection())this.turnLeft();
        this.setSpeed(stdSpeed);
    }

    //loop for processing events
    @Override
    public void run() {
        while (true) {
            if (!actionList.empty())
                executeMove(actionList.pop());
        }
    }
}
