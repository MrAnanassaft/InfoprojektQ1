package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;
import my_project.model.buttons.Buttons;

import java.awt.*;

import static my_project.control.ProgramController.viewController;

public class Select extends Buttons {
    private int scene;

    public Select(ProgramController p, int scene, String imagePath, double x, double y,double width, double height){
        super(p);
        this.scene = scene;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setPicture(imagePath);
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed){
                    wasPressed = true;
                }
            }

            @Override
            public int getSceneIndex() {
                return scene;
            }

            @Override
            public ViewController getViewController() {
                return viewController;
            }
        };
        button = new Button(buttonHandler,0,x,y,image,true);
        button.setHeight(image.getHeight());
        button.setWidth(image.getWidth());
        button.setFont("Monospace");
        viewController.draw(button,scene);
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawRectangle(x, y, width, height);
    }
}
