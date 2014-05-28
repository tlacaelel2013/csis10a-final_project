



import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static java.lang.Math.log;

/**
 * Created by section one on 5/23/14.
 */

class Spot extends Circle {

    private int radius = 20;
    private boolean[] neighbor = new boolean[4];
    private int[] neighborX = new int[4];
    private int[] neighborY = new int[4];
    private int status;
    private int spotLocX;
    private int spotLocY;
    private int spotPos;


    public Spot() {
        setRadius(radius);
        setFill(Color.LIGHTSKYBLUE);
        spotLocX = 0;
        spotLocY = 0;
        spotPos = 0;
        this.setCenterX(spotLocX);
        this.setCenterY(spotLocY);
        this.status = 0;
    }

    public Spot(int newRadius, Color newColor, int x, int y, int status) {
        setRadius(newRadius);
        setFill(newColor);
        spotLocX = x;
        spotLocY = y;
        this.setCenterX(spotLocX);
        this.setCenterY(spotLocY);
        this.status = 0;
    }

    public void setStatus(int onOff) {
        status = onOff;
    }

    public int getStatus() {
        return status;
    }

    public void setPos(int m) {
        spotPos = m;
    }

    public int getPos() {
        return spotPos;
    }

    public int getPos(double x, double y, int dim) {
        int locX = (int) x / dim;
        int locY = (int) y / dim;
        int pos = locX * locY - 1;
        return pos;
    }


    public void activatingSpot(Spot[] spotGrid, int row, int col) {

        setStatus(1);
        int pos = getPos();
        double denom = 6;

        // System.out.println("position = " + pos);

        Color newColor = (Color) getFill();
        double red = newColor.getRed() * (denom - 4) / denom; //(int) (newColor.getRed()/2);
        double green = newColor.getGreen() * (denom - 4) / denom; // (int) (newColor.getGreen()/2);
        double blue = newColor.getBlue() * (denom - 4) / denom; // (int) (newColor.getBlue()/2);

        if (pos - row >= 0) {
            Color red1 = (Color) (spotGrid[pos - row].getFill());
            red += (red1.getRed() / denom);
            Color green1 = (Color) spotGrid[pos - row].getFill();
            green += (green1.getGreen() / denom);
            Color blue1 = (Color) spotGrid[pos - row].getFill();
            blue += (blue1.getBlue() / denom);


        }
        if ((pos + 1) / col == pos / col) {
            Color red1 = (Color) (spotGrid[pos + 1].getFill());
            red += (red1.getRed() / denom);
            Color green1 = (Color) spotGrid[pos + 1].getFill();
            green += (green1.getGreen() / denom);
            Color blue1 = (Color) spotGrid[pos + 1].getFill();
            blue += (blue1.getBlue() / denom);


        }
        if (pos + row < row * col) {
            Color red1 = (Color) (spotGrid[pos + row].getFill());
            red += (red1.getRed() / denom);
            Color green1 = (Color) spotGrid[pos + row].getFill();
            green += (green1.getGreen() / denom);
            Color blue1 = (Color) spotGrid[pos + row].getFill();
            blue += (blue1.getBlue() / denom);


        }
        if ((((pos - 1) / col) == (pos / col)) && (pos > 0)) {
            Color red1 = (Color) (spotGrid[pos - 1].getFill());
            red += (red1.getRed() / denom);
            Color green1 = (Color) spotGrid[pos - 1].getFill();
            green += (green1.getGreen() / denom);
            Color blue1 = (Color) spotGrid[pos - 1].getFill();
            blue += (blue1.getBlue() / denom);

            //System.out.println("rgb1 = " + red1.getRed() + ", " + green1.getGreen() + ", " + blue1.getBlue());


        }


        //System.out.println("rgb = " + red + ", " + green + ", " + blue);
        setFill(Color.rgb((int) (255 * red), (int) (255 * green), (int) (255 * blue)));

        //setFill(newColor);
        //Color[] colormap = new Color[256];
        //for (int m = 0; m < colormap.length; m++) {
        //    colormap[m] = Color.rgb(255-m,0,m);
        //}


        Color[] colormap = new Color[3*256];
        for (int m = 0; m < colormap.length; m++) {
            colormap[m] = Color.rgb((767-m)/3,255-m%128,m/3);
            //colormap[m] = Color.rgb((767-m)/3,m%256,m/3);
        }
        /**
        Color[] colormap = new Color[3*256];
        for (int m = 0; m < colormap.length/3; m++) {
            colormap[m] = Color.rgb(0,m,m);
        }
        //Color[] colormap = new Color[256];
        for (int m = colormap.length/3; m < colormap.length*2/3; m++) {
            //colormap[m] = Color.rgb(255-m+256,255-m+256,m-256);
            colormap[m] = Color.rgb(m-256,511-m,m-256);
        }
        //Color[] colormap = new Color[256];
        for (int m = colormap.length*2/3; m < colormap.length; m++) {
            //colormap[m] = Color.rgb(0,255-m+512,m-512);
            colormap[m] = Color.rgb(767-m,0,m-512);
        }
         */

        setFill(colormap[(int) (3*255*(0*red + 0*green + 1*blue))]);

        //setFill(colormap[(int) (255*(0.299*red + 0.587*green + 0.114*blue))]);
        //setFill(colormap[(int) (255*(0*red + 0*green + 1*blue))]);

        //setFill(colormap[(int) (255*log(255*(0.299*red + 0.587*green + 0.114*blue))/log(256))]);
        //setFill(colormap[(int) (255*(0.333*red + 0.333*green + 0.333*blue))]);

      /*//
        if (pos-row>=0) spotGrid[pos-row].setFill(Color.rgb((int) red, (int) green, (int) blue));
        if ((pos+1)/col==pos/col) spotGrid[pos+1].setFill(Color.rgb((int) red, (int) green, (int) blue));
        if (pos+row<row*col) spotGrid[pos+row].setFill(Color.rgb((int) red, (int) green, (int) blue));
        if ((((pos - 1) / col) == (pos / col)) && (pos > 0))
            spotGrid[pos-1].setFill(Color.rgb((int) red, (int) green, (int) blue));
        /**
        if (pos-row>=0) spotGrid[pos-row].activatingSpot(spotGrid, row, col);
        if ((pos+1)/col==pos/col) spotGrid[pos+1].activatingSpot(spotGrid, row, col);
        if (pos+row<row*col) spotGrid[pos+row].activatingSpot(spotGrid, row, col);
        if ((((pos - 1) / col) == (pos / col)) && (pos > 0)) spotGrid[pos-1].activatingSpot(spotGrid, row, col);
        */

    }


    public int[] getNeighborX() {
        for (int n = 0; n < 4; n++) {
            neighborX[n] = (n % 2) + (int) getCenterX() - 2 * (n % 2) * (n / 2);
            // neighborY[n] = -((n+1)%2) + getCenterY() + 2*((n+1)%2)*((n+1)/2);
        }
        return neighborX;
    }

    public int[] getNeighborY() {
        for (int n = 0; n < 4; n++) {
            // neighborX[n] = (n%2) + getCenterX() - 2*(n%2)*(n/2);
            neighborY[n] = -((n + 1) % 2) + (int) getCenterY() + 2 * ((n + 1) % 2) * ((n + 1) / 2);
        }
        return neighborY;
    }

    public boolean[] getNeighbor() {
        for (int n = 0; n < 4; n++) {
            neighbor[n] = false;
            //Point nextLoc = new Point(neighborX[n], neighborY[n]);

            //if (nextLoc.getClass().isInstance(this)){

            //neighbor[n] = true;
            //}
            //System.out.println("next = " + nextLoc.getClass());
        }

        return neighbor;
    }


    public void setNeighborsOFF() {
        for (int n = 0; n < 4; n++) {
            neighbor[n] = false;
            //Point nextLoc = new Point(neighborX[n], neighborY[n]);

            //if (nextLoc.getClass().isInstance(this)){

            //neighbor[n] = true;
            //}
            //System.out.println("next = " + nextLoc.getClass());
        }

        //return neighbor;
    }

    public void setNeighborsON() {
        for (int n = 0; n < 4; n++) {
            neighbor[n] = true;
            //Point nextLoc = new Point(neighborX[n], neighborY[n]);

            //if (nextLoc.getClass().isInstance(this)){

            //neighbor[n] = true;
            //}
            //System.out.println("next = " + nextLoc.getClass());
        }

        //return neighbor;
    }
}