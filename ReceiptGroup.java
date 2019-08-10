import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ReceiptGroup implements ReceiptEntry
{
    private final String name;
    private final List<ReceiptEntry> entries;

    public ReceiptGroup(String name, Collection<ReceiptEntry> entries)
    {
        this.name = name;
        this.entries = new ArrayList<>(entries);
    }

    public ReceiptGroup(String name, ReceiptEntry... entries)
    {
        this.name = name;
        this.entries = Arrays.asList(entries);
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

    @Override
    public String toString()
    {
        String[] e = new String[entries.size()];
        for (int i = 0; i < entries.size(); i++)
            e[i] = entries.get(i).toString();
        return String.format("P% 10.2f - %s\n%s", getPrice(), name, String.join("\n", e));
    }
}
