package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;
import my_project.model.buttons.Buttons;

import java.awt.*;
import java.sql.SQLOutput;

import static my_project.control.ProgramController.viewController;

public class Select extends Buttons {
    private int scene;
    private int scale = 5;
    public Select(ProgramController p, int scene, String imagePath, double x, double y){
        super(p);
        this.scene = scene;
        this.x = x;
        this.y = y;
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
        button.setDrawImage(false);
        button.setHeight(image.getHeight()*scale);
        button.setWidth(image.getWidth()*scale);
        button.setFont("Monospace");
        viewController.draw(button,scene);
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.RED);
        double tempX = 0;
        double tempY = 0;
        for(int i = 1; i <= scale; i++){
            tempX = tempX + i*image.getWidth()*0.5;
            tempY = tempY + i*image.getHeight()*0.5;
        }
        drawTool.drawTransformedImage(image,x+tempX,y+tempY,0,scale);
    }

    /*
        Scale = 1, im1: x = 300, y = 400;  im2: x = 700, y = 400;
        Scale = 2, im1: x = 280, y = 390;  im2: x = 670, y = 390;
        Scale = 3, im1: x = 240, y = 370;  im2: x = 610, y = 370;
        Scale = 4, im1: x = 180, y = 340;  im2: x = 520, y = 340;
        Scale = 5, im1: x = 100, y = 300;  im2: x = 400, y = 300;
        Result: im1: x = x - scale*20 - scale-1 * 20

        im1 : width = 40, heigth = 20
        im2 : width = 60, heigth = 20
     */
}
