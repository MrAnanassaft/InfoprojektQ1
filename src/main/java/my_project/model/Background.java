package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Background extends GraphicalObject {
    private int scene;
    private ViewController viewController;
    public Background(int scene, ViewController viewController){
        this.scene = scene;
        this.viewController = viewController;
    }
    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(7, 119, 147));
        drawTool.drawFilledRectangle(0,0,viewController.getDrawFrame().getWidth(),viewController.getDrawFrame().getHeight());
        drawTool.setCurrentColor(Color.GRAY);
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
        drawTool.drawText(292,100,"WELCOME TO FORTNITE 2D"); //
    }

    public void update(double dt){


    }
    public void drawS0(DrawTool drawTool){

    }
    public void drawS1(DrawTool drawTool){

    }
    public void drawS2(DrawTool drawTool) {

    }
    public void drawS3(DrawTool drawTool){

    }
    public void drawS4(DrawTool drawTool){

    }
}
