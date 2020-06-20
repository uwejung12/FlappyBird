package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Animation of Earth rotating around the sun. (Hello, world!)
public class Main extends Application {

    int canvas_widht = 1500;
    int canvas_height = 800;
    boolean gameover = false;
    boolean restart = false;
    boolean start = false;
    int counter = 0;


    public static void main(String[] args)
    {
        launch(args);
    }

    static HashSet<String> currentlyActiveKeys;

    private static void prepareActionHandlers(Scene theScene)
    {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new HashSet<String>();
        theScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.add(event.getCode().toString());
            }
        });
        theScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.remove(event.getCode().toString());
            }
        });
    }



    @Override
    public void start(Stage theStage)
    {

        Flappy flappy = new Flappy(100 ,475 , 35, 50, 6, 10, 0.1);

        List<Piepe> piepeList = new ArrayList<Piepe>();

        // Initialization of the pipes
        for (int i = 1; i < 5 ; i++){
            piepeList.add(new Piepe(200 + i * 300));
        }

        //piepeList = piepesinitialize(piepeList);

        theStage.setTitle( "AnimationTimer Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( canvas_widht, canvas_height );
        root.getChildren().add( canvas );

        prepareActionHandlers(theScene);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        File file = new File("src/Flappy.png");
        Image imgFlappy = new Image(file.toURI().toString());
        File file1 = new File("src/piepetop.png");
        Image imgpiepetop= new Image(file1.toURI().toString());
        File file2 = new File("src/piepetoptop.png");
        Image imgpiepetoptop= new Image(file2.toURI().toString());
        File file3 = new File("src/piepebottom.png");
        Image imgpiepebottom= new Image(file3.toURI().toString());
        File file4 = new File("src/piepebottomtop.png");
        Image imgpiepebottomtop= new Image(file4.toURI().toString());
        File file5 = new File("src/bottom.png");
        Image imgpbottom= new Image(file5.toURI().toString());

        Button button = new Button("Restart ?");
        button.setLayoutX(canvas_widht/2 - 30);
        button.setLayoutY(canvas_height/2 + 100);
        root.getChildren().addAll(button);
        button.setOnAction(click  ->  {
                    System.out.println("clicked");
                    restart = true;
                }
        );
        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {

                double t = (currentNanoTime - startNanoTime) / 10000000;

                if (currentlyActiveKeys.contains("UP")) {
                    start = true;
                    flappy.setTime_last(t);
                }

                // restart

                if (restart){

                    flappy.reset(475, t);
                    start = false;
                    counter = 0;
                    gameover = false;
                    restart = false;
                    piepeList.clear();
                    for (int i = 1; i < 5 ; i++){
                        piepeList.add(new Piepe(200 + i * 300));
                    }
                }


                if (!gameover) {

                    button.setVisible(false);

                    // time in 100 hundredth


                    // Clear the canvas
                    gc.clearRect(0, 0, canvas_widht, canvas_height);

                    // Draw Flappy

                    gc.drawImage(imgFlappy, flappy.getX(), flappy.getY());

                    // Draw Bottom

                    gc.drawImage(imgpbottom,0,750);

                    int i;

                    // Draw pipes

                    for (int j = 0; j < piepeList.size(); j++) {

                       for (i = 0; i < piepeList.get(j).gettop(); i++) {
                           gc.drawImage(imgpiepetop, piepeList.get(j).getX(), i * 20);
                       }

                       gc.drawImage(imgpiepetoptop, piepeList.get(j).getX() - piepeList.get(j).getXtop(), i * 20);

                       for (i = 0; i < piepeList.get(j).getbottom(); i++) {
                           gc.drawImage(imgpiepebottom, piepeList.get(j).getX(), 730 - i * 20);
                       }

                       gc.drawImage(imgpiepebottomtop, piepeList.get(j).getX() - piepeList.get(j).getXtop(), 730 - i * 20 - 4);
                    }

                    // Draw position of flappy

                    gc.setFont(Font.font ("Verdana", 20));

                    String s = String.valueOf(flappy.getX());
                    //gc.fillText(s, 65, 50);

                    gc.fillText("y: ", 50, 60);
                    s = String.valueOf(flappy.getY());
                    gc.fillText(s, 80, 60);

                    gc.fillText("t: ", 50, 80);
                    s = String.valueOf(t);
                    gc.fillText(s, 80, 80);

                    s = String.valueOf(counter);

                    gc.fillText("Counter: ", 50, 40);
                    gc.fillText(s, 150, 40);
                    // check for new pipe

                    // draw timer

                    if(piepeList.get(piepeList.size() - 1 ).getX() == 1200){
                        piepeList.add(new Piepe( 1500));
                    }

                    // check for deleting piepes
                    if (piepeList.get(0).getX() == -100){
                        piepeList.remove(0);
                    }

                    // counter


                    if (flappy.getY() < canvas_height - flappy.getHeight() - 50 )  {

                        //flappy.fall();

                        for (int j = 0; j < piepeList.size(); j++){
                            for (int k = 0 ; k < flappy.getrand_number(); k++)
                            if (piepeList.get(j).Checkcollision(flappy.getX() + flappy.getrand_x(k), flappy.getY () + flappy.getrand_y(k))
                            ){
                                gameover = true;
                                File file = new File("src/gameover.png");
                                Image imggameover = new Image(file.toURI().toString());

                                gc.drawImage(imggameover, canvas_widht/2 - 250, canvas_height/2 - 83);

                                button.setVisible(true);
                            }

                            if (piepeList.get(j).getX() == 100){
                                counter++;
                            }
                            piepeList.get(j).move();
                        }

                        if (start){
                            flappy.fall(t);
                        }

                    }
                    // Gameover
                    else{
                        gameover = true;

                        File file = new File("src/gameover.png");
                        Image imggameover = new Image(file.toURI().toString());

                        gc.drawImage(imggameover, canvas_widht/2 - 250, canvas_height/2 - 83);

                        button.setVisible(true);

                    }


                    if (currentlyActiveKeys.contains("UP")) {
                        if (flappy.getY() > 0) {
                            flappy.presskey();
                        }
                    }
                }
            }
        }.start();

        theStage.show();
    }
}