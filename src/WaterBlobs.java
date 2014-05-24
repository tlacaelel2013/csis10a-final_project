import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Luis Arreguin on 5/22/14 (by modifying Brian's example code).
 */
public class WaterBlobs extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(getClass().getSimpleName());

        final Group root = new Group();
        final Scene scene = new Scene(root, 400, 400, Color.ALICEBLUE);


        primaryStage.setScene(scene);
        primaryStage.show();


        // -- 1. Add a text node that we will move around
        final Text text = new Text(".");
        text.setLayoutX(100);
        text.setLayoutY(100);
        root.getChildren().add(text);

        final int row = 30;
        final int col = 30;
        final int dim = 5;
        final int pad = 100;
        final int deltaX = 20;
        final int deltaY = 20;

        final Spot[] spotGrid = new Spot[row * col];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int pos = (i - 1) * col + j - 1;
                spotGrid[pos] = new Spot();
                spotGrid[pos].setCenterX(dim * j + pad + deltaX);
                spotGrid[pos].setCenterY(dim * i + pad + deltaY);
                spotGrid[pos].setRadius(2);
                spotGrid[pos].setFill(Color.FUCHSIA);
                spotGrid[pos].setPos(pos);
                //root.getChildren().add(spotGrid[pos]);

                //System.out.println((pos) + ") coordinates: " + spotGrid[pos].getCenterX() +
                //        ", " + spotGrid[pos].getCenterY());

                //System.out.println("radius = " + spotGrid[pos].getRadius());


            }
        }

        root.getChildren().addAll(spotGrid);


        int pos = 500;
        final int flag[] = new int[row * col];

        spotGrid[pos].setFill(Color.BLACK);
        System.out.println("coordinates: " + spotGrid[pos].getCenterX() + ", " + spotGrid[pos].getCenterY());
        System.out.println("status = " + spotGrid[pos].getStatus());
        System.out.println("radius = " + spotGrid[pos].getRadius());


        //spotGrid[pos].activatingSpot(spotGrid, row, col);
        spotGrid[pos].setStatus(2);
        flag[pos] = spotGrid[pos].getStatus();


        /**
         if (pos-row>=0) {
         spotGrid[pos-row].activatingSpot(spotGrid, row, col);
         flag[pos-row] = spotGrid[pos-row].getStatus();
         }
         if ((pos+1)/col==pos/col) {
         spotGrid[pos+1].activatingSpot(spotGrid, row, col);
         flag[pos+1] = spotGrid[pos+1].getStatus();
         }
         if (pos+row<row*col) {
         spotGrid[pos+row].activatingSpot(spotGrid, row, col);
         flag[pos+row] = spotGrid[pos+row].getStatus();
         }
         if ((((pos - 1) / col) == (pos / col)) && (pos > 0)) {
         spotGrid[pos-1].activatingSpot(spotGrid, row, col);
         flag[pos-1] = spotGrid[pos-1].getStatus();
         }
         */
        System.out.println("Next Step");


        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            //scene.getOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {


                int i = (int) ((event.getY() - pad - deltaX) / dim);
                int j = (int) ((event.getX() - pad - deltaY) / dim);
                int loc = ((i - 1) * col + j - 1);
                System.out.println("i = " + i + ", j = " + j);
                System.out.println("loc = " + loc);
                /**
                 spotGrid[loc].setFill(Color.RED);
                 spotGrid[loc].setStatus(2);

                 spotGrid[loc].activatingSpot(spotGrid, row, col);
                 */

                //root.getChildren().add(spotGrid[loc]);
                //root.getEffect();


                if (loc >= 0 && loc < row * col) {

                    spotGrid[loc].setStatus(2);
                    spotGrid[loc].setFill(Color.GREENYELLOW);
                    System.out.println("status = " + spotGrid[loc].getStatus());
                    System.out.println("color = " + spotGrid[loc].getFill());
                    System.out.println("coordinates: " + spotGrid[loc].getCenterX() +
                            ", " + spotGrid[loc].getCenterY());

                    spotGrid[loc].activatingSpot(spotGrid, row, col);

                }


            }
        });


        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {


            //boolean running = true;
            int timeIndex = 0;

            @Override
            public void handle(final ActionEvent t) {

                //while(running) {

                for (int k = 0; k < row * col; k++) {

                    if (flag[k] > 0) {

                        if (flag[k] == 1) spotGrid[k].activatingSpot(spotGrid, row, col);
                        int act = 1;


                        if (k - row >= 0 && spotGrid[k - row].getStatus() < 2) {
                            spotGrid[k - row].setStatus(act);
                            flag[k - row] = act;
                        }
                        if ((k + 1) / col == k / col && spotGrid[k + 1].getStatus() < 2) {
                            spotGrid[k + 1].setStatus(act);
                            flag[k + 1] = act;
                        }
                        if (k + row < row * col && spotGrid[k + row].getStatus() < 2) {
                            spotGrid[k + row].setStatus(act);
                            flag[k + row] = act;
                        }
                        if ((((k - 1) / col == (k / col)) && (k > 0)) && spotGrid[k - 1].getStatus() < 2) {
                            spotGrid[k - 1].setStatus(act);
                            flag[k - 1] = act;
                        }


                    }

                }
                timeIndex++;
                System.out.println("time = " + timeIndex);

                if (timeIndex % 5 == 0) {
                    //running = false;
                    for (int k = 0; k < row * col; k++) {

                        if (flag[k] == 2 && spotGrid[k].getFill().equals(Color.BLACK)) {
                            spotGrid[k].setFill(Color.YELLOW);
                        } else if (flag[k] == 2 && !spotGrid[k].getFill().equals(Color.BLACK)) {
                            spotGrid[k].setFill(Color.BLACK);
                        }
                    }
                }

                scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        //event.getX -> relative to node origin
                        //event.getSceneX -> relative to scene origin
                        //event.getScreenX -> relative to screen origin
                        text.setLayoutX(event.getX());
                        text.setLayoutY(event.getY());
                        //text.setText(".");


                        int i = (int) ((event.getY() - pad - deltaX) / dim);
                        int j = (int) ((event.getX() - pad - deltaY) / dim);
                        text.setText("." + ((i - 1) * col + j - 1));

                    }
                });


            }
        }));


        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();


    }


}


class Spot extends Circle {

    private int radius = 10;
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