package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import java.awt.*;

import static my_project.control.ProgramController.*;

public class SkinButton extends Buttons{
    public static boolean isPlayer1 = true;
    private String[] pathToImage = new String[4];
    public SkinButton(ProgramController p){
        super(p);
        setPicture("src/main/resources/graphic/buttons/Skinselectbutton.png");
        pathToImage[0] = "src/main/resources/graphic/skins/Skin Blackbear.png";
        pathToImage[1] = "src/main/resources/graphic/skins/Skin Hotdog.png";
        pathToImage[2] = "src/main/resources/graphic/skins/Skin Man.png";
        pathToImage[3] = "src/main/resources/graphic/skins/Skin ManStretched.png";
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed){
                    wasPressed = true;
                    getViewController().showScene(3);
                    create(4,3,200,100,400,pathToImage,0.5);
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
        button = new Button(buttonHandler, 0, 320, 650,image,false);
        button.setHeight(image.getHeight());
        button.setWidth(image.getWidth());
        button.setFont("Monospaced");
        viewController.draw(button,0);
    }
    public void draw(DrawTool drawTool){
        //drawTool.setCurrentColor(Color.red);
        //drawTool.drawRectangle(x + 20, y + 180, 100, 300);
        //drawTool.drawRectangle(x + 140, y + 180, 100, 300);
        //drawTool.drawRectangle(x + 260, y + 180, 100, 300);
        //drawTool.drawRectangle(x + 380, y + 180, 100, 300);
    }
    public static void useButtons(String image){
        if(image == "src/main/resources/graphic/skins/Skin Blackbear.png"){
            if(isPlayer1){
                player.selectBlackbear = true;
            } else if (!isPlayer1) {
                player1.selectBlackbear = true;
            }
        }else if(image == "src/main/resources/graphic/skins/Skin Hotdog.png"){
            if(isPlayer1){
                player.selectHotdog = true;
            } else if (!isPlayer1) {
                player1.selectHotdog = true;
            }
        }else if(image == "src/main/resources/graphic/skins/Skin Man.png"){
            if(isPlayer1){
                player.selectMan = true;
            } else if (!isPlayer1) {
                player1.selectMan = true;
            }
        }else if(image == "src/main/resources/graphic/skins/Skin ManStretched.png"){
            if(isPlayer1){
                player.selectManStretched = true;
            } else if (!isPlayer1) {
                player1.selectManStretched = true;
            }
        }
    }
}
