package my_project.model.buttons;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

public abstract class Buttons extends GraphicalObject {
    protected ProgramController p;
    public Button button;
    //private BufferedImage image;
    protected boolean wasPressed = false;
    public Buttons(ProgramController p){
        this.p = p;
    }

    public void draw(DrawTool drawTool){

    }
}
