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

    Player player;
    Player playerTarget;
    double velocity = 100;
    ViewController viewController;

    public Shot(double x, double y, double dirX, double dirY, double radius, Player player, Player playerTarget){
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.radius = radius;
        this.player = player;
        this.playerTarget = playerTarget;
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawFilledRectangle(x, y, 20, 8);
    }

    public void update(double dt) {
        x += dirX * velocity + (x - player.getX());
        y += dirY * velocity + (y - player.getY());

        double magnitude = Math.sqrt(x * x + y * y);

        double normX = x / magnitude;
        double normY = y / magnitude;

        dirX = ProgramController.player1.getX() - ProgramController.player.getX();
        dirY = ProgramController.player1.getY() - ProgramController.player.getY();


        for (int i = 0; i <= Variable_Container.shots.size() - 1; i++){
           // Shot ball = Variable_Container.shots.get(i);

            if (x > viewController.getDrawFrame().getWidth() + radius || x < viewController.getDrawFrame().getWidth() - viewController.getDrawFrame().getWidth() - radius || y > viewController.getDrawFrame().getHeight() + radius || y < viewController.getDrawFrame().getHeight() - viewController.getDrawFrame().getHeight() - radius){
                Variable_Container.shots.remove(i);
            }
        }
    }
}
