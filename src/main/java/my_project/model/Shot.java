package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.control.ProgramController;

import java.awt.*;

public class Shot extends GraphicalObject {

    double dirX = 0;
    double dirY = 0;
    public Player shooter;
    public Player target;
    double velocity = 2500;
    ViewController viewController;




    public Shot(double x, double y, double dirX, double dirY, double radius, Player shooter, Player target, ViewController viewController) {
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.radius = radius;
        this.shooter = shooter;
        this.target = target;
        this.viewController = viewController;
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawFilledCircle(x, y, radius);
    }

    public void update(double dt) {
        double targetX = target.getX();
        double targetY = target.getY();
        double distanceX = targetX - x;
        double distanceY = targetY - y;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance < distance / 2) {
            //dirX = distanceX / distance;
            //dirY = distanceY / distance;

            dirX = Math.cos(distanceX / distance);
            dirY = Math.sin(distanceY / distance);
        }

        x += dirX * velocity * dt;
        y += dirY * velocity * dt;

        //for (int i = 0; i <= Variable_Container.shots.size() - 1; i++){
        //Shot ball = Variable_Container.shots.get(i);
        if (x > viewController.getDrawFrame().getWidth() + radius || x < -radius || y > viewController.getDrawFrame().getHeight() + radius || y < -radius) {
            viewController.removeDrawable(this);
            Variable_Container.shots.remove(this);
        }
        //}
    }
}