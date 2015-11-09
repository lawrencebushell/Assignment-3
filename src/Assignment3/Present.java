package Assignment3;

import becker.robots.City;
import becker.robots.Thing;

import java.awt.*;

/**
 * Created by Frank on 2015-11-09.
 */
public class Present extends Thing {

    public Present(City city, int st, int av) {
        super(city, st, av);

        setCanBeCarried(true);

        setColor(Color.BLACK);

    }

}

