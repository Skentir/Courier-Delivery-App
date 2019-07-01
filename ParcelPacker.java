import java.util.Collection;
import packer.Container;
import packer.DefaultPacker;
import packer.Dimension;
import packer.Packing;
import packer.PackItem;

public class ParcelPacker extends DefaultPacker {
  private static final Container[] CONTAINERS = new Container[] {
    new Container(9, 1, 14, 3, Parcel.FLAT),
    new Container(12, 3, 18, 3, Parcel.FLAT),
    new Container(12, 10, 5, 1000, Parcel.BOX),
    new Container(14, 11, 7, 1000, Parcel.BOX),
    new Container(18, 12, 9, 1000, Parcel.BOX),
    new Container(20, 16, 12, 1000, Parcel.BOX)
  };

  public boolean pack(Parcel parcel, Item[] items) {
    Container candidate = null;
    for (Container container : CONTAINERS) {
      PackItem[] packItems = new PackItem[items.length];
      for (int i = 0; i < items.length; i++) {
        Item item = items[i];
        Dimension dim = new Dimension(item.getWidth(), item.getHeight(), item.getLength());
        double weight = item.getWeight();
        packItems[i] = new PackItem(dim, weight);
      }

      Collection<Packing> packings = pack(container, packItems);
      if (packings == null)
        continue;

      if (packings.size() == 1) {
        candidate = container;
        break;
      }
    }

    if (candidate != null) {
      parcel.setDimensions(candidate.getDimensions());
      parcel.setParcelType(candidate.getType());
      return true;
    }

    return false;
  }
}
