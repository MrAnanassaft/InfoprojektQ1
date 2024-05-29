package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import java.awt.*;

import static my_project.control.ProgramController.viewController;

public class WeaponButton extends Buttons{
    public WeaponButton(ProgramController p){
        super(p);
        setPicture("src/main/resources/graphic/buttons/Gunselectbutton.png");
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed) {
                    wasPressed = true;
                    viewController.showScene(5);
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
        button = new Button(buttonHandler, 0, 760, 650,image,false);
        button.setHeight(image.getHeight());
        button.setWidth(image.getWidth());
        button.setFont("Monospaced");
        viewController.draw(button,0);
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawRectangle(viewController.getDrawFrame().getWidth() - viewController.getDrawFrame().getWidth() + 150, 200, 150, 680);
        drawTool.drawRectangle(viewController.getDrawFrame().getWidth() - 300, 200, 150, 680);
    }
}
