/**
 * Represents an irregularly shaped product whose dimensions are its maximum
 * extents.
 */
public class IrregularProduct extends Item
{
  private final double height;
  private final double weight;

  /**
   * Constructs an irregular product.
   *
   * @param name the name of the item
   * @param length the maximum length measure of the item
   * @param width the maximum width measure of the item
   * @param height the maximum height measure of the item
   * @param weight the weight of the item
   */
  public IrregularProduct(String name, double length, double width, double height, double weight)
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
   * @return the type, in this case, Irregular Product
   */
  @Override
  public String getItemType()
  {
    return "Irregular Product";
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
