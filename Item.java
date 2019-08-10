/**
 * Class to represent an item that can be placed inside a parcel.
 */
public abstract class Item
{
  private final double length;
  private final double width;

  private final String name;

  /**
   * Constructs an item with the given attributes.
   *
   * @param name the name of the item
   * @param length the length of the item
   * @param width the width of the item
   */
  protected Item(String name, double length, double width)
  {
    if (length <= 0.0)
      length = 1.0;
    if (width <= 0.0)
      width = 1.0;

    this.name = name;
    this.length = length;
    this.width = width;
  }

  /**
   * Returns the name given to the item.
   *
   * @return the name of the item
   */
  public String getName()
  {
    return name;
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
   * Returns the volume of the item.
   *
   * @return the volume, in cubic inches, of the item.
   */
  public double getVolume()
  {
    return getWidth() * getHeight() * getLength();
  }

  public abstract String getItemType();

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

  /**
   * Returns a string that represents this item.
   *
   * @return a descriptive string that describes the item, including its name,
   *         type, dimensions and weight
   */
  @Override
  public String toString()
  {
    return String.format("%s (%f' x %f' x %f; @ %f kg)", getItemType(), width, getHeight(), length, getWeight());
  }
}
