package Assignment3;

import becker.robots.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Frank on 2015-11-09.
 * Main class
 */

public class World {

    private int size;       //Size of the frame
    private int present;    //Instead of bombs

    private Menu menu;
    private Frame frame;    //"Reasonable" Frame
    private PresentCity presentCity; //Instead of bombcity
    private RobotUIComponents roboComps;    //Not sure what this is doing
    private CityView view;

    private JButton up = new JButton("up");
    private JButton left = new JButton("left");;
    private JButton down = new JButton("down");;
    private JButton right = new JButton("right");;
    private JButton pick = new JButton("pick");;



    public World(){ //Default constructor
        frame = new Frame("Paris");
        menu = new Menu();
        size = 11;
        present = 1;
    }

    private void addCity() {

        City.showFrame(false);

        presentCity = new PresentCity(size, present);

        roboComps = new RobotUIComponents(presentCity, 0, 0, size, size);

        view = roboComps.getCityView();

        frame.add(view);

        frame.pack();
    }

    private JMenu createActionsMenu(){

        JMenuItem restart = new JMenuItem("Pause/Play");

        JMenu actions = new JMenu("Actions");
        actions.add(restart);
        return actions;
    }

    private JMenu createSettingsMenu() {

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

    private void addMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu actions = createActionsMenu();
        menuBar.add(actions);

        JMenu settings = createSettingsMenu();

        menuBar.add(settings);

        frame.setJMenuBar(menuBar);

        frame.pack();
    }

    private void addController(){
        JPanel controller = new JPanel(new BorderLayout());

    }

    public static void main(String[] args) {

      World world = new World();
        world.addCity();
        world.addMenu();





    }

}
