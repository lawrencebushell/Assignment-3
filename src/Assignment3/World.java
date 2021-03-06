package Assignment3;

import becker.robots.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Frank on 2015-11-09.
 * Main class
 */

public class World implements Runnable{

    private int size;       //Size of the frame
    private int present;    //Instead of bombs

    private Menu menu;
    private Frame frame;    //"Reasonable" Frame
    private PresentCity presentCity; //Instead of bombcity
    private RobotUIComponents roboComps;
    private CityView view;

    private JPanel panel;
    private JPanel controller;
    private JButton up = new JButton("up");
    private JButton left = new JButton("left");
    private JButton down = new JButton("down");
    private JButton right = new JButton("right");
    private JButton pick = new JButton("pick");

    private UserRobot player;
    private AI enemy;
    private static Thread worldThread;
    private static Thread playerThread;
    private static Thread enemyThread;



    public World() { //Default constructor
        frame = new Frame("Paris");
        size = 11;
        present = 1;
        panel = new JPanel();
        controller = new JPanel();
    }

    public void startThreads(){
        worldThread.resume();
        playerThread.resume();
        enemyThread.resume();
    }

    public void stopThreads(){
        worldThread.suspend();
        playerThread.suspend();
        enemyThread.suspend();
    }

    private void addCity() {

        City.showFrame(false);

        presentCity = new PresentCity(size, present);

        //creating enemy thread
        enemy = new AI(presentCity, 5, 5, Direction.NORTH);
        enemyThread = new Thread(enemy, "AI thread");
        enemyThread.start();

        //creating player thread
        player = new UserRobot (presentCity, 3, 3, Direction.NORTH);
        playerThread = new Thread(player, "Player thread");
        playerThread.start();

        roboComps = new RobotUIComponents(presentCity, 0, 0, size, size);

        roboComps.getStartStopButton().doClick();
        view = roboComps.getCityView();

        panel.add(view);

        frame.add(panel, BorderLayout.CENTER);

        frame.pack();

    }

    private JMenu createActionsMenu() {

        JMenuItem restart = new JMenuItem("Pause/Play");

        JMenu actions = new JMenu("Actions");
        actions.add(restart); //action to restart


        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                roboComps.getStartStopButton().doClick();
            }
        });
        return actions;
    }
    private JMenu createSettingsMenu() {

        JRadioButtonMenuItem easy = new JRadioButtonMenuItem("Easy");
        JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Medium");
        medium.setSelected(true); // Medium by default
        JRadioButtonMenuItem hard = new JRadioButtonMenuItem("Hard");

        easy.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AI.stdSpeed = 2;
            }
        });

        medium.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AI.stdSpeed = 5;
            }
        });

        hard.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AI.stdSpeed = 10;
            }
        });

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

    //setting positions for controller buttons
    private void addControllers() {

        JPanel controller = new JPanel();
        controller.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 0;
        controller.add(up, c);

        c.gridy = 1;
        c.gridx = 0;
        controller.add(left, c);

        c.gridy = 1;
        c.gridx = 1;
        controller.add(pick, c);

        c.gridy = 1;
        c.gridx = 2;
        controller.add(right, c);

        c.gridx = 1;
        c.gridy = 3;
        controller.add(down, c);

        frame.add(controller, BorderLayout.SOUTH);
        frame.pack();

        up.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                player.addAction(Direction.NORTH);
                System.out.println("Player up");
                }
        });

        down.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                player.addAction(Direction.SOUTH);
                System.out.println("Player down");
            }
        });

        left.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                player.addAction(Direction.WEST);
                System.out.println("Player left");
            }
        });

        right.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                player.addAction(Direction.EAST);
                System.out.println("Player right");
            }
        });

        pick.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // player.pickThing();
                if (player.canPickThing()) {
                    stopThreads();
                    roboComps.getStartStopButton().doClick();
                    int choice = JOptionPane.showConfirmDialog(null,
                            "You Win! \n Do you want to restart the game?",
                            "victory!", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION){
                       panel.remove(view);
                        addCity();
                        roboComps.getStartStopButton();
                        startThreads();
                    }
                    else {
                        System.exit(0);
                    }
                }
            }
        });
    }

    @Override
    public void run() {
        boolean running = true;
        while (running){
            if (player.getIntersection() == enemy.getIntersection()){
                running = false;
                roboComps.getStartStopButton().doClick();
                int choice = JOptionPane.showConfirmDialog(null,
                        "You Lose! \n Do you want to restart the game?",
                        "Defeat!", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION){
                    running = true;
                    panel.remove(view);
                    addCity();
                    roboComps.getStartStopButton();
                }
                else {
                    System.exit(0);
                }

            }
        }
    }

    public static void main(String[] args) {

        World world = new World();
        world.addCity();
        world.addMenu();
        world.addControllers();

        worldThread = new Thread(world, "World thread");
        worldThread.start();
    }

}
