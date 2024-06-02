package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;
import my_project.model.Variable_Container;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

import static my_project.control.ProgramController.*;

public class WeaponButton extends Buttons{

    public static boolean isPlayer1 = true;
    private String[] pathToImage = new String[2];
    public static BufferedImage scar;
    public static BufferedImage sniper;
    public WeaponButton(ProgramController p){
        super(p);
        setPicture("src/main/resources/graphic/buttons/Gunselectbutton.png");
        pathToImage[0] = "src/main/resources/graphic/weapons/Scar.png";
        pathToImage[1] = "src/main/resources/graphic/weapons/Sniper.png";
        scar = createNewImage(pathToImage[0]);
        sniper = createNewImage(pathToImage[1]);
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed) {
                    wasPressed = true;
                    viewController.showScene(4);
                    create(2,4,300,400,400,pathToImage,5);
                    create(2,4,100,200,1400,players,1);
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
        button = new Button(buttonHandler, 2, 760, 650,image,false);
        button.setHeight(image.getHeight());
        button.setWidth(image.getWidth());
        button.setFont("Monospaced");
        viewController.draw(button,0);
    }

    public void draw(DrawTool drawTool){
        if(viewController.getSceneIndex() == 4){
            if(isPlayer1){
                drawTool.drawText(700,200,"PLAYER 1 CHOOSES");
            }else{
                drawTool.drawText(700,200,"PLAYER 2 CHOOSES");
            }
        }
    }
    public static void useButtons(String image){
        if(Objects.equals(image,"src/main/resources/graphic/buttons/Player 1.png")){
            isPlayer1 = true;
        }else if (Objects.equals(image, "src/main/resources/graphic/buttons/Player 2.png")) {
            isPlayer1 = false;
        }else if(Objects.equals(image, "src/main/resources/graphic/weapons/Scar.png")){
            if(isPlayer1){
                player.selectScar = true;
                player.selectSniper = false;
            } else {
                player1.selectScar = true;
                player1.selectSniper = false;
            }
        }else if(Objects.equals(image, "src/main/resources/graphic/weapons/Sniper.png")){
            if(isPlayer1){
                player.selectSniper = true;
                player.selectScar = false;
            } else {
                player1.selectSniper = true;
                player1.selectScar = false;
            }
        }
    }

}
