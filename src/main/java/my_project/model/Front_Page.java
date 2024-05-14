package my_project.model;
import KAGO_framework.model.GraphicalObject;
import my_project.model.*;
import static my_project.control.ProgramController.viewController;
import my_project.control.ProgramController;
public class Front_Page extends GraphicalObject {
    public StartButton startButton;
    public Background background;
    public ProgramController p;

    public Front_Page(ProgramController p){
        this.p = p;
        background = new Background(0,viewController);
        startButton = new StartButton(p);
        viewController.draw(startButton);
    }

    public void drawButton(){
        viewController.draw(startButton);
    }
}
