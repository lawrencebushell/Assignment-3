package Assignment3;

import becker.robots.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Frank on 2015-11-09.
 * Main class
 */

public class World {

    int size;       //Size of the frame
    int present;    //Instead of bombs

    Frame frame;    //"Reasonable" Frame
    PresentCity presentCity; //Instead of bombcity
    RobotUIComponents roboComps;    //Not sure what this is doing
    CityView view;



    public World(){ //Default constructor
        frame = new Frame("Paris");
        size = 11;
        present = 1;
    }

    public void addCity() {

        City.showFrame(false);

        presentCity = new PresentCity(size, present);

        roboComps = new RobotUIComponents(presentCity, 0, 0, size, size);

        view = roboComps.getCityView();

        frame.add(view);

        frame.pack();
    }

    protected JMenu createActionsMenu(){

        JMenuItem restart = new JMenuItem("Pause/Play");


        JMenu actions = new JMenu("Actions");
        actions.add(restart);
        return actions;
    }

    protected JMenu createSettingsMenu() {

        JRadioButtonMenuItem easy = new JRadioButtonMenuItem("Easy");
        JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Medium");
        medium.setSelected(true); // Medium by default
        JRadioButtonMenuItem hard = new JRadioButtonMenuItem("Hard");

        // put buttons in a group, so that we know only
        // one of them can be selected at a time
        ButtonGroup settingsGroup = new ButtonGroup();
        settingsGroup.add(easy);
        settingsGroup.add(medium);
        settingsGroup.add(hard);

        // add the items to the menu
        JMenu settings = new JMenu("Settings");
        settings.add(easy);
        settings.add(medium);
        settings.add(hard);
        return settings;
    }

    public void addMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu actions = createActionsMenu();
        menuBar.add(actions);

        JMenu settings = createSettingsMenu();

        menuBar.add(settings);

        frame.setJMenuBar(menuBar);

        frame.pack();

    }

    public static void main(String[] args) {

      World world = new World();
        world.addCity();
        world.addMenu();



    }

}
