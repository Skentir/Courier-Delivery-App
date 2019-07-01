import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import packer.Dimension;

class ItemComparator implements Comparator<Item>
{
  @Override
  public int compare(Item a, Item b)
  {
    // TODO: implement this function
    return 0;
  }
}

public class Parcel
{
  public static final String FLAT = "FLT";
  public static final String BOX = "BOX";

  private boolean insured;
  private final String recipient;
  private final String region;
  private final Date shipDate;
  private ArrayList<Item> items;
  private String trackingCode;
  private String parcelType;
  private Dimension dimensions;

  public static final String[] REGIONS =
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
  }

  public Parcel(String recipient, String region, boolean insured)
  {
    shipDate = new Date();
    this.recipient = recipient;
    this.region = region;
    this.insured = insured;
    this.items = new ArrayList<>();
  }

  public void setParcelType(String type)
  {
    // accept only either BOX or FLAT
    if (!type.equals(BOX) && !type.equals(FLAT))
      return;

    this.parcelType = type;
  }
/*
  public void setShipDate(Date currDate)
  {
    shipDate = currDate;
  }
  public void getShipDate(Date currDate)
  {
    shipDate = currDate;
  }
  public void getStatus(Date currDate)
  {
    shipDate = currDate;
  }*/
  public  String getParcelType()
  {
    return parcelType;
  }

  public String getRecipient()
  {
    return recipient;
  }

  public String getRegion()
  {
    return region;
  }

  public String getTrackingCode()
  {
    return trackingCode;
  }

  public List<Item> getItems()
  {
    return items;
  }

  public Dimension getDimensions()
  {
    return this.dimensions;
  }

  public void setDimensions(Dimension dimension)
  {
    this.dimensions = dimension;
  }

  public void removeItem(Item item)
  {
    items.remove(item);
  }

  public void setInsurance(boolean insured)
  {
    this.insured = insured;
  }

  // Add stackLeft, stackWidth, stackHeight
  // added packItems
  private double computeVolume(Integer[] dimensions)
  {
  /* Multiplies the length, width, height */
    int i; double volume = 1;
    //for (i=0; i<dimensions; i++)
    //  volume *= dimensions[i];
    return volume;
  }

  public void addItem(Item item)
  {
    if (item != null) {
      items.add(item);
      System.out.println("Item added to parcel!");
    } else
      System.out.println("Item can't be added to parcel!");
  }

  public void addItems(Collection<Item> items)
  {
    items.addAll(items);
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
      //fitsLStack = stackLeft(boxSize[i]);
      //fitsWStack = stackWidth();
      //fitsHStack = stackHeight();
      // item.getWeight() makes sure <= 3 kilos for flat
      //if (itemList.size() < computeVolume(boxSize[i])* 0.40)
      //  start = stop;
    //  else
    //  {
        /* If it consumes more than 40% of the available space then
        use volume based algorithm (bin packaging algorithm) */
    //    done = binPackaging();
    //    i++;
  //    }
    }
  }

  private void sortByVolume()
  {
    Collections.sort(items, new ItemComparator());
  }

  public void displayItems()
  {
    for (int i = 0; i < items.size(); i++)
    {
      Item item = items.get(i);
      System.out.printf("[%d] %s - %s", i + 1, item.getName(), item.getItemType());
      System.out.println();
    }
  }

  @Override
  public String toString()
  {
    return String.format("%s (for %s, %s)", trackingCode, recipient, region);
  }
}
