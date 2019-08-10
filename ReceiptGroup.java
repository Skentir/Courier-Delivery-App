import java.util.ArrayList;
import java.util.Collection;

public class ReceiptGroup implements ReceiptEntry
{
    private final String name;
    private final ArrayList<ReceiptEntry> entries;

    public ReceiptGroup(String name, Collection<ReceiptEntry> entries)
    {
        this.name = name;
        this.entries = new ArrayList<>(entries);
    }

    @Override
    public String getDescription()
    {
        return name;
    }

    @Override
    public double getPrice()
    {
        double p = 0.0;
        for (ReceiptEntry e : entries)
            p += e.getPrice();
        return p;
    }
}
