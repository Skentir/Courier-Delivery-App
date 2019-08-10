/**
* @author Kirsten Sison and Marc Tiburcio
* CCPROG3 S12A - Nathalie Lim Cheng
*/
package packer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Default implementation of the 3D packing algorithm.
 * Sourced from https://github.com/mushishi78/box_packer and translated into
 * Java.
 */
public class DefaultPacker implements Packer
{
  /**
   * Default implementation of the packing algorithm.
   */
  @Override
  public Collection<Packing> pack(Container container, PackItem[] items)
  {
    List<Packing> packings = new ArrayList<>();

    for (int i = 0; i < items.length; i++)
    {
      if (items[i].getWeight() > container.getMaxWeight())
        continue;

      boolean isPlaced = false;
      for (int j = 0; j < packings.size(); j++)
      {
        Packing packing = packings.get(j);
        if (packing.placeItem(items[i]))
        {
          isPlaced = true;
          break;
        }
      }

      if (!isPlaced)
      {
        Packing packing = new Packing(container);
        if (!packing.placeItem(items[i]))
          return null;

        packings.add(packing);
      }
    }

    if (items == null || items.length == 0)
      packings.add(new Packing(container));

    return packings;
  }
}
