
/**
 * A Wave is an actor that grows overtime and cahnges its color.
 * 
 * @author: Luis Acevedo-Arreguin. 
 * @version: May 14, 2014.
 */

import info.gridworld.actor.ActorWorld;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class Wave extends Actor
{
    // instance variables
    private Color myColor = Color.YELLOW;

    /**
     * Constructor for objects of class Wave
     */
    public Wave()
    {
        // initialise instance variables
        setColor(myColor);
    }

    public Wave(Color newColor)
    {
        // initialise instance variables
        setColor(newColor);
    }

    /**
     * 
     * Causes the wave to grow and its color to change accordingly.
     */
    public void act()
    {
        grow();
    }

    private void grow() {
        //Wave[] child = new Wave[9];
        Color[] neighborColor = new Color[8];
        for (int i=0; i<9; i++) {
            if (canGrow()) {
                Grid<Actor> gr = getGrid();
                //child[i] = new Wave();
                //child[i].putSelfInGrid(gr, getNextLocation());
                Wave child = new Wave();
                child.putSelfInGrid(gr, getNextLocation());
                child.setDirection(getDirection());
                // Change color following method Act in class Flower:

                for (int j=0; j<8; j++) {
                    if (getNeighbor() != null) neighborColor[j] = getColor();
                    child.setColor(neighborColor);    

                    turn(45*j);

                }

            }
            turn(45*i);
        }

    }

    public boolean canGrow() {
        Location next = getNextLocation();
        return isValid(next);
    }

    /*
     * These methods are from class Termite and we need them to grow this wave.
     */

    /*
     * Turns the given number of degrees.
     */
    public void turn(int degrees) {
        setDirection(getDirection() + degrees);
    }

    private Location getNextLocation() {
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        return next;
    }

    /*
     * Gets the contents of the location the termite is facing.
     * Returns null if the location is empty or invalid.
     */
    private Actor getNeighbor() {
        Location next = getNextLocation();
        if (getGrid().isValid(next)) {
            return getGrid().get(next);
        } else {
            return null;
        }
    }

    /*
     * Checks whether a location is valid in the termite's grid.
     */
    private boolean isValid(Location loc) {
        return getGrid().isValid(loc);
    }

    private void setColor(Color[] c) {
        double red = 255.0, green = 255.0, blue = 255.0;
        if (c[0] != null) {
            red = c[0].getRed()/8.0;
            green = c[0].getGreen()/8.0;
            blue = c[0].getBlue()/8.0;
        }
        for (int i=1; i<8; i++) {
            if (c[i] != null) {
                red = red + c[i].getRed()/8.0;
                green = green + c[i].getGreen()/8.0;
                blue = blue + c[i].getBlue()/8.0;
            }
        }
        setColor(new Color((int) red, (int) green, (int) blue));

    }

}

