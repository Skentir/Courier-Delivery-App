package packer;

public class ItemPlacement
{
  private final PackItem item;
  private final Space placement;
  private final double weight;

  public ItemPlacement(PackItem item, Space placement)
  {
    this.item = item;
    this.placement = placement;
    this.weight = item.getWeight();
  }
  /**
   * Returns item inside the container
   *
   * @return a PackItem
   */
  public PackItem getItem()
  {
    return item;
  }
  /**
   * Returns placement of item
   *
   * @return a Space placement
   */
  public Space getPlacement()
  {
    return placement;
  }
  /**
   * Returns weight of item
   *
   * @return a double value of weight
   */
  public double getWeight()
  {
    return weight;
  }
}
