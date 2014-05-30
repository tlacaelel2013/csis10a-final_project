import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    public static Pane canvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(getClass().getSimpleName());

        canvas = new Pane();
        final Scene scene = new Scene(canvas, 635, 635);

        primaryStage.setTitle("Lake");
        primaryStage.setScene(scene);
        primaryStage.show();


        // -- 1. Add a text node that we will move around
        final Text text = new Text(".");
        text.setLayoutX(100);
        text.setLayoutY(100);
        canvas.getChildren().add(text);

        final int spectrumDim = 8192;

        final Color[] colormap = new Color[spectrumDim*256];
        for (int m = 0; m < colormap.length; m++) {
            colormap[m] = Color.rgb((spectrumDim*256-m-1)/spectrumDim,0,m/spectrumDim); // rainbow colormap
        }

        final int row = 50;
        final int col = 50;
        final int dim = 12;
        final int pad = 0;
        final int deltaX = 10;
        final int deltaY = 10;
        final double rad = 8.15;

        final Spot[] spotGrid = new Spot[row * col];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int pos = (i - 1) * col + j - 1;
                spotGrid[pos] = new Spot();
                spotGrid[pos].setRadius(rad);
                spotGrid[pos].setCenterX(dim * j + pad + deltaX);
                spotGrid[pos].setCenterY(dim * i + pad + deltaY);

                spotGrid[pos].setFill(Color.BLUE);
                spotGrid[pos].setPos(pos);
                canvas.getChildren().add(spotGrid[pos]);

                //System.out.println((pos) + ") coordinates: " + spotGrid[pos].getCenterX() +
                //        ", " + spotGrid[pos].getCenterY());

                //System.out.println("radius = " + spotGrid[pos].getRadius());


            }
        }

        // canvas.getChildren().addAll(spotGrid);


        int initPos = 575;
        int pos;
        final int flag[] = new int[row * col];


        for (int p = 0; p < 2; p++) {
            for (int q = 0; q < 2; q++) {
                pos = initPos + p + col*q;
                spotGrid[pos].setRadius(rad*1/4);
                spotGrid[pos].setFill(Color.WHITE);
                System.out.println("coordinates: " + spotGrid[pos].getCenterX() + ", " + spotGrid[pos].getCenterY());
                System.out.println("status = " + spotGrid[pos].getStatus());
                System.out.println("radius = " + spotGrid[pos].getRadius());


                //spotGrid[pos].activatingSpot(spotGrid, row, col);
                spotGrid[pos].setStatus(2);
                flag[pos] = spotGrid[pos].getStatus();

            }
        }





        System.out.println("Next Step");



        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            //scene.getOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {


                int i = (int) ((event.getY() - pad - deltaX) / dim);
                int j = (int) ((event.getX() - pad - deltaY) / dim);
                int loc = ((i - 1) * col + j - 1);
                System.out.println("i = " + i + ", j = " + j);
                System.out.println("loc = " + loc);
                if (loc >= 0 && loc < row * col) {

                    spotGrid[loc].setStatus(2);
                    flag[loc] = 2;
                    spotGrid[loc].setFill(Color.WHITE);
                    spotGrid[loc].setRadius(0);
                    spotGrid[loc].setCenterX((event.getY() - pad - deltaX) / dim);
                    spotGrid[loc].setCenterY((event.getX() - pad - deltaY) / dim);

                    System.out.println("status = " + spotGrid[loc].getStatus());
                    System.out.println("color = " + spotGrid[loc].getFill());
                    System.out.println("coordinates: " + spotGrid[loc].getCenterX() +
                            ", " + spotGrid[loc].getCenterY());

                    //spotGrid[loc].activatingSpot(spotGrid, row, col);

                }


            }
        });



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






        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
        //final Timeline loop = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {


            //boolean running = true;
            int timeIndex = 0;

            @Override
            public void handle(final ActionEvent t) {

                //while(running) {

                for (int k = 0; k < row * col; k++) {

                    if (flag[k] > 0) {

                        if (flag[k] == 1) spotGrid[k].activatingSpot(spotGrid, row, col,
                                spectrumDim, colormap);
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





                if (timeIndex % 25 == 0) {
                    //running = false;
                    for (int k = 0; k < row * col; k++) {

                        if (flag[k] == 2 && spotGrid[k].getFill().equals(Color.YELLOW)) {
                            spotGrid[k].setFill(Color.WHITE);
                        } else if (flag[k] == 2 && !spotGrid[k].getFill().equals(Color.YELLOW)) {
                            spotGrid[k].setFill(Color.YELLOW);
                        }
                    }
                }


            }
        }));


        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();


    }


}

