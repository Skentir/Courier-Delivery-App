import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Receipt
{
    private ArrayList<ReceiptEntry> entries;

    public Receipt(Collection<ReceiptEntry> entries)
    {
        this.entries = new ArrayList<>(entries);
    }

    public List<ReceiptEntry> getEntries()
    {
        return this.entries;
    }
}
