package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import static my_project.control.ProgramController.viewController;

public class SkinButton extends Buttons{
    public SkinButton(ProgramController p){
        super(p);
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed){
                    wasPressed = true;
                    getViewController().showScene(3);
                }
            }

            @Override
            public int getSceneIndex() {
                return 0;
            }

            @Override
            public ViewController getViewController() {
                return ProgramController.viewController;
            }
        };

        button = new Button(buttonHandler, 0, 400, 600,"Skin", 50);
        button.setHeight(button.getHeight());
        button.setWidth(button.getWidth());
        button.setFont("Monospaced");
        viewController.draw(button,0);
    }
}
