package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Background extends GraphicalObject {
    private int scene;
    private ViewController viewController;
    public static int background = 1;
    public Background(int scene, ViewController viewController){
        this.scene = scene;
        this.viewController = viewController;
    }
    public void draw(DrawTool drawTool){
        if(scene == 0){
            drawS0(drawTool);
        }else if(scene == 1){
            drawS1(drawTool);
        }else if(scene == 2){
            drawS2(drawTool);
        }else if(scene == 3){
            drawS3(drawTool);
        }else if(scene == 4){
            drawS4(drawTool);
        }else if(scene == 5){
            drawS5(drawTool);
        }
    }

    public void update(double dt){


    }
    public void drawS0(DrawTool drawTool){
        setNewImage("src/main/resources/graphic/backgrounds/Background Title.png");
        drawTool.drawImage(getMyImage(),0,0);
        drawTool.setCurrentColor(Color.WHITE);
        //drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
        //drawTool.drawText(292,100,"WELCOME TO FORTNITE 2D"); //
    }
    public void drawS1(DrawTool drawTool){
        //drawTool.setCurrentColor(new Color(7, 119, 147));
        //drawTool.drawFilledRectangle(0,0,viewController.getDrawFrame().getWidth(),viewController.getDrawFrame().getHeight());
        if(background == 1){
            drawCave(drawTool);
        }else if(background == 2){
            drawPlains(drawTool);
        }else if(background == 3) {
            drawVulcan(drawTool);
        }
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
        drawTool.drawText(330,100,"LET THE BATTLE BEGIN!"); //

    }
    public void drawS2(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(7, 119, 147));
        drawTool.drawFilledRectangle(0,0,viewController.getDrawFrame().getWidth(),viewController.getDrawFrame().getHeight());
        drawTool.setCurrentColor(Color.GRAY);
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
        drawTool.drawText(292,100,"NICE GAME!"); //
    }
    public void drawS3(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(7, 119, 147));
        drawTool.drawFilledRectangle(0,0,viewController.getDrawFrame().getWidth(),viewController.getDrawFrame().getHeight());
        drawTool.setCurrentColor(Color.GRAY);
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
        drawTool.drawText(292,100,"PICK YOUR SKIN "); //
    }
    public void drawS4(DrawTool drawTool){
         drawTool.setCurrentColor(new Color(7, 119, 147));
         drawTool.drawFilledRectangle(0,0,viewController.getDrawFrame().getWidth(),viewController.getDrawFrame().getHeight());
         drawTool.setCurrentColor(Color.GRAY);
         drawTool.setCurrentColor(Color.WHITE);
         drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
         drawTool.drawText(292,100,"PICK YOUR WEAPONS"); //
    }
    public void drawS5(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(7, 119, 147));
        drawTool.drawFilledRectangle(0,0,viewController.getDrawFrame().getWidth(),viewController.getDrawFrame().getHeight());
        drawTool.setCurrentColor(Color.GRAY);
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
        drawTool.drawText(292,100,"PICK THE MAP"); //
    }
    public void drawCave(DrawTool drawTool){
        setNewImage("src/main/resources/graphic/backgrounds/Background Cave.png");
        drawTool.drawImage(getMyImage(),0,0);
    }
    public void drawPlains(DrawTool drawTool){
        setNewImage("src/main/resources/graphic/backgrounds/Background Plains.png");
        drawTool.drawImage(getMyImage(),0,0);
    }
    public void drawVulcan(DrawTool drawTool){
        setNewImage("src/main/resources/graphic/backgrounds/Background Vulcan.png");
        drawTool.drawImage(getMyImage(),0,0);
    }
    public static void setBackground(int backgroundIndex){
        background = backgroundIndex;
    }
}
