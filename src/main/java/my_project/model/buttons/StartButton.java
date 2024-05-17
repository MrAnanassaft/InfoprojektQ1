package my_project.model.buttons;

import KAGO_framework.control.SoundController;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static my_project.control.ProgramController.viewController;

public class StartButton extends Buttons {


    public StartButton(ProgramController p) {
        super(p);
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed) {
                    wasPressed = true;
                    System.out.println(1);
                    p.startGame();
                    p.restartButton.restart();
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

        button = new Button(buttonHandler, 0, 800, 400,"Start", 50);
        button.setHeight(button.getHeight());
        button.setWidth(button.getWidth());
        button.setFont("Monospaced");
        viewController.draw(button,0);
    }


    public void update(double dt){

    }
    public void restart(){
        wasPressed = false;
    }

}
