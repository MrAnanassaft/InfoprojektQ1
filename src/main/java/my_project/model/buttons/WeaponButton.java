package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;
import my_project.model.Variable_Container;

import java.awt.*;
import java.util.ArrayList;

import static my_project.control.ProgramController.*;

public class WeaponButton extends Buttons{
    private String[] pathToImage = new String[2];
    public WeaponButton(ProgramController p){
        super(p);
        setPicture("src/main/resources/graphic/buttons/Gunselectbutton.png");
        pathToImage[0] = "src/main/resources/graphic/weapons/Scar.png";
        pathToImage[1] = "src/main/resources/graphic/weapons/Sniper.png";
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed) {
                    wasPressed = true;
                    viewController.showScene(4);
                    create(2,4,300,400,400,pathToImage,5);
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
    public static void useButtons(String image){
        if(image == "src/main/resources/graphic/weapons/Scar.png"){
            if(isPlayer1){
                player.selectScar = true;
            } else if (!isPlayer1) {
                player1.selectScar = true;
            }
        }else if(image == "src/main/resources/graphic/weapons/Sniper.png"){
            if(isPlayer1){
                player.selectSniper = true;
            } else if (!isPlayer1) {
                player1.selectSniper = true;
            }
        }
    }

}
