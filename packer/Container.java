package packer;

public class Container {
    private final Dimension dimensions;
    private final double maxWeight;

    public Container(double width, double height, double length, double maxWeight) {
      this(new Dimension(width, height, length), maxWeight);
    }

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
