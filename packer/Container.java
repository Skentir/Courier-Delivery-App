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
}
