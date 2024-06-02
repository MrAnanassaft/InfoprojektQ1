package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.model.*;
import my_project.model.Buildings.Build;
import my_project.model.buttons.*;

import java.util.ArrayList;

public class ProgramController {

    public static Player player;
    public static Player player1;
    public StartButton startButton;
    public RestartButton restartButton;
    public SkinButton skinButton;
    public MapButton mapButton;
    public WeaponButton weaponButton;

   // public static boolean selectScar = false;
    //public static boolean selectSniper = false;



    private ArrayList<Build> allBuildings = new ArrayList<>();


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
        if(ViewController.isKeyDown(77)){ // M
            player.selectScar = true;
        }
        if(ViewController.isKeyDown(78)){ // N
           player1.selectSniper = true;
        }

        if(ViewController.isKeyDown(74)){ // J
            viewController.showScene(0);
        }
        if(ViewController.isKeyDown(75)){ // K
            viewController.showScene(1);
        }
        if(ViewController.isKeyDown(76)){ // L
            viewController.showScene(2);
        }
        if(ViewController.isKeyDown(27)){
            if(viewController.getSceneIndex() == 3){
                viewController.showScene(0);
                skinButton.notPressed();
            }
            if(viewController.getSceneIndex() == 4){
                viewController.showScene(0);
                weaponButton.notPressed();
            }
            if(viewController.getSceneIndex() == 5){
                viewController.showScene(0);
                mapButton.notPressed();
            }
        }
        //timer += dt;
        for (int i = 0; i <= Variable_Container.shots.size() - 1; i++){
            Shot shot = Variable_Container.shots.get(i);
            if ((CollisionDetector.circleWithRectangle(shot.getX(), shot.getY(), shot.getRadius(), player.getX(), player.getY(), player.getWidth(), player.getHeight()) && shot.shooter != player) || (CollisionDetector.circleWithRectangle(shot.getX(), shot.getY(), shot.getRadius(), player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight()) && shot.shooter != player1)){
                if (shot.target == player){
                    if (player1.selectScar){
                        player1.health -= 2;

                        player1.healthbarwidth -= 2;
                    } else {
                        player1.health -= 15;

                        player1.healthbarwidth -= 15;
                    }
                }
                if (shot.target == player1){
                    if (player.selectScar){
                        player.health -= 2;

                        player.healthbarwidth -= 2;
                        player.healthbarx += 2;
                    } else {
                        player.health -= 15;

                        player.healthbarwidth -= 15;
                        player.healthbarx += 15;
                    }
                }
                viewController.removeDrawable(Variable_Container.shots.get(i));
                Variable_Container.shots.remove(i);
                i--;

            }
            if (i >= 0){
                for (Build build1: player.getAllBuildings()) {
                    if (CollisionDetector.circleWithRectangle(shot.getX(), shot.getY(), shot.getRadius(), build1.getX(), build1.getY(), build1.getWidth() * 7, build1.getHeight())){
                        build1.setBuildhealth();
                        if (build1.buildhealth <= 0){
                            player.destroyBuild(build1);
                        }

                        viewController.removeDrawable(Variable_Container.shots.get(i));
                        Variable_Container.shots.remove(i);
                        i--;
                        break;
                    }
                }
            }
            if (i >= 0) {
                for (Build build2 : player1.getAllBuildings()) {
                    if (CollisionDetector.circleWithRectangle(shot.getX(), shot.getY(), shot.getRadius(), build2.getX(), build2.getY(), build2.getWidth() * 7, build2.getHeight())) {
                        build2.setBuildhealth();

                        if (build2.buildhealth <= 0) {
                            player.destroyBuild(build2);
                        }

                        viewController.removeDrawable(Variable_Container.shots.get(i));
                        Variable_Container.shots.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }


        if (player.health <= 0 || player1.health <= 0){
            viewController.showScene(2);
        }
    }

    public void startNewGame(){
        viewController.createScene(); // index 0 = start
        viewController.createScene(); // index 1 = game
        viewController.createScene(); // index 2 = end
        viewController.createScene(); // index 3 = skins
        viewController.createScene(); // index 4 = weapon
        viewController.createScene(); // index 5 = map
        viewController.showScene(0);
        Player.velocity = 500;
        createNewPlayers();
        /*player = new Player(100, 100, 100, 100, 200, viewController.getDrawFrame().getWidth() - 200 - 10, 200, viewController,68, 65, 32, 69, allBuildings, true, false, true, false, false, false);
        player1 = new Player( 200, 200, 100, 100, 200, viewController.getDrawFrame().getWidth() - viewController.getDrawFrame().getWidth() + 10, 200, viewController,39, 37, 155, 17, allBuildings, true, false, false, true, false, false);
        */
    }
    public void setScenesForStart(){
        for(int i = 0; i< viewController.getSceneSize(); i++){
            Background background = new Background(i, viewController);
            viewController.draw(background,i);
        }
        startButton = new StartButton(this);
        Variable_Container.buttons[0] = startButton.button;
        viewController.draw(startButton,0);
        restartButton = new RestartButton(this);
        Variable_Container.buttons[1] = restartButton.button;
        viewController.draw(restartButton,2);
        skinButton = new SkinButton(this);
        Variable_Container.buttons[2] = skinButton.button;
        viewController.draw(skinButton,0);
        weaponButton = new WeaponButton(this);
        Variable_Container.buttons[3] = weaponButton.button;
        viewController.draw(weaponButton,0);
        mapButton = new MapButton(this);
        Variable_Container.buttons[4] = mapButton.button;
        viewController.draw(mapButton,0);

    }
    public void startGame(){
        allBuildings = new ArrayList<>();

        player.setTarget(player1);
        player1.setTarget(player);

        viewController.draw(player,1);
        viewController.register(player);
        viewController.draw(player1,1);
        viewController.register(player1);
        viewController.showScene(1);
    }
    public void createNewPlayers(){
        player = new Player(100, 100, 100, 100, 200, viewController.getDrawFrame().getWidth() - 200 - 10, 200, viewController,68, 65, 32, 69, allBuildings, true, false, true, false, false, false, 81, 82, 70);
        player1 = new Player( 200, 200, 100, 100, 200, viewController.getDrawFrame().getWidth() - viewController.getDrawFrame().getWidth() + 10, 200, viewController,39, 37, 155, 17, allBuildings, true, false, false, true, false, false, 16, 97, 98);
    }
}

/*if (shot.target == player){
        if (player1.selectScar && player1.selectSniper == false){
        player1.health -= 2;

        player1.healthbarwidth -= 2;
        } else if (player1.selectSniper && player1.selectScar == false) {
        player1.health -= 15;

        player1.healthbarwidth -= 15;
        }
        }
        if (shot.target == player1){
        if (player.selectScar && player.selectSniper== false){
        player.health -= 2;

        player.healthbarwidth -= 2;
        player.healthbarx += 2;
        } else if (player.selectSniper && player.selectScar == false) {
        player.health -= 15;

        player.healthbarwidth -= 15;
        player.healthbarx += 15;
        }
        }*/