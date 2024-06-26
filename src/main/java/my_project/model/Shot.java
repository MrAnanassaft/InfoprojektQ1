package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.control.ProgramController;

import java.awt.*;

public class Shot extends GraphicalObject {

    private double dirX = 0;
    private double dirY = 0;
    public Player shooter;
    public Player target;
    private double velocity = 2500;
    private ViewController viewController;
    public double degree = 0;

    private String bulletImage = "src/main/resources/graphic/weapons/Bullet.png";

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

        setNewImage(bulletImage);
        drawTool.drawTransformedImage(getMyImage(), x - getMyImage().getWidth() / 2, y - getMyImage().getHeight() / 2, shooter.degree, 1);
    }

    public void update(double dt) {
        double targetX = target.getX();
        double targetY = target.getY();
        double distanceX = targetX - x;
        double distanceY = targetY - y;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance < distance / 2) {

            dirX = Math.cos(distanceX / distance);
            dirY = Math.sin(distanceY / distance);
        }

        x += dirX * velocity * dt;
        y += dirY * velocity * dt;

        if (x > viewController.getDrawFrame().getWidth() + radius || x < -radius || y > viewController.getDrawFrame().getHeight() + radius || y < -radius) {
            viewController.removeDrawable(this);
            Variable_Container.shots.remove(this);
        }
    }
}