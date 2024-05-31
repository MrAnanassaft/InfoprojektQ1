package my_project.model;

import KAGO_framework.Config;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import javafx.scene.input.KeyCode;
import my_project.control.ProgramController;
import my_project.model.Buildings.Build;
import my_project.model.Buildings.Floor;
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
    private BufferedImage weapon;

    private ArrayList<Build> allBuildings;

    public ViewController viewController;
    public ProgramController programController;

    public Player(double x, double y, double width, double height, int healthbarwidth, int healthbarx, int health, ViewController viewController, int right, int left, int jump, int shoot, ArrayList<Build> allBuildings) {
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
    }

    public void draw(DrawTool drawTool) {
        /*drawTool.setCurrentColor(new Color(225, 209, 209));
        drawTool.drawFilledRectangle(x, y, this.getWidth(), this.getHeight());
        drawTool.setCurrentColor(new Color(0, 0, 0));*/

        drawTool.setCurrentColor(new Color(185, 0, 0));
        drawTool.drawFilledRectangle(healthbarx, 10, healthbarwidth, 30);

        if (programController.selectScar) {
            setNewImage(playerImageScar);
            drawTool.drawTransformedImage(getMyImage(), x + width / 2, y + height / 2, degree, 3);
            scar = true;
        } else {
            scar = false;
        }
        if (programController.selectSniper) {
            setNewImage(playerImageSniper);
            drawTool.drawTransformedImage(getMyImage(), x + width / 2, y + height / 2, degree, 3);
            sniper = true;
        } else {
            sniper = false;
        }
    }

    public void update(double dt) {
        cooldown -= dt;
        boolean collidesWithBuild = false;
        double playerY = 0;
        double buildY = 0;
        for (Build build : allBuildings) {
            if (build.colidesWithPlayer(this)) {
                collidesWithBuild = true;
                buildY = build.getY();
                if (build instanceof Floor) {
                    if (y > build.getY()) {
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
        }

        if (y < viewController.getDrawFrame().getHeight() - height && !collidesWithBuild) {
            y += verticalVeloctiy * dt;
            verticalVeloctiy += gravityConstant * dt;
            touchedGrass = false;
        } else {
            verticalVeloctiy = 0;
            if (playerY != 0) {
                y = playerY;
            } else {
                y = viewController.getDrawFrame().getHeight() - height;
            }
            touchedGrass = true;
            cooldowntimerboolean = false;
        }

        if (ViewController.isKeyDown(right)) { // d
            x += velocity * dt;
        }
        if (ViewController.isKeyDown(left)) { // a
            x -= velocity * dt;
        }
        if (touchedGrass) {
            if (ViewController.isKeyDown(jump)) { // Leertaste
                if (!collidesWithBuild || y <= buildY - height + 10) {
                    cooldowntimerboolean = true;
                }
            }
        }
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
        if (ViewController.isKeyDown(KeyCode.ENTER.getCode())) {
            Build build = new Stair(x + 150 - (x % 150), y + 150 - (y % 150),Stair.RIGHT);
            allBuildings.add(build);
            viewController.draw(build);
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
}
