package packer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultPacker implements Packer {
    @Override
    public Collection<Packing> pack(Container container, PackItem[] items) {
        List<Packing> packings = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            if (items[i].getWeight() > container.getMaxWeight())
                continue;

            boolean isPlaced = false;
            for (int j = 0; j < packings.size(); j++) {
                Packing packing = packings.get(j);
                if (packing.placeItem(items[i])) {
                    isPlaced = true;
                    break;
                }
            }

            if (!isPlaced) {
                Packing packing = new Packing(container);
                if (!packing.placeItem(items[i])) {
                    throw new RuntimeException("Item cannot be packed");
                }

                packings.add(packing);
            }
        }

        return packings;
    }
}
