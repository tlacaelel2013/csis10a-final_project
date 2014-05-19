
/**
 * A Wavelike is an actor that grows overtime and change its color according to a formula.
 * 
 * @author: Luis Acevedo-Arreguin. 
 * @version: May 19, 2014.
 */

import info.gridworld.actor.ActorWorld;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

//import info.gridworld.actor.Actor.Wave;

import java.awt.Color;

public class Wavelike extends Wave
{
    // instance variables
    private boolean source = true;

    /**
     * Constructor for objects of class Wave
     */
    
    
    /**
     * 
     * Causes the wave to grow and change its color according to a formula.
     */
    public void act()
    {
        grow();
        
        if (source == false) updateColor(getColor());
    }

}

