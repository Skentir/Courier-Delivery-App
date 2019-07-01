package packer;

/**
 * Item that can be placed inside a container.
 */
public class PackItem
{
  private final Dimension dimensions;
  private final double weight;

  /**
   * Constructs a new item class with the given size and weight.
   *
   * @param dimensions the size of the item, whose measures are in inches
   * @param weight the weight of the item, in kilos
   */
  public PackItem(Dimension dimensions, double weight)
  {
    this.dimensions = dimensions;
    this.weight = weight;
  }

  /**
   * Gets the dimensions of the item.
   *
   * @return the dimensions, where measures are in inches
   */
  public Dimension getDimensions()
  {
    return dimensions;
  }

  /**
   * Gets the weight of the item.
   *
   * @return the weight in kilos
   */
  public double getWeight()
  {
    return weight;
  }
}
