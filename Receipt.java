import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Receipt
{
    private Recipient recipient;
    private Date dateGenerated;
    private List<ReceiptEntry> entries;

    public Receipt(Recipient recipient, Collection<ReceiptEntry> entries)
    {
        this.recipient = recipient;
        this.dateGenerated = new Date();
        this.entries = new ArrayList<>(entries);
    }

    public Receipt(Recipient recipient, ReceiptEntry... entries)
    {
        this.recipient = recipient;
        this.dateGenerated = new Date();
        this.entries = Arrays.asList(entries);
    }

    public Date getDateGenerated()
    {
        return dateGenerated;
    }

    public Recipient getRecipient()
    {
        return recipient;
    }

    public List<ReceiptEntry> getEntries()
    {
        return this.entries;
    }
}
