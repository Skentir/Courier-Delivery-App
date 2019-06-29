package packer;

public class Item {
    private final Dimension dimensions;
    private final double weight;

    public Item(Dimension dimensions, double weight) {
        this.dimensions = dimensions;
        this.weight = weight;
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public double getWeight() {
        return weight;
    }
}
