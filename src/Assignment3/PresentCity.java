package Assignment3;

import becker.robots.*;

import static dit948.Random.randomInt;

/**
 * Created by Frank on 2015-11-09.
 */
public class PresentCity extends City {

    int size;

    int present;

    public PresentCity(int size, int present) {
        super(size, size);

        this.size = size;
        this.present = present;

        distributePresent();

        putWalls();

    }

    void distributePresent() {

        showThingCounts(true);

        for (int i = 0; i < present; i++) {
            int st = 0;
            int av = 0;

            do {
                st = randomInt(size);
                av = randomInt(size);
            } while (st == 5 && av == 5);
            new Present(this, st, av);
        }
    }

    void putWalls() {

        for (int i = 0; i < size; i++) {
            new Wall(this, 0, i, Direction.NORTH);
            new Wall(this, size-1, i, Direction.SOUTH);
            new Wall(this, i, 0, Direction.WEST);
            new Wall(this, i, size-1, Direction.EAST);
        }

    }


    }



