package my_project.model.Buildings;

import KAGO_framework.view.DrawTool;
import my_project.model.CollisionDetector;
import my_project.model.Player;

public class Stair extends Build {
    private final int direction;
    public static int LEFT = -1,RIGHT = 1;
    public Stair(double x, double y,int direction) {
        super(x, y);
        this.direction = direction;
    }
    public double getPlayerY(Player player){
        double dx;
        if(direction == LEFT){
            dx = x - player.getX();
        }else{
            dx = player.getX() + player.getWidth() - x;
        }
        double resultY = y - dx - player.getHeight();
        if(resultY <= y - 150 - player.getHeight()){
            return y-150-player.getHeight();
        }
        return resultY;
    }

    @Override
    public boolean collidesWithPlayer(Player player) {
        return CollisionDetector.lineRect(x,y,x + 150 * direction,y-150,player.getX(),player.getY(),player.getWidth(),player.getHeight());
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setLineWidth(1);
        drawTool.drawLine(x,y,x + 150 * direction,y-150);
    }
}
