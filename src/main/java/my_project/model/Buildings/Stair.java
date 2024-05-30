package my_project.model.Buildings;

import my_project.model.Player;

public class Stair extends Build {
    public Stair(double x, double y) {
        super(x, y);
    }

    @Override
    public boolean colidesWithPlayer(Player player) {
        return player.collidesWith(this);
    }
}
