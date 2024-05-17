package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.simple_gui.Button;
import my_project.model.*;
import my_project.model.buttons.*;

public class ProgramController {

    public static Player player;
    public static Player player1;
    public StartButton startButton;
    public RestartButton restartButton;
    public SkinButton skinButton;
    public MapButton mapButton;

    public Button[] buttons = new Button[10];
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
        viewController.createScene(); // index 0 = start
        viewController.createScene(); // index 1 = game
        viewController.createScene(); // index 2 = end
        viewController.createScene(); // index 3 = skins
        viewController.createScene(); // index 4 = map
        viewController.showScene(0);
    }
    public void setScenesForStart(){
        for(int i = 0; i< viewController.getSceneSize(); i++){
            Background background = new Background(i, viewController);
            viewController.draw(background,i);
        }
        startButton = new StartButton(this);
        buttons[0] = startButton.button;
        viewController.draw(startButton,0);
        restartButton = new RestartButton(this);
        buttons[1] = restartButton.button;
        viewController.draw(restartButton,2);
        skinButton = new SkinButton(this);
        buttons[2] = skinButton.button;
        viewController.draw(skinButton,0);
        mapButton = new MapButton(this);
        buttons[3] = mapButton.button;
        viewController.draw(mapButton,0);
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
