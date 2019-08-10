/**
* @author Kirsten Sison and Marc Tiburcio
* CCPROG3 S12A - Nathalie Lim Cheng
*/

public class Recipient
{
    /*
     * Represents the recipient of the parcel
     */
    private final String name;
    private final String region;
    /**
     * Creates a receipt object.
     *
     * @param name the of the recipient.
     * @param region the destination region of the recipient.
     */
    public Recipient(String name, String region)
    {
        if (name == null)
            throw new IllegalArgumentException("name is null");
        if (region == null)
            throw new IllegalArgumentException("region is null");

        this.name = name;
        this.region = region;
    }
    /**
     * Returns the name of the recipient
     * @return name of the recipient
     */
    public String getName()
    {
        return name;
    }
    /**
     * Returns the region of the recipient
     * @return destination of the recipient
     */
    public String getRegion()
    {
        return region;
    }
}
