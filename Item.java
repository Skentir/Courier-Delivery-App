/**
 * Class to represent an item that can be placed inside a parcel.
 */
public abstract class Item
{
  private final double length;
  private final double width;

  protected Item(double length, double width)
  {
    if (length <= 0.0)
      length = 1.0;
    if (width <= 0.0)
      width = 1.0;

    this.length = length;
    this.width = width;
  }

  /**
   * Returns the length of the item.
   *
   * @return the length, in inches
   */
  public double getLength()
  {
    return length;
  }

  /**
   * Returns the width of the item.
   *
   * @return the width, in inches
   */
  public double getWidth()
  {
    return width;
  }

  /**
   * Returns the height of the item.
   *
   * @return the height, in inches
   */
  public abstract double getHeight();

  /**
   * Returns the weight of the item.
   *
   * @return the weight, in kilograms, of the item.
   */
  public abstract double getWeight();
}
