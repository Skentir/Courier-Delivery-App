package packer;

public class Point {
    public static final Point ZERO = new Point(0.0, 0.0, 0.0);

    private final double x;
    private final double y;
    private final double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return String.format("Point[%f, %f, %f]", x, y, z);
    }
}
