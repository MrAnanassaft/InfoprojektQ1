package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.CollisionDetector;
import my_project.model.Player;
import my_project.model.Shot;
import my_project.model.Variable_Container;

public class ProgramController {

    Player player;
    private ViewController viewController;
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }
    public void startProgram() {

        Player player = new Player(100, 100, 100, 100, 200, 20, viewController,68, 65, 32);
        Player player1 = new Player( 200, 200, 100, 100, 200, 1705, viewController,39, 37, 155);
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
