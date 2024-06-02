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
        }else if(scene == 6){
            drawS6(drawTool);
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
        drawTool.drawText(viewController.getDrawFrame().getWidth() / 3.5,100,"PICK YOUR SKIN "); //
    }
    public void drawS4(DrawTool drawTool){
         drawTool.setCurrentColor(new Color(7, 119, 147));
         drawTool.drawFilledRectangle(0,0,viewController.getDrawFrame().getWidth(),viewController.getDrawFrame().getHeight());
         drawTool.setCurrentColor(Color.GRAY);
         drawTool.setCurrentColor(Color.WHITE);
         drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
         drawTool.drawText(viewController.getDrawFrame().getWidth() / 4.12,100,"PICK YOUR WEAPONS"); //
    }
    public void drawS5(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(7, 119, 147));
        drawTool.drawFilledRectangle(0,0,viewController.getDrawFrame().getWidth(),viewController.getDrawFrame().getHeight());
        drawTool.setCurrentColor(Color.GRAY);
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
        drawTool.drawText(viewController.getDrawFrame().getWidth() / 3.1,100,"PICK THE MAP"); //
    }
    public void drawS6(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(7, 119, 147));
        drawTool.drawFilledRectangle(0,0,viewController.getDrawFrame().getWidth(),viewController.getDrawFrame().getHeight());
        drawTool.setCurrentColor(Color.GRAY);
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.formatText("Monospaced",3,100);  // Bereits gecentert, bei veränderungen bitte wieder centern
        drawTool.drawText(viewController.getDrawFrame().getWidth() / 3,100,"DIRECTIONS"); //
        drawTool.formatText("Monospaced",3,20);
        drawTool.drawText(500,250,"Player 1 moves with a : Right, d : Left, Space : Jump");
        drawTool.drawText(500,310,"He shoots with e and builds with q");
        drawTool.drawText(500,370,"Player 2 moves with right : Right, left : Left, Num0 : Jump");
        drawTool.drawText(500,430,"He shoots with strg and builds with Right Shift");
        drawTool.drawText(500,490,"Before playing, you can choose the map, skin and your weapon");
        drawTool.drawText(500,550,"For selecting the Skin, press Skinbutton etc,");
        drawTool.drawText(500,610,"For leaving the Page, press Esc");
        drawTool.drawText(500,670,"If Player 1 wants to choose, press the Button Player 1, if");
        drawTool.drawText(500,730,"Player 2 wants to choose, press the Button Player 2, the current");
        drawTool.drawText(500,790,"Skin / Weapon / Map is shown");
        drawTool.drawText(500,850,"We wish you a happy fight, please do not cheat, harm people and other illegal stuff");
        drawTool.drawText(500,910,"To accept conditions, press Enter");
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
