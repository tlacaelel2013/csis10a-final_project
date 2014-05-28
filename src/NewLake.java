/**
 * Created by section one on 5/23/14.
 */
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
public class NewLake extends Application {

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
                    spotGrid[loc].setFill(Color.BLACK);
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

                if (timeIndex % 15 == 0) {
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

