package packer;

import java.util.Collection;

/**
 * Allows subclasses to perform packing algorithms, or packing items into 3D
 * containers.
 */
public interface Packer
{
  /**
   * Packs all the items into containers. Typically the number of packages used
   * is minimized. If the items cannot fit inside a package, then another
   * package is used.
   *
   * @param container the container to use
   * @param items the items to be packed
   *
   * @return packages containing all the items
  Collection<Packing> pack(Container container, PackItem[] items);
}
