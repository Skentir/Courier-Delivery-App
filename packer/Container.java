package packer;

public class Container {
    private final Dimension dimensions;
    private final double maxWeight;

    public Container(Dimension dimensions, double maxWeight) {
        this.dimensions = dimensions;
        this.maxWeight = maxWeight;
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
}
