package my_project.model.Buildings;

import KAGO_framework.model.GraphicalObject;
import my_project.model.Player;

public abstract class Build extends GraphicalObject {
    public Build(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public abstract boolean collidesWithPlayer(Player player);

}
