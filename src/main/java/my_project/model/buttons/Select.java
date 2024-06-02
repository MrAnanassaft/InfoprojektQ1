package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;
import my_project.model.Background;
import my_project.model.buttons.Buttons;

import java.awt.*;
import java.sql.SQLOutput;

import static my_project.control.ProgramController.*;

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
                        wasPressed = false;
                    }
                    if (code == 2) {
                        WeaponButton.useButtons(imagePath);
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
        Graphics2D g2d = drawTool.getGraphics2D();
        g2d.drawImage(image, (int) x, (int) y, (int) (scale * image.getWidth()), (int) (scale * image.getHeight()), null);
        drawTool.setCurrentColor(Color.BLACK);
        if (viewController.getSceneIndex() == 3) {
            if (SkinButton.isPlayer1) {
                drawTool.drawText(viewController.getDrawFrame().getWidth() / 4, 200, "PLAYER 1 CHOOSES");
            } else {
                drawTool.drawText(viewController.getDrawFrame().getWidth() / 4, 200, "PLAYER 2 CHOOSES");
            }
            if((player.selectBlackbear && SkinButton.isPlayer1) || (player1.selectBlackbear && !SkinButton.isPlayer1)){
                g2d.drawImage(SkinButton.blackbear,   510,300,(2 * SkinButton.blackbear.getWidth()),(2 * SkinButton.blackbear.getHeight()),null);
            }else if((player.selectHotdog  && SkinButton.isPlayer1)|| (player1.selectHotdog && !SkinButton.isPlayer1)){
                g2d.drawImage(SkinButton.hotdog,  510,300,(2 * SkinButton.hotdog.getWidth()),(2 * SkinButton.hotdog.getHeight()),null);
            }else if((player.selectMan && SkinButton.isPlayer1) || (player1.selectMan && !SkinButton.isPlayer1)){
                g2d.drawImage(SkinButton.man,  510,300,(2 * SkinButton.man.getWidth()), (2 * SkinButton.man.getHeight()),null);
            }else if((player.selectManStretched && SkinButton.isPlayer1)|| (player1.selectManStretched && !SkinButton.isPlayer1)) {
                g2d.drawImage(SkinButton.manStretched,  510,300,(2 * SkinButton.manStretched.getWidth()), (2 * SkinButton.manStretched.getHeight()),null);
            }
        } else if (viewController.getSceneIndex() == 4) {
            if (WeaponButton.isPlayer1) {
                drawTool.drawText(viewController.getDrawFrame().getWidth() / 4, 200, "PLAYER 1 CHOOSES");
            } else {
                drawTool.drawText(viewController.getDrawFrame().getWidth() / 4, 200, "PLAYER 2 CHOOSES");
            }
            if((player.selectScar && WeaponButton.isPlayer1) || (player1.selectScar && !WeaponButton.isPlayer1)){
                g2d.drawImage(WeaponButton.scar,  510,300,(5*WeaponButton.scar.getWidth()),(5*WeaponButton.scar.getHeight()),null);
            }else if((player.selectSniper && WeaponButton.isPlayer1) || (player1.selectSniper && !WeaponButton.isPlayer1)){
                g2d.drawImage(WeaponButton.sniper,  510,300,(5*WeaponButton.sniper.getWidth()),(5*WeaponButton.sniper.getHeight()),null);
            }
        } else if (viewController.getSceneIndex() == 5 ) {
            drawTool.drawText(viewController.getDrawFrame().getWidth() / 3,200,"CURRENT MAP");
            if(Background.background == 1){
                g2d.drawImage(MapButton.cave,(viewController.getDrawFrame().getWidth() / 2) - (int)(0.2*MapButton.cave.getWidth()) / 2,300,(int)(0.2*MapButton.cave.getWidth()),(int)(0.2*MapButton.cave.getHeight()),null);
            }else if(Background.background == 2){
                g2d.drawImage(MapButton.plains,(viewController.getDrawFrame().getWidth() / 2) - (int)(0.2*MapButton.plains.getWidth()) / 2,300,(int)(0.2*MapButton.plains.getWidth()),(int)(0.2*MapButton.plains.getHeight()),null);
            }else if(Background.background == 3){
                g2d.drawImage(MapButton.vulcan,(viewController.getDrawFrame().getWidth() / 2) - (int)(0.2*MapButton.vulcan.getWidth()) / 2,300,(int)(0.2*MapButton.vulcan.getWidth()),(int)(0.2*MapButton.vulcan.getHeight()),null);
            }
        }
    } // Imagine ich habe eif was krasses gefixed: Zitat Kiyan

    public void tellWoChooses(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(12, 12, 68, 37));
    }

}
