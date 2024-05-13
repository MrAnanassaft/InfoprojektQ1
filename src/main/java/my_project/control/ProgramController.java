package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.Config;
import my_project.model.CollisionDetector;
import my_project.model.Player;
import my_project.model.Shot;
import my_project.model.Variable_Container;

import javax.swing.*;

public class ProgramController {

    public static Player player;
    public static Player player1;
    private ViewController viewController;
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }
    public void startProgram() {

        player = new Player(100, 100, 100, 100, 200, viewController.getDrawFrame().getWidth() - 200 - 10, viewController,68, 65, 32, 69);
        player1 = new Player( 200, 200, 100, 100, 200, viewController.getDrawFrame().getWidth() - viewController.getDrawFrame().getWidth() + 10, viewController,39, 37, 155, 17);
        viewController.draw(player);
        viewController.register(player);
        viewController.draw(player1);
        viewController.register(player1);

    }
    public void updateProgram(double dt){
        for (int i = 0; i <= Variable_Container.shots.size() - 1; i++){
            Shot ball = Variable_Container.shots.get(i);
            if (CollisionDetector.circleWithRectangle(ball.getX(), ball.getY(), ball.getRadius(), player.getX(), player.getY(), player.getWidth(), player.getHeight())){

            }
        }
    }
}

    //Player player = new Player(100, 100, 100, 100, 200, 20, viewController,68, 65, 32);
    //Player player1 = new Player( 200, 200, 100, 100, 200, 1705, viewController,39, 37, 155);
