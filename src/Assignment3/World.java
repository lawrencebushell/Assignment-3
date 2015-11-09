package Assignment3;

import becker.robots.*;

/**
 * Created by Frank on 2015-11-09.
 * Main class
 */

public class World {

    Frame frame;    //"Reasonable" Frame
    int size;       //Size of the frame
    int present;    //Instead of bombs
    PresentCity presentCity; //Instead of bombcity
    RobotUIComponents roboComps;    //Not sure what this is doing

    public World(){ //Default constructor
        frame = new Frame("Paris");
        size = 11;
        present = 1;
    }


    public void addCity() {

        City.showFrame(false);

        presentCity = new PresentCity(size, present);

        roboComps = new RobotUIComponents(presentCity, 0, 0, size, size);

        CityView view = roboComps.getCityView();

        frame.add(view);

        frame.pack();

    }

    public static void main(String[] args) {

      World world = new World();
        world.addCity();



    }

}
