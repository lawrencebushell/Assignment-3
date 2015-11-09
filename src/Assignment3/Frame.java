package Assignment3;

import javax.swing.*;

/**
 * Created by Frank on 2015-11-09.
 */
public class Frame extends JFrame {

    //Default constructor
    public Frame(String title) {
        super(title);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Method for adding panel
    private void addPanel(){
        JPanel contents = new JPanel();
        JButton actions = new JButton("actions");
        JButton settings = new JButton("settings");
        contents.add(actions);
        contents.add(settings);
    }

    public static void main(String[] args) {
        Frame frame = new Frame("I am reasonable!");
        frame.setSize(550, 500);
        frame.addPanel();
    }
}


