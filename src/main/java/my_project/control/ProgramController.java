package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.*;

public class ProgramController {

    public static Player player;
    public static Player player1;
    public StartButton startButton;
    public RestartButton restartButton;
    double timer;
    public static ViewController viewController;
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }
    public void startProgram() {
        startNewGame();
        setScenesForStart();
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
        //timer += dt;
        for (int i = 0; i <= Variable_Container.shots.size() - 1; i++){
            Shot shot = Variable_Container.shots.get(i);
            if ((CollisionDetector.circleWithRectangle(shot.getX(), shot.getY(), shot.getRadius(), player.getX(), player.getY(), player.getWidth(), player.getHeight()) && shot.shooter != player) || (CollisionDetector.circleWithRectangle(shot.getX(), shot.getY(), shot.getRadius(), player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight()) && shot.shooter != player1)){
                if (shot.target == player){
                    player1.health -= 2;

                    player1.healthbarwidth -= 2;
                }
                if (shot.target == player1){
                    player.health -= 2;

                    player.healthbarwidth -= 2;
                    player.healthbarx += 2;
                }
                viewController.removeDrawable(Variable_Container.shots.get(i));
                Variable_Container.shots.remove(i);
                i--;

            }

        }
    }

    public void startNewGame(){
        viewController.createScene();
        viewController.createScene();
        viewController.createScene();
        viewController.showScene(0);
    }
    public void setScenesForStart(){
        Background backgroundS1 = new Background(0, viewController);
        viewController.draw(backgroundS1,0);
        startButton = new StartButton(this);
        viewController.draw(startButton,0);
        restartButton = new RestartButton(this);
        viewController.draw(restartButton,2);
    }
    public void startGame(){
        player = new Player(100, 100, 100, 100, 200, viewController.getDrawFrame().getWidth() - 200 - 10, 200, viewController,68, 65, 32, 69);
        player1 = new Player( 200, 200, 100, 100, 200, viewController.getDrawFrame().getWidth() - viewController.getDrawFrame().getWidth() + 10, 200, viewController,39, 37, 155, 17);

        player.setTarget(player1);
        player1.setTarget(player);

        viewController.draw(player,1);
        viewController.register(player);
        viewController.draw(player1,1);
        viewController.register(player1);
        viewController.showScene(1);
    }

}

    //Player player = new Player(100, 100, 100, 100, 200, 20, viewController,68, 65, 32);
    //Player player1 = new Player( 200, 200, 100, 100, 200, 1705, viewController,39, 37, 155);
