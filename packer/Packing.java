/**
* @author Kirsten Sison and Marc Tiburcio
* CCPROG3 S12A - Nathalie Lim Cheng
*/
package packer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the packing configuration of items inside a container. The code
 * is inspired from this repository: https://github.com/mushishi78/box_packer.
 */
public class Packing
{
  private List<PackItem> itemsPlaced;

  private List<ItemPlacement> itemPlacements;
  private List<Space> freeSpaces;
  private double totalWeight;

  private final double maxWeight;

  /**
   * Constructs an empty packing whose free space is all the space in the
   * container.
   */
  public Packing(Container container)
  {
    this.maxWeight = container.getMaxWeight();
    this.itemPlacements = new ArrayList<>();
    this.freeSpaces = new ArrayList<>();
    this.itemsPlaced = new ArrayList<>();

    addSpace(container.getDimensions(), Point.ZERO);
  }

  /**
   * Gets all the placement of items inside the container.
   *
   * @return configuration of placements inside the container.
   */
  public Iterable<ItemPlacement> getItemPlacements()
  {
    return itemPlacements;
  }

  /**
   * Gets all the free and usable spaces inside the container.
   *
   * @return free spaces inside the container
   */
  public Iterable<Space> getSpaces()
  {
    return freeSpaces;
  }

  /**
   * Adds a new free space. Only used when creating the packing object instance.
   * As such, there will be no error checking.
   *
   * @param placement the size of the free space
   * @param position the location of the free space inside the box
   */
  private void addSpace(Dimension placement, Point position)
  {
    Space space = new Space(placement, position);
    freeSpaces.add(space);
  }

  /**
   * Attempts to place an item into the container. It makes sure that the
   * container won't be too heavy and there is free space available for the
   * item.
   *
   * @param item the item to be placed inside the container
   *
   * @return true if the item was successfully placed, false otherwise
   */
  public boolean placeItem(PackItem item)
  {
    // no need to place an item that was already placed
    if (itemsPlaced.contains(item))
    {
      return false;
    }

    // reject if the item would result in a too heavy container
    if (totalWeight + item.getWeight() > maxWeight)
    {
      return false;
    }

    boolean isPacked = false;
    Space selected = null;
    ItemPlacement placement = null;
    for (int i = 0; i < freeSpaces.size(); i++)
    {
      Space space = freeSpaces.get(i);
      placement = place(item, space);
      if (placement != null)
      {
        selected = space;
        break;
      }
    }

    if (selected != null)
    {
      itemPlacements.add(placement);
      totalWeight += item.getWeight();
      freeSpaces.remove(selected);
      freeSpaces.addAll(selected.split(placement));
      isPacked = true;
    }

    return isPacked;
  }

  /**
   * Attempts to place an item into the given space.
   *
   * @param item the item to be placed
   * @param space the space where the item is to be placed.
   *
   * @return a new placement, or null if it can't fit
   */
  private ItemPlacement place(PackItem item, Space space)
  {
    Dimension placement = item.getDimensions().rotateDecreasing();

    Dimension[] permutations = new Dimension[]
    {
      new Dimension(placement.getWidth(), placement.getHeight(), placement.getLength()),
      new Dimension(placement.getWidth(), placement.getLength(), placement.getHeight()),
      new Dimension(placement.getHeight(), placement.getWidth(), placement.getLength()),
      new Dimension(placement.getHeight(), placement.getLength(), placement.getWidth()),
      new Dimension(placement.getLength(), placement.getWidth(), placement.getHeight()),
      new Dimension(placement.getLength(), placement.getHeight(), placement.getWidth())
    };

    Dimension dim = space.getDimensions();
    for (Dimension perm : permutations)
    {
      if (perm.getWidth() <= dim.getWidth() &&
          perm.getHeight() <= dim.getHeight() &&
          perm.getLength() <= dim.getLength())
          return new ItemPlacement(item, new Space(perm, space.getPosition()));
    }

    return null;
  }
}
