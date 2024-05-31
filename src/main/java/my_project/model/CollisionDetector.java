package my_project.model;

public class CollisionDetector {
    public static boolean circleWithRectangle(double cx, double cy, double radius, double rx, double ry, double rw, double rh){
        // temporary variables to set edges for testing
        double testX = cx;
        double testY = cy;

        // which edge is closest?
        if (cx < rx)         testX = rx;      // test left edge
        else if (cx > rx+rw) testX = rx+rw;   // right edge
        if (cy < ry)         testY = ry;      // top edge
        else if (cy > ry+rh) testY = ry+rh;   // bottom edge

        // get distance from closes edges
        double distX = cx-testX;
        double distY = cy-testY;
        double distance = Math.sqrt( (distX*distX) + (distY*distY) );

        // if the distance is less than the radius, collision!
        if (distance <= radius) {
            return true;
        }
        return false;
    }
    public static boolean lineRect(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {

        // check if the line has hit any of the rectangle's sides
        // uses the Line/Line function below
        boolean left =   lineLine(x1,y1,x2,y2, rx,ry,rx, ry+rh);
        boolean right =  lineLine(x1,y1,x2,y2, rx+rw,ry, rx+rw,ry+rh);
        boolean top =    lineLine(x1,y1,x2,y2, rx,ry, rx+rw,ry);
        boolean bottom = lineLine(x1,y1,x2,y2, rx,ry+rh, rx+rw,ry+rh);

        // if ANY of the above are true, the line
        // has hit the rectangle
        return left || right || top || bottom;
    }


    // LINE/LINE
    public static boolean lineLine(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {

        // calculate the direction of the lines
        double uA = ((x4-x3)*(y1-y3) - (y4-y3)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
        double uB = ((x2-x1)*(y1-y3) - (y2-y1)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));

        // if uA and uB are between 0-1, lines are colliding
        return uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1;
    }
}
