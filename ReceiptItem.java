public class ReceiptItem implements ReceiptEntry
{
    private final String description;
    private final double price;

    public ReceiptItem(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("item is null");

        if (item instanceof Document)
        {
            Document d = (Document)item;
            description = String.format("Document (%.2f\" x %.2f\", %d pages)", d.getWidth(), d.getLength(), d.getPages());
            price = d.getWeight() * 40.0;
        }
        else if (item instanceof RegularProduct)
        {
            RegularProduct p = (RegularProduct)item;
            description = String.format("Reg. Prod. (%.2f\" x %.2f\" x %.2f, %.2f kg)", p.getWidth(), p.getHeight(), p.getLength(), d.getWeight());
            price = d.getWeight() * 40.0;
        }
        else if (item instanceof RegularProduct)
        {
            RegularProduct p = (RegularProduct)item;
            description = String.format("Reg. Prod. (%.2f\" x %.2f\" x %.2f, %.2f kg)", p.getWidth(), p.getHeight(), p.getLength(), d.getWeight());
            double byWeight = 40.0 * d.getWeight();
            double byVolume = 30.0 * d.getVolume() / 305.0;
            price = Math.max(byWeight, byVolume);
        }
        else
        {
            throw new IllegalArgumentException("item is unsupported");
        }
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public double getPrice()
    {
        return price;
    }
}
