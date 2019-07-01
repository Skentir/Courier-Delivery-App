package packer;

public class Container
{
  private final Dimension dimensions;
  private final double maxWeight;
  private final String type;

  public Container(double width, double height, double length, double maxWeight, String type, int index)
  {
    this(new Dimension(width, height, length), maxWeight, type + index);
  }

  public Container(double width, double height, double length, double maxWeight, String type)
  {
    this(new Dimension(width, height, length), maxWeight, type);
  }

  public Container(Dimension dimensions, double maxWeight, String type)
  {
    this.dimensions = dimensions;
    this.maxWeight = maxWeight;
    this.type = type;
  }

  public Dimension getDimensions()
  {
    return dimensions;
  }

  public double getMaxWeight()
  {
    return maxWeight;
  }

  public String getType()
  {
    return type;
  }

  @Override
  public String toString()
  {
    String type;
    switch (this.type.substring(0, 3))
    {
      case "FLT": type = "Flat"; break;
      case "BOX": type = "Box"; break;
      default: type = "Unknown"; break;
    }

    return String.format("%s, %.2f' x %.2f' x %.2f'", type,
      dimensions.getWidth(),
      dimensions.getHeight(),
      dimensions.getLength());
  }
}
