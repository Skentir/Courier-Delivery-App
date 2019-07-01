import java.util.ArrayList;
import java.util.Collection;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.*;
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
  private String status;
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

  public String getStatus(Date currentDate)
  {
    long diff = (currentDate.getTime() - shipDate.getTime())/(24*60*60*10000);
    System.out.printf("\nIt has been %d days since parcel was shipped\n", diff);

    if (region.equalsIgnoreCase("METRO MANILA"))
    {
      if (diff <= 0)
        status = "Processing";
      else if (diff > 0 && diff < 3)
        status = "Delivered";
    }
    else if (region.equalsIgnoreCase("LUZON"))
    {
      if (diff == 0)
        status = "Processing";
      else if (diff > 0 && diff < 4)
        status = "Shipping";
      else
        status = "Delivered";
    }
    else if (region.equalsIgnoreCase("VISAYAS"))
    {
      if (diff <= 0)
        status = "Processing";
      else if (diff > 0 && diff < 5)
        status = "Shipping";
      else
        status = "Delivered";
    }
    else if (region.equalsIgnoreCase("MINDANAO"))
    {
      if (diff <= 0)
        status = "Processing";
      else if (diff > 0 && diff < 7)
        status = "Shipping";
      else
        status = "Delivered";
    }

    return status;
  }

  public Date getShipDate()
  {
    return shipDate;
  }
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
