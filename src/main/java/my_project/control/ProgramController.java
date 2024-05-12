package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.Player;

public class ProgramController {
    private ViewController viewController;
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }
    public void startProgram() {

        Player player = new Player(100, 100, viewController,68, 65, 32);
        Player player1 = new Player( 200, 200, viewController,39, 37, 155);
        viewController.draw(player);
        viewController.register(player);
        viewController.draw(player1);
        viewController.register(player1);

    }
    public void updateProgram(double dt){

    }
}
