import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Represents a receipt for a transaction.
 */
public class Receipt
{
    private Recipient recipient;
    private Date dateGenerated;
    private List<ReceiptEntry> entries;

    /**
     * Creates a receipt object.
     *
     * @param recipient the recipient of this receipt.
     * @param entries the entries to be shown in the receipt.
     */
    public Receipt(Recipient recipient, Collection<ReceiptEntry> entries)
    {
        this.recipient = recipient;
        this.dateGenerated = new Date();
        this.entries = new ArrayList<>(entries);
    }

    /**
     * Creates a receipt object.
     *
     * @param recipient the recipient of this receipt.
     * @param entries the entries to be shown in the receipt.
     */
    public Receipt(Recipient recipient, ReceiptEntry... entries)
    {
        this.recipient = recipient;
        this.dateGenerated = new Date();
        this.entries = Arrays.asList(entries);
    }

    /**
     * Gets the generation time of the receipt.
     *
     * @return time the receipt was generated.
     */
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
        return String.format("Sent to: %s\nRegion: %s\n\n%s\n\nP% 10.2f - Total", recipient.getName(), recipient.getRegion(),
            String.join("\n\n", e), getPrice());
    }
}
