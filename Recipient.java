public class Recipient
{
    private final String name;
    private final String region;

    public Recipient(String name, String region)
    {
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
