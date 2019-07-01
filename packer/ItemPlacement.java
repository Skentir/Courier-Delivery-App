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

  public PackItem getItem()
  {
    return item;
  }

  public Space getPlacement()
  {
    return placement;
  }

  public double getWeight()
  {
    return weight;
  }
}
