package packer;

public class Container
{
  private final Dimension dimensions;
  private final double maxWeight;
  private final String type;

  /**
   * Constructs a container.
   *
   * @param width the width of the container
   * @param height the height of the container
   * @param length the length of the container
   * @param maxWeight the maximum weight that the container can accommodate
   * @param type the type name used by the container
   * @param id the unique identifier
   */
  public Container(double width, double height, double length, double maxWeight, String type, int id)
  {
    this(new Dimension(width, height, length), maxWeight, type + id);
  }

  /**
   * Constructs a container.
   *
   * @param width the width of the container
   * @param height the height of the container
   * @param length the length of the container
   * @param maxWeight the maximum weight that the container can accommodate
   * @param type the type name used by the container
   */
  public Container(double width, double height, double length, double maxWeight, String type)
  {
    this(new Dimension(width, height, length), maxWeight, type);
  }

  /**
   * Constructs a container.
   *
   * @param dimensions the dimensions of the container
   * @param maxWeight the maximum weight that the container can accommodate
   * @param type the type name used by the container
   */
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

  /**
   * Formats the container into a string.
   */
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
