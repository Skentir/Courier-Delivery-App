package packer;

/**
 * Represents a 3D point inside a container.
 */
public class Point
{
  public static final Point ZERO = new Point(0.0, 0.0, 0.0);

  private final double x;
  private final double y;
  private final double z;

  /**
   * Constructs a new point.
   *
   * @param x the x location of the point relative to the lower-left corner
   * @param y the y location of the point relative to the lower-left corner
   * @param z the z location of the point relative to the lower-left corner
   */
  public Point(double x, double y, double z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double getX()
  {
    return x;
  }

  public double getY()
  {
    return y;
  }

  public double getZ()
  {
    return z;
  }

  /**
   * Returns a string representing the point.
   *
   * @return a three-component string representing the point.
   */
  @Override
  public String toString()
  {
    return String.format("(%.2f, %.2f, %.2f)", x, y, z);
  }
}
