package Assignment3;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Frank on 2015-11-09.
 */
public class Frame extends JFrame {

    //Default constructor
    public Frame(String title) {
        super(title); //invoking constructor of jframe class
        setVisible(true); //shows window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes window when x button is pressed
    }

}


