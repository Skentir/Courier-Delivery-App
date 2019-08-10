import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Receipt
{
    private List<ReceiptEntry> entries;

    public Receipt(Collection<ReceiptEntry> entries)
    {
        this.entries = new ArrayList<>(entries);
    }

    public Receipt(ReceiptEntry... entries)
    {
        this.entries = Arrays.asList(entries);
    }

    public List<ReceiptEntry> getEntries()
    {
        return this.entries;
    }
}
