
/**
 * This class runs a world that contains waves.
 * 
 * @author: Luis Acevedo-Arreguin.
 * @version: May 14, 2014.
 */

import info.gridworld.gui.GUIController;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;
public class WaveRunner
{
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();

        Wave numberOne = new Wave();
        numberOne.setColor(Color.green);
        world.add(numberOne);

        Wavelike numberTwo = new Wavelike();
        numberTwo.setColor(Color.blue);
        world.add(numberTwo);

        Wavelike numberThree = new Wavelike();
        numberThree.setColor(Color.red);
        world.add(numberThree);

        // wall settings
        int x0 = 0;
        int y0 = 0;
        int dx = 1;
        int dy = 0;
        Color wallColor = Color.white;

        BoundarySpot[] wall = new BoundarySpot[6];
        for (int i = 0; i<wall.length; i++) {
            wall[i] = new BoundarySpot();
            wall[i].setColor(wallColor);
            
            world.add(wall[i]);
            wall[i]. moveTo(new Location(x0+i*dx, y0+i*dy));
            
        }

        world.show();
    }
}
