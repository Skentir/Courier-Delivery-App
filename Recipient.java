public class Recipient
{
    private final String name;
    private final String region;

    public Recipient(String name, String region)
    {
        if (name == null)
            throw new IllegalArgumentException("name is null");
        if (region == null)
            throw new IllegalArgumentException("region is null");

        this.name = name;
        this.region = region;
    }

    public String getName()
    {
        return name;
    }

    public String getRegion()
    {
        return region;
    }
}
