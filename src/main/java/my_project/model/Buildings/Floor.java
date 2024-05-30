package my_project.model.Buildings;

import KAGO_framework.view.DrawTool;
import my_project.model.Player;

public class Floor extends Build{
    public Floor(double x, double y) {
        super(x, y);
        width = 150;
        height = 10;
    }

    @Override
    public boolean colidesWithPlayer(Player player) {
        return player.collidesWith(this);
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawFilledRectangle(x,y - height/2,width,height);
    }
}
