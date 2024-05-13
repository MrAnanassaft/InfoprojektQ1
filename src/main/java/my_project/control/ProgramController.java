package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.Config;
import my_project.model.*;

import javax.swing.*;

public class ProgramController {

    public static Player player;
    public static Player player1;
    double timer;
    public static ViewController viewController;
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }
    public void startProgram() {
        viewController.showScene(0);
        viewController.createScene();
        viewController.createScene();
        Background backgroundS1 = new Background(0, viewController);
        viewController.draw(backgroundS1,0);
        StartButton startButton = new StartButton(this);
        viewController.draw(startButton,0);


    }
    public void updateProgram(double dt){
        if(ViewController.isKeyDown(74)){ // J
            viewController.showScene(0);
        }
        if(ViewController.isKeyDown(75)){ // K
            viewController.showScene(1);
        }
        if(ViewController.isKeyDown(76)){ // K
            viewController.showScene(2);
        }
        timer += dt;
        for (int i = 0; i <= Variable_Container.shots.size() - 1; i++){
            Shot ball = Variable_Container.shots.get(i);
            if (CollisionDetector.circleWithRectangle(ball.getX(), ball.getY(), ball.getRadius(), player.getX(), player.getY(), player.getWidth(), player.getHeight())){

            }
        }
    }

    public void startNewGame(){
        viewController.createScene();
        viewController.createScene();
        viewController.createScene();
        viewController.showScene(0);
    }
    public void startGame(){
        Player player = new Player(100, 100, 100, 100, 200, viewController.getDrawFrame().getWidth() - 200 - 10, viewController,68, 65, 32, 69);
        Player player1 = new Player( 200, 200, 100, 100, 200, viewController.getDrawFrame().getWidth() - viewController.getDrawFrame().getWidth() + 10, viewController,39, 37, 155, 17);
        viewController.draw(player,1);
        viewController.register(player);
        viewController.draw(player1,1);
        viewController.register(player1);
        viewController.showScene(1);
    }
}

    //Player player = new Player(100, 100, 100, 100, 200, 20, viewController,68, 65, 32);
    //Player player1 = new Player( 200, 200, 100, 100, 200, 1705, viewController,39, 37, 155);
