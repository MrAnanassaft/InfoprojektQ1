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
    private double scale;
    public Select(ProgramController p, int scene, String imagePath, double x, double y,double scale){
        super(p);
        this.scene = scene;
        this.x = x;
        this.y = y;
        this.scale = scale;
        //this.whatButton = whatButton;
        setPicture(imagePath);
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed){
                    wasPressed = true;
                    if(code == 1){
                        SkinButton.useButtons(imagePath);
                        SkinButton.isPlayer1 = false;
                        wasPressed = false;
                    }
                    if(code == 2) {
                        WeaponButton.useButtons(imagePath);
                        WeaponButton.isPlayer1 = false;
                        wasPressed = false;
                    }
                    if(code == 3){
                        MapButton.useButtons(imagePath);
                        wasPressed = false;
                    }
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
        for(int i = 1; i < scale; i++){
            tempX = tempX + i*image.getWidth()*0.5;
            tempY = tempY + i*image.getHeight()*0.5;
        }
        drawTool.drawTransformedImage(image,x+tempX,y+tempY,0,scale);
    }
    public void gotClicked(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(12, 12, 68, 37));
        drawTool.drawFilledRectangle(x,y, getWidth(), getHeight());
    }
}
