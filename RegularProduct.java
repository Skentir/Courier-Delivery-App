/**
 * Represents a regular, cuboid (3d figure with a width, length and height)
 * object.
 */
public class RegularProduct extends Item
{
  private final double height;
  private final double weight;

  /**
   * Constructs a regular product.
   *
   * @param name the name of the item
   * @param length the length of the item
   * @param width the width of the item
   * @param height the height of the item
   * @param weight the weight of the item
   */
  public RegularProduct(String name, double length, double width, double height, double weight)
  {
    super(name, length, width);
    if (height <= 0.0)
      height = 1.0;
    if (weight <= 0.0)
      weight = 1.0;

    this.height = height;
    this.weight = weight;
  }

  /**
   * Gets the type of the item.
   *
   * @return the type, in this case, Regular Product
   */
  @Override
  public String getItemType()
  {
    return "Regular Product";
  }

  /**
   * Returns the height of the item.
   *
   * @return the height, in inches
   */
  @Override
  public double getHeight()
  {
    return height;
  }

  /**
   * Returns the weight of the item.
   *
   * @return the weight, in kilograms, of the item.
   */
  @Override
  public double getWeight()
  {
    return weight;
  }
}
