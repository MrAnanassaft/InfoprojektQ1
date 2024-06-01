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

    public Select(ProgramController p, int scene, String imagePath, double x, double y, double scale) {
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
                if (!wasPressed) {
                    wasPressed = true;
                    if (code == 1) {
                        SkinButton.useButtons(imagePath);
                        SkinButton.isPlayer1 = false;
                        wasPressed = false;
                    }
                    if (code == 2) {
                        WeaponButton.useButtons(imagePath);
                        WeaponButton.isPlayer1 = false;
                        wasPressed = false;
                    }
                    if (code == 3) {
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
        button = new Button(buttonHandler, 0, x, y, image, true);
        button.setDrawImage(false);
        button.setHeight(image.getHeight() * scale);
        button.setWidth(image.getWidth() * scale);
        button.setFont("Monospace");
        viewController.draw(button, scene);
    }

    public void draw(DrawTool drawTool) {
        //drawTool.setCurrentColor(Color.RED);
        if(scale > 1){
            double tempX = 0;
            double tempY = 0;
            for(int i = 1; i < scale; i++){
                tempX = tempX + i*image.getWidth()*0.5;
                tempY = tempY + i*image.getHeight()*0.5;
            }
            //drawTool.drawTransformedImage(image,x+tempX,y+tempY,0,scale);
        }else{
            if(this.button.getButtonHandler().getSceneIndex() == 5 && scale < 1){
                //System.out.println(x);
                //System.out.println(y);
                double tempX = 0;
                double tempY = 0;
                double difx = 172.8;
                double dify = 97.2;
                double amount = (10 - scale*10);
                for(int i = 0; i < amount; i++){
                    tempX = tempX + (difx - (i*38.4));
                    tempY = tempY + (dify - (i*21.6));
                }
                drawTool.drawTransformedImage(image,x- tempX,y-tempY,0,scale);
                System.out.println("Width: " + image.getWidth() + ":  Height: " + image.getHeight());
            }
        }
        /*
        Normaler lauf:   Map 1: x = 200, y = 400;  Map 2: x = 500, y = 400;   Map 3: x = 800, y = 400;
        Scale 2: Map 1: x = -760, y = -140;  Map 2: x = -460, y = -140;  Map 3: x = -160, y = -140
        Scale 1.0: Map 1: x = 200, y = 400;  Map 2: x = 500, y = 400;   Map 3: x = 800, y = 400;
        Scale 0.9: Map 1: x = 372.8, y = 497.2; Map 2: x = 672.8, y = 497.2; Map 3: x = 972.8, y = 497.2
        Scale 0.8: Map 1: x = 507.2, y = 572.8; Map 2: x = 807.2, y = 572.8; Map 3: x = 1107.2, y = 572.8
       Scale 0.7: Map 1: x = 603.2, y = 626.8; Map 2: x = 903.2, y = 626.8; Map 3: x = 1203.2, y = 626.8
       Scale 0.6: Map 1: x = 660.8, y = 659.2; Map 2: x = 960.8, y = 659.2; Map 3: x = 1260.8, y = 659.2;
       Scale 0.5: Map 1: x = 680, y = 670;  Map 2: x = 980, y = 670;  Map 3: x = 1280, y = 670
       Scale 0.4: Map 1: x = 660.8, y = 659.2; Map 2: x = 960.8, y = 659.2; Map 3: x = 1260.8, y = 659.2;
       Scale 0.3: Map 1: x = 603.2, y = 626.8; Map 2: x = 903.2, y = 626.8; Map 3: x = 1203.2, y = 626.8;
       Scale 0.2: Map 1: x = 507.2, y = 572.8; Map 2: x = 807.2, y = 572.8; Map 3: x = 1107.2, y = 572.8;
       Scale 0.1: Map 1: x = 372.8, y = 497.2; Map 2: x = 672.8, y = 497.2; Map 3: x = 972.8, y = 497.2;
       Scale 0.0: Map 2: x = 200.0, y = 400.0; Map 2: x = 500.0, y = 400.0; Map 3: x = 800.0, y = 400.0;
       M1: 1 - 0.9: difx = +172.8      dify = + 97.2
           0.9 - 0.8: difx = +134.4    dify = 75.6
           0.8 - 0.7: difx = +96       dify = 54
           0.7 - 0.6: difx = +57.6
           0.6 - 0.5: difx = +19.2
           0.5 - 0.4: difx = -19.2
           1 - 0.5: difx = 480
       Width = 1980
       Height = 1080
               difdifx = 38.4;
                    */

    }

    public void tellWoChooses(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(12, 12, 68, 37));
    }
}
