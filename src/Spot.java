



import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
        double denom = 4.5;

        // System.out.println("position = " + pos);

        Color newColor = (Color) getFill();
        double red = 0 * (denom - 4) / denom; //(int) (newColor.getRed()/2);
        double green = 1 * (denom - 4) / denom; // (int) (newColor.getGreen()/2);
        double blue = 1 * (denom - 4) / denom; // (int) (newColor.getBlue()/2);

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

            //    System.out.println("rgb1 = " + red1.getRed() + ", " + green1.getGreen() + ", " + blue1.getBlue());


        }


        //System.out.println("rgb = " + red + ", " + green + ", " + blue);
        setFill(Color.rgb((int) (255 * red), (int) (255 * green), (int) (255 * blue)));

        //setFill(newColor);

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