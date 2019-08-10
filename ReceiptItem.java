import packer.Dimension;

public class ReceiptItem implements ReceiptEntry
{
    private final String description;
    private final double price;

    public ReceiptItem(String description, double price)
    {
        this.description = description;
        this.price = price;
    }

    public static ReceiptItem chargeForItem(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("item is null");

        String description = null;
        double price = 0.0;
        if (item instanceof Document)
        {
            Document d = (Document)item;
            description = String.format("Document (%.2f\" x %.2f\", %d pages)", d.getWidth(), d.getLength(), d.getPages());
            price = d.getWeight() * 40.0;
        }
        else if (item instanceof RegularProduct)
        {
            RegularProduct p = (RegularProduct)item;
            description = String.format("Reg. Prod. (%.2f\" x %.2f\" x %.2f, %.2f kg)", p.getWidth(), p.getHeight(), p.getLength(), p.getWeight());
            price = p.getWeight() * 40.0;
        }
        else if (item instanceof IrregularProduct)
        {
            IrregularProduct p = (IrregularProduct)item;
            description = String.format("Reg. Prod. (%.2f\" x %.2f\" x %.2f, %.2f kg)", p.getWidth(), p.getHeight(), p.getLength(), p.getWeight());
            double byWeight = 40.0 * p.getWeight();
            double byVolume = 30.0 * p.getVolume() / 305.0;
            price = Math.max(byWeight, byVolume);
        }
        else
        {
            throw new IllegalArgumentException("item is unsupported");
        }

        return new ReceiptItem(description, price);
    }

    public static ReceiptItem chargeForRegion(String region, double weight, double volume)
    {
        switch (region)
        {
        case "METRO MANILA": return new ReceiptItem("Delivery fee", 50.0);
        case "LUZON": return new ReceiptItem("Delivery fee", 100.0);
        case "VISAYAS":
            return new ReceiptItem("Delivery fee", 1000.0 + Math.max(weight, volume) * 0.1);
        case "MINDANAO":
            return new ReceiptItem("Delivery fee", 3000.0 + Math.max(weight, volume) * 0.25);
        default: throw new IllegalArgumentException("unknown region");
        }
    }

    public static ReceiptItem chargeForInsurance(int itemCount)
    {
        return new ReceiptItem("Insurance", itemCount * 5);
    }

    public static ReceiptItem chargeForParcel(String parcelType, Dimension dimensions)
    {
        if (parcelType.equals("FLT"))
        {
            return new ReceiptItem(String.format("Flat parcel, %s", dimensions.getVolume() < 200.0 ? "small" : "big"),
                dimensions.getVolume() < 200.0 ? 30.0 : 50.0);
        }
        else
        {
            return null;
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
