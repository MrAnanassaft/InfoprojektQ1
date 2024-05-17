package my_project.model.buttons;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import static my_project.control.ProgramController.viewController;


public class RestartButton extends Buttons {

    public RestartButton(ProgramController p) {
        super(p);
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
        button = new Button(buttonHandler, 0, 90, 290,"Play Again", 50);
        button.setHeight(button.getHeight());
        button.setWidth(button.getWidth());
        button.setFont("Monospaced");
        viewController.draw(button,2);
    }
    private void restartGame() {
        remove();
        p.startButton.restart();
        p.viewController.removeAllDrawables();
        //p.enemies = new ArrayList<>();
        p.startGame();
        p.setScenesForStart();
        p.viewController.showScene(0);
    }

    private void remove(){
        ProgramController.viewController.removeDrawable(button,3);
        ProgramController.viewController.removeDrawable(button,1);
        ProgramController.viewController.removeDrawable(this,3);
        ProgramController.viewController.removeDrawable(this,1);
    }

    public void restart(){
        wasPressed = false;
    }
}
