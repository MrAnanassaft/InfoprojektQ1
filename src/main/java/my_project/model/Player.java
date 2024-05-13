package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Player extends InteractiveGraphicalObject {

    //public final static int WINDOW_WIDTH = 600;
    //public final static int WINDOW_HEIGHT = 600 + 29;

    private int velocity = 500;
    private double gravityConstant = 981;
    private double verticalVeloctiy = 0;

    private double cooldown = 1;
    private boolean touchedGrass = false;

    private boolean cooldowntimerboolean = false;

    private int right;
    private int left;
    private int jump;
    private int shoot;
    public int healthbarwidth;
    public int healthbarx;
    ViewController viewController;
    public Player(double x, double y, double width, double height, int healthbarwidth, int healthbarx, ViewController viewController, int right, int left, int jump, int shoot) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.healthbarwidth = healthbarwidth;
        this.healthbarx = healthbarx;
        this.right = right;
        this.left = left;
        this.jump = jump;
        this.shoot = shoot;
        this.viewController = viewController;
    }

    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(225, 209, 209));
        drawTool.drawFilledRectangle(x,y,width,height);
        drawTool.setCurrentColor(new Color(0,0,0));

        drawTool.setCurrentColor(new Color(255, 209, 209));
        drawTool.drawFilledRectangle(x,y,width,height);
        drawTool.setCurrentColor(new Color(0,0,0));

        drawTool.setCurrentColor(new Color(185, 0, 0));
        drawTool.drawFilledRectangle(healthbarx, 10, healthbarwidth, 30);

        /*drawTool.drawLine(0,200,600,200);
        drawTool.drawLine(0,500,600,500);
        drawTool.drawLine(0,400,600,400);
        drawTool.setCurrentColor(new Color(255,0,0));
        drawTool.drawLine(0,590,600,590);
        drawTool.drawLine(0,600,600,600);
        drawTool.drawLine(0,0,600,0);*/
    }

    public void update(double dt){
       // System.out.println(healthbarx);
        /*System.out.println(Config.WINDOW_WIDTH);
        System.out.println(Config.WINDOW_HEIGHT);*/

        if(y < viewController.getDrawFrame().getHeight() - height){
            y += verticalVeloctiy * dt;
            verticalVeloctiy += gravityConstant * dt;
            touchedGrass = false;
        }else {
            verticalVeloctiy = 0;
            y = viewController.getDrawFrame().getHeight() - height;
            touchedGrass = true;
            cooldowntimerboolean = false;
        }
        /*System.out.println(Config.WINDOW_HEIGHT -40- height);
        System.out.println(Config.WINDOW_HEIGHT-40);
        System.out.println(height);
        System.out.println(y + 100);*/
        //System.out.println(WINDOW_HEIGHT);
        if (ViewController.isKeyDown(right)) { // d
            x += velocity * dt;
        }
        if (ViewController.isKeyDown(left)) { // a
            x -= velocity * dt;
        }
        if(touchedGrass){
            if (ViewController.isKeyDown(jump)) { // Leertaste
                cooldowntimerboolean = true;
            }
        }
        if(cooldowntimerboolean){
            y -= velocity * dt;
        }
        if (ViewController.isKeyDown(shoot)){
            shoot();
        }
    }
    public void shoot(){
        //Shot shot = new Shot(x, y, );
    }
}
