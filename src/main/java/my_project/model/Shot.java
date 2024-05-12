package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;

public class Shot extends GraphicalObject {

    double dirX = 0;
    double dirY = 0;

    double velocity = 100;

    public Shot(double x, double y, double dirX, double dirY, double radius){
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.radius = radius;
    }

    public void draw(DrawTool drawTool) {

    }

    public void update(double dt) {
        x += dirX * velocity;
        y += dirY * velocity;

        if (x > Config.WINDOW_WIDTH + radius || x < Config.WINDOW_WIDTH - radius || y > Config.WINDOW_HEIGHT + radius || y < Config.WINDOW_HEIGHT - radius){
            //Variable_Container.shots.remove();
        }
    }
}
