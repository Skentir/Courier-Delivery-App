import java.util.ArrayList;

public class Parcel
{
  private final boolean insured;
  private final String recipient;
  private final String region;
  public ArrayList<Item> items;

  public Parcel(String recipient, String region)
  {
    this(recipient, region, false);
  }

  public Parcel(String recipient, String region, boolean insured)
  {
    this.recipient = recipient;
    this.region = region;
    this.insured = insured;
  }

  public String getRecipient()
  {
    return recipient;
  }

  public String getRegion()
  {
    return region;
  }

  private void computeVolume()
  {

  }

  public void displayItems()
  {
    for (int i = 0; i < items.size(); i++)
    {
      Item item = items.get(i);
      System.out.printf("[%d] %s - %s", i + 1, item.getName(), item.getItemType());
    }
  }
}
