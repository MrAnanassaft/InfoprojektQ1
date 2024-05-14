package my_project.model;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import static my_project.control.ProgramController.viewController;


public class RestartButton extends GraphicalObject {
    private ProgramController p;
    private Button button;
    //private BufferedImage image;
    private boolean wasPressed = false;

    public RestartButton(ProgramController p) {
        this.p = p;
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
        button.setHeight(100);
        button.setWidth(140);
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