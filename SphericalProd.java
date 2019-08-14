public class SphericalProd extends RegularProduct
{
    public SphericalProd(double size, double weight)
    {
        super(null, size, size, size, weight);
    }

    @Override
    public String getItemType()
    {
        return "Spherical Product";
    }

    public double getDiameter()
    {
        return getWidth();
    }

    public double getRadius()
    {
        return getDiameter() / 2.0;
    }
}
