package my_project.model;

import KAGO_framework.Config;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import javafx.scene.input.KeyCode;
import my_project.control.ProgramController;
import my_project.model.Buildings.Build;
import my_project.model.Buildings.Floor;
import my_project.model.Buildings.Wall;
import my_project.model.Buildings.Stair;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends InteractiveGraphicalObject {

    private String playerImageScar = "src/main/resources/graphic/weapons/Scar.png";
    private String playerImageSniper = "src/main/resources/graphic/weapons/Sniper.png";

    private String playerImageBlackbear = "src/main/resources/graphic/skins/Skin Blackbear.png";
    private String playerImageHotdog = "src/main/resources/graphic/skins/Skin Hotdog.png";
    private String playerImageMan = "src/main/resources/graphic/skins/Skin Man.png";
    private String playerImageManStreched = "src/main/resources/graphic/skins/Skin ManStretched.png";


    public static int velocity;
    private double gravityConstant = 981;
    private double verticalVeloctiy = 0;

    private boolean touchedGrass = false;
    private double cooldown = 0;

    public double degree = 0;

    private boolean cooldowntimerboolean = false;

    private int right;
    private int left;
    private int jump;
    private int shoot;
    public int healthbarwidth;
    public int healthbarx;
    public int health;
    public boolean playerWeapon = false;
    public boolean selectSniper;
    public boolean selectScar;
    public boolean selectBlackbear;
    public boolean selectHotdog;
    public boolean selectMan;
    public boolean selectManStretched;
    public static boolean sniper = false;
    public static boolean scar = false;
    private double maxSniperCooldown = 1;
    private double maxScarCooldown = 0.15;
    private int floor;
    private int wall;
    private int stair;
    private Player target;
    private BufferedImage weapon;

    private ArrayList<Build> allBuildings;

    public ViewController viewController;
    public ProgramController programController;

    private int direction;
    private double buildCooldown;
    public boolean isOnStair;
    public double yMax;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean moveDown = true;

    public Player(double x, double y, double width, double height, int healthbarwidth, int healthbarx, int health, ViewController viewController, int right, int left, int jump, int shoot, ArrayList<Build> allBuildings, boolean selectScar, boolean selectSniper, boolean selectBlackbear, boolean selectHotdog, boolean selectMan, boolean selectManStretched, int floor, int wall, int stair) {
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
        this.allBuildings = allBuildings;
        this.selectScar = selectScar;
        this.selectSniper = selectSniper;
        this.selectBlackbear = selectBlackbear;
        this.selectHotdog = selectHotdog;
        this.selectMan = selectMan;
        this.selectManStretched = selectManStretched;
        this.floor = floor;
        this.wall = wall;
        this.stair = stair;
        yMax = viewController.getDrawFrame().getHeight();
        //this.playerWeapon = playerWeapon;
    }

    public void draw(DrawTool drawTool) {
        //drawTool.setCurrentColor(new Color(225, 209, 209));
        //drawTool.drawFilledRectangle(x, y, this.getWidth(), this.getHeight());
        //drawTool.setCurrentColor(new Color(0, 0, 0));

        drawTool.setCurrentColor(new Color(185, 0, 0));
        drawTool.drawFilledRectangle(healthbarx, 10, healthbarwidth, 30);

        if (selectBlackbear){
            setNewImage(playerImageBlackbear);
            drawTool.drawTransformedImage(getMyImage(), x, y + 15, 0, 1);
        } else if (selectHotdog) {
            setNewImage(playerImageHotdog);
            drawTool.drawTransformedImage(getMyImage(), x, y + 15, 0, 1);
        } else if (selectMan) {
            setNewImage(playerImageMan);
            drawTool.drawTransformedImage(getMyImage(), x, y + 15, 0, 1);
        } else if (selectManStretched) {
            setNewImage(playerImageManStreched);
            drawTool.drawTransformedImage(getMyImage(), x, y + 15, 0, 1);
        }

        if (selectScar) {
            setWeaponImage(playerImageScar);
            drawTool.drawTransformedImage(weapon, x + this.width / 2, y + this.height / 2, degree, 3);
            scar = true;
            sniper = false;
        } else {
            scar = false;
        }
        if (selectSniper) {
            setWeaponImage(playerImageSniper);
            drawTool.drawTransformedImage(weapon, x + width / 2, y + height / 2, degree, 3);
            sniper = true;
            scar = false;
        } else {
            sniper = false;
        }
        //drawTool.drawLine(x+width,0,x+width,1080);
        //drawTool.drawLine(0,y,1920,y);
    }

    public void update(double dt) {
        cooldown -= dt;
        buildCooldown -= dt;
        double buildY;
        for (Build build: allBuildings) {
            if (build instanceof Wall) {
                if (x+30 < build.getX() && (x + width) >= build.getX() && y + height-20 > build.getY()) {
                    x = build.getX() - width-1;
                } else if (x+30 > build.getX() && x < build.getX() + 10 && y + height-20 > build.getY()) {
                    x = build.getX() + 10 ;
                }
            }
        }
        if(y >= viewController.getDrawFrame().getHeight()-height){
            y = viewController.getDrawFrame().getHeight() - height ;
            moveDown = false;
            touchedGrass = true;
            verticalVeloctiy = 0;
        }else {
            moveDown = true;
        }

        //boolean collidesWithBuild = false;
        //boolean standsOnTop = false;
        /*
        for (Build build: allBuildings) {
            if((x+width) > build.getX() && (build.getX() + build.getWidth()) > x){
                System.out.println("Ist in richtiger collum");
                if(y+height < build.getY()){
                    y = build.getY() - height +1;
                    collidesWithBuild = true;
                    moveDown = false;
                }
            }
        }*/
        if(moveDown){
            y += verticalVeloctiy*dt;
            verticalVeloctiy += gravityConstant *dt;
        }
        if(touchedGrass){
            cooldowntimerboolean = false;
            if(ViewController.isKeyDown(jump)) {
                cooldowntimerboolean = true;
                touchedGrass = false;
            }else{
                moveUp = false;
            }
        }
        if(moveLeft){
            x -= velocity*dt;
            direction = -1;
        }
        if(moveRight){
            x += velocity*dt;
            direction = 1;
        }
        //if(moveUp){
        //    y -= velocity*dt;
        //}
        if (ViewController.isKeyDown(right)) {
            moveRight = true;
        }else{
            moveRight = false;
        }
        if (ViewController.isKeyDown(left)) {
            moveLeft = true;
        }else{
            moveLeft = false;
        }
        if(ViewController.isKeyDown(jump)) {
            cooldowntimerboolean = true;
        }
        if(cooldowntimerboolean){
            moveUp = true;
        }
        /*
        double playerY = 0;
        double buildX ;
        for (Build build : allBuildings) {
            buildX = build.getX();
            if (build.collidesWithPlayer(this)) {
                collidesWithBuild = true;
                buildY = build.getY();
                if (build instanceof Floor) {
                    if (y  > build.getY()) {
                        playerY = build.getY() + 15;
                    } else {
                        playerY = build.getY() - height;
                    }
                } else if (build instanceof Stair) {
                    Stair stair = (Stair) build;
                    buildY = stair.getPlayerY(this);
                    playerY = buildY;
                }
            }
            if (build instanceof Wall) {
                if (x+30 < buildX && (x + width) >= buildX) {
                    x = buildX - width-1;
                    playerY = y;
                } else if (x+30 > buildX && x < buildX + 10) {
                    x = buildX + 10 ;
                    playerY = y;
                }
            }
        }
        */
        /*
        if(y + height >= viewController.getDrawFrame().getHeight()){
            touchedGrass = true;
            y = viewController.getDrawFrame().getHeight() - height;
            cooldowntimerboolean = false;
        }
        if (y < viewController.getDrawFrame().getHeight() - height && !collidesWithBuild) {
            y += verticalVeloctiy * dt;
            verticalVeloctiy += gravityConstant * dt;
            touchedGrass = false;
        }*/
        /*
        for (Build build: allBuildings) {
            if(CollisionDetector.rectRect(this,build)){
                collidesWithBuild = true;
            }
            if(CollisionDetector.standsOnTop(this,build)){
                //standsOnTop = true;
                touchedGrass = true;
                cooldowntimerboolean = false;
            }
            if(x + width > build.getX() && x< build.getX()+ build.getWidth() && !(y+height  > build.getY())){
                y = build.getY() - height;
                moveDown = false;
            }
        }*/
        /*
        if (ViewController.isKeyDown(right)) { // d
            moveRight = true;
            //direction = 1;
        }else{
            moveRight = false;
        }
        if (ViewController.isKeyDown(left)) { // a
            moveLeft = true;
            //direction = -1;
        }else{
            moveLeft = false;
        }*/
        /*
        buildY = y;
        if (touchedGrass) {
            verticalVeloctiy = 0;
            if (ViewController.isKeyDown(jump)) { // Leertaste
                if (!collidesWithBuild || y <= buildY - height + 10) {

                }
                cooldowntimerboolean = true;
            }
        }*/
        if (cooldowntimerboolean) {
            y -= velocity * dt;
        }
        if (ViewController.isKeyDown(shoot) && cooldown < 0) {
            if (scar) {
                cooldown = maxScarCooldown;
            } else {
                cooldown = maxSniperCooldown;
            }

            shoot();
        }
        //buildCooldown -= dt;
        /*if (ViewController.isKeyDown(KeyCode.ENTER.getCode()) && buildCooldown <= 0 && touchedGrass) {


            double bX,bY;
            double relativeX;
            double relativeY = y + height;
            if(relativeY % 150 <= 10 || relativeY % viewController.getDrawFrame().getHeight() <= 10){
                bY = relativeY + 150 - (relativeY % 150);
            }else{
                bY = relativeY - (relativeY % 150);
            }
            if(direction == 1){
                relativeX = x + width;
                bX = relativeX + 150 - (relativeX % 150);
            }else{
                relativeX = x;
                bX = relativeX - (relativeX % 150);
            }
            Build build = new Stair(bX,bY,direction);
            buildCooldown = 10;
            allBuildings.add(build);
            viewController.draw(build);
            build.buildhealth = 50;
        }

        if(ViewController.isKeyDown(KeyCode.X.getCode()) && buildCooldown <= 0 && touchedGrass){
            double bX,bY;
            double relativeX;
            double relativeY = y + height;
            if(relativeY % 150 <= 10 || relativeY % viewController.getDrawFrame().getHeight() <= 10){
                bY = relativeY - (relativeY % 150);
            }else{
                bY = relativeY - (relativeY % 150);
            }
            if(direction == 1){
                relativeX = x + width/2;
                bX = relativeX + 150 - (relativeX % 150);
            }else{
                relativeX = x + width/2;
                bX = relativeX - (relativeX % 150) - 150;
            }
            Build build = new Floor(bX,bY);
            buildCooldown = 10;
            allBuildings.add(build);
            viewController.draw(build);
            build.buildhealth = 50;
        }
        */
        if(ViewController.isKeyDown(wall) && buildCooldown <= 0){
            Build build = new Wall((150-((x+width)%150))+x+width,y+90);
            buildCooldown = 2.5;
            allBuildings.add(build);
            viewController.draw(build);
            build.buildhealth = 20;
        }
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public void shoot() {
        if (target == null) {
            return;
        }

        double magnitude = Math.sqrt((target.getX() - x) * (target.getX() - x) + (target.getY() - y) * (target.getY() - y));

        double normX = (target.getX() - x) / magnitude;
        double normY = (target.getY() - y) / magnitude;

        double weaponlength = weapon.getWidth();
        double weaponheight = weapon.getHeight();

        degree = (-Math.atan2(target.getX() - x, target.getY() - y)) * 57.296 + 90;
        if (degree >= 90 && degree <= 270) {
            playerImageScar = "src/main/resources/graphic/weapons/Scarreversed.png";
            playerImageSniper = "src/main/resources/graphic/weapons/Sniper.png";
        } else {
            playerImageScar = "src/main/resources/graphic/weapons/Scar.png";
            playerImageSniper = "src/main/resources/graphic/weapons/Sniper.png";
        }

        Shot shot = new Shot(x + width / 2 + normX * weaponlength, y + height / 2 + normY * weaponheight, normX, normY, 7, this, target, viewController);
        Variable_Container.shots.add(shot);
        viewController.draw(shot, 1);
    }
    private void setWeaponImage(String pathToImage){
        try {
            weapon = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            if (Config.INFO_MESSAGES) System.out.println("Laden eines Bildes fehlgeschlagen: " + pathToImage);
        }
    }
    public void restartGame(){
        selectBlackbear = false;
        selectHotdog = false;
        selectMan = false;
        selectManStretched = false;
        selectScar = true;
        selectSniper = false;
        healthbarwidth = 200;
    }
    public void destroyBuild(Build build){
        viewController.removeDrawable(build);
        allBuildings.remove(build);
    }
    public ArrayList<Build> getAllBuildings(){
        return allBuildings;
    }
}