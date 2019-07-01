package packer;

import java.util.ArrayList;
import java.util.List;

public class Packing {
    private List<PackItem> itemsPlaced;

    private List<ItemPlacement> itemPlacements;
    private List<Space> freeSpaces;
    private double totalWeight;

    private final double maxWeight;

    public Packing(Container container) {
        this.maxWeight = container.getMaxWeight();
        this.itemPlacements = new ArrayList<>();
        this.freeSpaces = new ArrayList<>();
        this.itemsPlaced = new ArrayList<>();

        addSpace(container.getDimensions(), Point.ZERO);
    }

    public Iterable<ItemPlacement> getItemPlacements() {
        return itemPlacements;
    }

    public Iterable<Space> getSpaces() {
        return freeSpaces;
    }

    public void addSpace(Dimension placement, Point position) {
        Space space = new Space(placement, position);
        freeSpaces.add(space);
    }

    public boolean placeItem(PackItem item) {
        // no need to place an item that was already placed
        if (itemsPlaced.contains(item)) {
            return false;
        }

        // reject if the item would result in a too heavy container
        if (totalWeight + item.getWeight() > maxWeight) {
            return false;
        }

        boolean isPacked = false;
        Space selected = null;
        ItemPlacement placement = null;
        for (int i = 0; i < freeSpaces.size(); i++) {
            Space space = freeSpaces.get(i);
            placement = place(item, space);
            if (placement != null) {
                selected = space;
                break;
            }
        }

        if (selected != null) {
            itemPlacements.add(placement);
            totalWeight += item.getWeight();
            freeSpaces.remove(selected);
            freeSpaces.addAll(selected.split(placement));
            isPacked = true;
        }

        return isPacked;
    }

    private ItemPlacement place(PackItem item, Space space) {
        Dimension placement = item.getDimensions().rotateDecreasing();

        Dimension[] permutations = new Dimension[] {
            new Dimension(placement.getWidth(), placement.getHeight(), placement.getLength()),
            new Dimension(placement.getWidth(), placement.getLength(), placement.getHeight()),
            new Dimension(placement.getHeight(), placement.getWidth(), placement.getLength()),
            new Dimension(placement.getHeight(), placement.getLength(), placement.getWidth()),
            new Dimension(placement.getLength(), placement.getWidth(), placement.getHeight()),
            new Dimension(placement.getLength(), placement.getHeight(), placement.getWidth())
        };

        Dimension dim = space.getDimensions();
        for (Dimension perm : permutations) {
            if (perm.getWidth() <= dim.getWidth() &&
                perm.getHeight() <= dim.getHeight() &&
                perm.getLength() <= dim.getLength())
                return new ItemPlacement(item, new Space(perm, space.getPosition()));
        }

        return null;
    }
}
