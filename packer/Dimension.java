package packer;

import java.util.ArrayList;
import java.util.List;

public class Dimension {
    private final double width;
    private final double height;
    private final double length;

    public Dimension(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public Dimension rotateDecreasing() {
        List<Double> dimensions = new ArrayList<>();
        dimensions.add(width);
        dimensions.add(height);
        dimensions.add(length);
        
        dimensions.sort(Double::compare);
        return new Dimension(dimensions.get(2), dimensions.get(1), dimensions.get(0));
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double getVolume() {
        return width * height * length;
    }

    public boolean isEmpty() {
        return getVolume() == 0.0;
    }

    @Override
    public String toString() {
        return String.format("Dimension[%f, %f, %f]", width, height, length);
    }
}
