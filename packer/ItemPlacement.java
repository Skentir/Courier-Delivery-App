package packer;

public class ItemPlacement {
    private final Item item;
    private final Space placement;
    private final double weight;

    public ItemPlacement(Item item, Space placement) {
        this.item = item;
        this.placement = placement;
        this.weight = item.getWeight();
    }

    public Item getItem() {
        return item;
    }

    public Space getPlacement() {
        return placement;
    }

    public double getWeight() {
        return weight;
    }
}
