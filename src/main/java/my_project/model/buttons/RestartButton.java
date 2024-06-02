package my_project.model.buttons;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;
import my_project.model.Player;
import my_project.model.Variable_Container;

import java.util.ArrayList;

import static my_project.control.ProgramController.*;


public class RestartButton extends Buttons {

    public RestartButton(ProgramController p) {
        super(p);
        setPicture("src/main/resources/graphic/buttons/Playagainbutton.png");
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed) {
                    wasPressed = true;
                    restartGame();
                }
            }

            @Override
            public int getSceneIndex() {
                return 2;
            }

            @Override
            public ViewController getViewController() {
                return ProgramController.viewController;
            }
        };
        button = new Button(buttonHandler, 0, 760,400,image,false);
        button.setHeight(image.getHeight());
        button.setWidth(image.getWidth());
        button.setFont("Monospaced");
        //viewController.draw(button,2);
    }

    private void restartGame() {
        remove();
        p.createNewPlayers();
        p.startButton.restart();
        p.viewController.removeAllDrawables();
        //p.enemies = new ArrayList<>();
        Variable_Container.buttons = new Button[10];
        Variable_Container.selects = new ArrayList<>();
        Variable_Container.shots = new ArrayList<>();
        p.startProgram();
        p.viewController.showScene(0);
        System.out.println(1);
    }

    private void remove(){
        ProgramController.viewController.removeDrawable(button,3);
        ProgramController.viewController.removeDrawable(button,1);
        ProgramController.viewController.removeDrawable(this,3);
        ProgramController.viewController.removeDrawable(this,1);
        viewController.removeDrawable(player);
        viewController.removeDrawable(player1);
    }

    public void restart(){
        wasPressed = false;
    }
}
