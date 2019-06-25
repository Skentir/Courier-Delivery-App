public class RegularProduct extends Item
{
  private final double height;
  private final double weight;

  public RegularProduct(double length, double width, double height, double weight)
  {
    super(length, width);
    if (height <= 0.0)
      height = 1.0;
    if (weight <= 0.0)
      weight = 1.0;

    this.height = height;
    this.weight = weight;
  }

  @Override
  public double getHeight()
  {
    return height;
  }

  @Override
  public double getWeight()
  {
    return weight;
  }
}
