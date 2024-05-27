package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Buttons extends GraphicalObject {
    protected ProgramController p;
    public Button button;
    protected BufferedImage image;
    protected boolean wasPressed = false;
    public Buttons(ProgramController p){
        this.p = p;
    }

    public void draw(DrawTool drawTool){

    }
    protected void setPicture(String pathToImage) {
        try {
            image = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void notPressed(){
        wasPressed = false;
    }
}
