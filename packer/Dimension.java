/**
* @author Kirsten Sison and Marc Tiburcio
* CCPROG3 S12A - Nathalie Lim Cheng
*/
package packer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the volume occupied by a cuboid.
 */
public class Dimension
{
  private final double width;
  private final double height;
  private final double length;

  /**
   * Constructs a new dimension.
   *
   * @param width the width in inches
   * @param height the height in inches
   * @param length the length in inches
   */
  public Dimension(double width, double height, double length)
  {
    this.width = width;
    this.height = height;
    this.length = length;
  }

  /**
   * Returns a new dimension such that the width has the largest measure,
   * followed by the height and the length.
   *
   * @return a reoriented dimension
   */
  public Dimension rotateDecreasing()
  {
    List<Double> dimensions = new ArrayList<>();
    dimensions.add(width);
    dimensions.add(height);
    dimensions.add(length);

    dimensions.sort(Double::compare);
    return new Dimension(dimensions.get(2), dimensions.get(1), dimensions.get(0));
  }

  /**
   * Gets the width of the volume.
   *
   * @return the width, in inches
   */
  public double getWidth()
  {
    return width;
  }

  /**
   * Gets the height of the volume.
   *
   * @return the height, in inches
   */
  public double getHeight()
  {
    return height;
  }

  /**
   * Gets the length of the volume.
   *
   * @return the length, in inches
   */
  public double getLength()
  {
    return length;
  }

  /**
   * Gets the volume of the cuboid.
   *
   * @return the volume in cubic inches (in^3)
   */
  public double getVolume()
  {
    return width * height * length;
  }

  /**
   * Checks if the dimension has zero volume.
   *
   * @return true if the volume is zero, false otherwise
   */
  public boolean isEmpty()
  {
    return getVolume() == 0.0;
  }

  /**
   * Returns a string that represents the volume.
   *
   * @return a string
   */
  @Override
  public String toString()
  {
    return String.format("[%.2f, %.2f, %.2f]", width, height, length);
  }
}
