
/**
 * This class runs a world that contains waves.
 * 
 * @author: Luis Acevedo-Arreguin.
 * @version: May 14, 2014.
 */

import info.gridworld.actor.ActorWorld;


import java.awt.Color;

public class WaveRunner
{
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        
        
        Wave numberOne = new Wave();
        numberOne.setColor(Color.green);
        world.add(numberOne);
        
        Wave numberTwo = new Wave();
        numberTwo.setColor(Color.blue);
        world.add(numberTwo);
        
        world.show();
    }
}
