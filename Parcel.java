import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ItemComparator implements ItemComparator<Item>
{

}

public class Parcel
{
  private final boolean insured;
  private final String recipient;
  private ArrayList<Item> items;
  public static final String[] region =
  new String[]
  {
      "LUZON",
      "METRO MANILA",
      "VISAYAS",
      "MINDANAO"
  };

  public final static Integer[][] boxSize =
  new Integer[][]
  { /* In the order of length, width, height in inches*/
      {9,14,1},   /* Flat parcel has max of 3 kilos weight */
      {12,18,3},  /* Flat parcel has max of 3 kilos weight */
      {12,10,15},
      {14,11,7},
      {18,12,9},
      {20,16,12}
  };

  public Parcel(String recipient, String region)
  {
    this(recipient, region, false);
    this.items  = new ArrayList<>();
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

  // Add stackLeft, stackWidth, stackHeight
  // added packItems
  private double computeVolume(Integer[] dimensions)
  {
  /* Multiplies the length, width, height */
    int i; double volume = 1;
    for (i=0; i<dimensions; i++)
        volume *= dimensions[i];
    return volume;
  }

  public void packItems()
  {
    boolean fitsLStack, fitsWStack, fitsHStack, start = true;
    int i = 0; /*  Start with flat box dimensions */
    sortByVolume(); /* Sort by dimensions/volume */
    while (start)
    {
    /* If it can stack ALL items in 1 orientation without consuming
    60 % of available space then use stack algorithm */
      fitsLStack = stackLeft();
      fitsWStack = stackWidth();
      fitsHStack = stackHeight();
      // item.getWeight() makes sure <= 3 kilos for flat
      if (itemList.size() < computeVolume(boxSize[i])* 0.40);
        start = stop;
      else {
    /* If it consumes more than 40% of the available space then
    use volume based algorithm (bin packaging algorithm) */
      done = binPackaging();
      i++;
      }
    }
  }

  private sortByVolume()
  {
    Collections.sort(items, new ItemComparator());
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
