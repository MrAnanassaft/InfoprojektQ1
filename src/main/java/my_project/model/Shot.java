package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class Shot extends GraphicalObject {

    double dirX = 0;
    double dirY = 0;

    double velocity = 30;

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

    }
}
