package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import static my_project.control.ProgramController.viewController;

public class MapButton extends Buttons{
    public MapButton(ProgramController p){
        super(p);
        setPicture("src/main/resources/graphic/buttons/Mapselectbutton.png");
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed){
                    wasPressed = true;
                    getViewController().showScene(4);
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

        button = new Button(buttonHandler, 0, 1200, 650,image,false);
        button.setHeight(image.getHeight());
        button.setWidth(image.getWidth());
        button.setFont("Monospaced");
        viewController.draw(button,0);
    }
}
