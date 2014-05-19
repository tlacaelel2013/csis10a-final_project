
/**
 * A Wave is an actor that grows overtime and changes its color. It works more like a
 * perturbation or disturbance of a medium.
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
        // source = true;
    }

    /**
     * 
     * Causes the wave to grow and change its color.
     */
    public void act()
    {
        grow();
        updateColor(getColor());
       
    }

    public void grow() {

        for (int i=0; i<9; i++) {
            setDirection(45*i);
            if (canGrow()) {
                Grid<Actor> gr = getGrid();

                Wave child = new Wave();
                child.putSelfInGrid(gr, getNextLocation());
                child.updateColor(getColor());
            }

        }

    }

    public boolean canGrow() {
        Location next = getNextLocation();
        return (isValid(next) && getNeighbor() == null);
    }

    public void updateColor(Color c) {
        Color[] neighborColor = new Color[8];

        for (int j=0; j<8; j++) {

            setDirection(45*j);

            if (getNeighbor() instanceof Wave) { 
                neighborColor[j] = getNeighbor().getColor();
            }
            else {
                neighborColor[j] = c;
            }
            setColor(neighborColor);    

        }

    }

    private void setColor(Color[] c) {
        double red = 0.0, green = 255.0, blue = 255.0;
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

}

