package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static my_project.control.ProgramController.*;

public class SkinButton extends Buttons{
    public static boolean isPlayer1 = true;
    private String[] pathToImage = new String[4];
    public static BufferedImage blackbear;
    public static BufferedImage hotdog;
    public static BufferedImage man;
    public static BufferedImage manStretched;
    public static boolean isBlackbear;
    public static boolean isHotdog;
    public static boolean isMan;
    public static boolean isManStretched;
    public SkinButton(ProgramController p){
        super(p);
        setPicture("src/main/resources/graphic/buttons/Skinselectbutton.png");
        pathToImage[0] = "src/main/resources/graphic/skins/Skin Blackbear.png";
        pathToImage[1] = "src/main/resources/graphic/skins/Skin Hotdog.png";
        pathToImage[2] = "src/main/resources/graphic/skins/Skin Man.png";
        pathToImage[3] = "src/main/resources/graphic/skins/Skin ManStretched.png";
        blackbear = createNewImage(pathToImage[0]);
        hotdog = createNewImage(pathToImage[1]);
        man = createNewImage(pathToImage[2]);
        manStretched = createNewImage(pathToImage[3]);
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed){
                    wasPressed = true;
                    getViewController().showScene(3);
                    create(4,3,200,100,400,pathToImage,1);
                    create(2,3,100,200,1400,players,1);
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
        if(Objects.equals(image,"src/main/resources/graphic/buttons/Player 1.png")){
            isPlayer1 = true;
        }else if (Objects.equals(image, "src/main/resources/graphic/buttons/Player 2.png")) {
            isPlayer1 = false;
        }else if(Objects.equals(image, "src/main/resources/graphic/skins/Skin Blackbear.png")){
            if(isPlayer1){
                player.selectBlackbear = true;
            } else {
                player1.selectBlackbear = true;
            }
            isBlackbear = true;
        }else if(Objects.equals(image , "src/main/resources/graphic/skins/Skin Hotdog.png")){
            if(isPlayer1){
                player.selectHotdog = true;
            } else {
                player1.selectHotdog = true;
            }
            isHotdog = true;
        }else if(Objects.equals(image, "src/main/resources/graphic/skins/Skin Man.png")){
            if(isPlayer1){
                player.selectMan = true;
            } else {
                player1.selectMan = true;
            }
            isMan = true;
        }else if(Objects.equals(image , "src/main/resources/graphic/skins/Skin ManStretched.png")){
            if(isPlayer1){
                player.selectManStretched = true;
            } else {
                player1.selectManStretched = true;
            }
            isManStretched = true;
        }
    }

    public static BufferedImage getBlackbear() {
        return blackbear;
    }
    public BufferedImage getHotdog(){
        return hotdog;
    }
    public BufferedImage getMan() {
        return man;
    }

    public BufferedImage getManStretched() {
        return manStretched;
    }
}
