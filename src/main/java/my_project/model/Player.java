package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import javax.swing.*;
import java.awt.*;

public class Player extends InteractiveGraphicalObject {

    //public final static int WINDOW_WIDTH = 600;
    //public final static int WINDOW_HEIGHT = 600 + 29;


    private String playerImageScar = "src/main/resources/graphic/weapons/Scar.png";
    private String playerImageSniper = "src/main/resources/graphic/weapons/Sniper.png";
    private int velocity = 500;
    private double gravityConstant = 981;
    private double verticalVeloctiy = 0;

    private double cooldown = 0;
    private boolean touchedGrass = false;
    public double degree = 0;

    private boolean cooldowntimerboolean = false;

    private int right;
    private int left;
    private int jump;
    private int shoot;
    public int healthbarwidth;
    public int healthbarx;
    public int health;

    public static boolean sniper = false;
    public static boolean scar = false;
    private double maxSniperCooldown = 1;
    private double maxScarCooldown = 0.15;
    private Player target;


    public ViewController viewController;
    public ProgramController programController;

    public Player(double x, double y, double width, double height, int healthbarwidth, int healthbarx, int health, ViewController viewController, int right, int left, int jump, int shoot) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.healthbarwidth = healthbarwidth;
        this.healthbarx = healthbarx;
        this.health = health;
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

        /*drawTool.setCurrentColor(new Color(255, 209, 209));
        drawTool.drawFilledRectangle(x,y,width,height);
        drawTool.setCurrentColor(new Color(0,0,0));*/

        drawTool.setCurrentColor(new Color(185, 0, 0));
        drawTool.drawFilledRectangle(healthbarx, 10, healthbarwidth, 30);
        /*drawTool.drawLine(0,200,600,200);
        drawTool.drawLine(0,500,600,500);
        drawTool.drawLine(0,400,600,400);
        drawTool.setCurrentColor(new Color(255,0,0));
        drawTool.drawLine(0,590,600,590);
        drawTool.drawLine(0,600,600,600);
        drawTool.drawLine(0,0,600,0);*/

        if (programController.selectScar){
            setNewImage(playerImageScar);
            drawTool.drawTransformedImage(getMyImage(), x + width / 2, y + height / 2, degree, 3);
            scar = true;
        }else{
            scar = false;
        }
        if (programController.selectSniper){
            setNewImage(playerImageSniper);
            drawTool.drawTransformedImage(getMyImage(), x + width / 2, y + height / 2, degree, 3);
            sniper = true;
        }else{
            sniper = false;
        }
    }

    public void update(double dt){
        cooldown -= dt;
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
        if (ViewController.isKeyDown(shoot) && cooldown < 0){
            if (scar){
                cooldown = maxScarCooldown;
            }else{
                cooldown = maxSniperCooldown;
            }

            shoot();
        }
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public void shoot(){
        if (target == null){
            return;
        }

        double magnitude = Math.sqrt((target.getX() - x) * (target.getX() - x) + (target.getY() - y) * (target.getY() - y));

        double normX = (target.getX() - x) / magnitude;
        double normY = (target.getY() - y) / magnitude;

        double weaponlength = getMyImage().getWidth();
        double weaponheight = getMyImage().getHeight();

        degree = (-Math.atan2(target.getX() - x, target.getY() - y)) * 57.296 + 90;
        if (degree >= 90 && degree <= 270){
            playerImageScar = "src/main/resources/graphic/weapons/Scarreversed.png";
            playerImageSniper = "src/main/resources/graphic/weapons/Sniper.png";
        }else{
            playerImageScar = "src/main/resources/graphic/weapons/Scar.png";
            playerImageSniper = "src/main/resources/graphic/weapons/Sniper.png";
        }

        Shot shot = new Shot(x + width / 2 + normX * weaponlength, y + height / 2 + normY * weaponheight, normX, normY, 7, this, target, viewController);
        Variable_Container.shots.add(shot);
        viewController.draw(shot, 1);
    }
}
