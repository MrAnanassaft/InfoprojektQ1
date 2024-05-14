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
    public static Player normplayer;
    public static Player playerTarget;
    double velocity = 100;
    ViewController viewController;

    public Shot(double x, double y, double dirX, double dirY, double radius, Player normplayer, Player playerTarget){
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.radius = radius;
        this.player = normplayer;
        this.playerTarget = playerTarget;
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawFilledRectangle(x, y, 20, 8);
    }

    public void update(double dt) {
        System.out.println(player.getX());


        if (ViewController.isKeyDown(69)){
            normplayer = ProgramController.player;
            playerTarget = ProgramController.player1;
        }
        if (ViewController.isKeyDown(17)){
            normplayer = ProgramController.player1;
            playerTarget = ProgramController.player;
        }

        dirX = playerTarget.getX() - normplayer.getX();
        dirY = playerTarget.getY() - normplayer.getY();

        x = player.normX;
        y = player.normY;

        x += dirX * velocity + (x - player.getX());
        y += dirY * velocity + (y - player.getY());

        for (int i = 0; i <= Variable_Container.shots.size() - 1; i++){
           // Shot ball = Variable_Container.shots.get(i);

            if (x > viewController.getDrawFrame().getWidth() + radius || x < viewController.getDrawFrame().getWidth() - viewController.getDrawFrame().getWidth() - radius || y > viewController.getDrawFrame().getHeight() + radius || y < viewController.getDrawFrame().getHeight() - viewController.getDrawFrame().getHeight() - radius){
                Variable_Container.shots.remove(i);
            }
        }
    }
}
