package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;

import java.awt.*;

public class Shot extends GraphicalObject {

    double dirX = 0;
    double dirY = 0;

    double velocity = 100;
    ViewController viewController;

    public Shot(double x, double y, double dirX, double dirY, double radius){
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.radius = radius;
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawFilledRectangle(x, y, 20, 8);
    }

    public void update(double dt) {
        x += dirX * velocity;
        y += dirY * velocity;

        for (int i = 0; i <= Variable_Container.shots.size() - 1; i++){
           // Shot ball = Variable_Container.shots.get(i);

            if (x > viewController.getDrawFrame().getWidth() + radius || x < viewController.getDrawFrame().getWidth() - viewController.getDrawFrame().getWidth() - radius || y > viewController.getDrawFrame().getHeight() + radius || y < viewController.getDrawFrame().getHeight() - viewController.getDrawFrame().getHeight() - radius){
                Variable_Container.shots.remove(i);
            }
        }
    }
}
