package my_project.model.Buildings;

import KAGO_framework.view.DrawTool;
import my_project.model.CollisionDetector;
import my_project.model.Player;

import java.awt.*;

public class Wall extends Build{
    private int direction;
    public Wall(double x, double y){
        super(x,y);
        width = 10;
        height = 180;
    }

    public boolean collidesWithPlayer(Player player) {
        return CollisionDetector.lineRect(x,y,x ,y+150,player.getX(),player.getY(),player.getWidth(),player.getHeight());
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawFilledRectangle(x,y,width,height);
    }
}
