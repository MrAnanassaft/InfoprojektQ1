package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;
import my_project.model.Variable_Container;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static my_project.control.ProgramController.viewController;

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
    public void create(int amount, int scene, double x, double y, double diff, String[] string){
        for(int i = 0; i < amount; i++){
            if(string[i] != null) {
                Select select = new Select(p, scene, string[i], x + i*diff, y);
                Variable_Container.selects.add(select.button);
                viewController.draw(select, scene);
            }
        }
    }
}
