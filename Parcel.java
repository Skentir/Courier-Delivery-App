
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.*;
import java.util.*;

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
  private boolean insured;
  private final String recipient;
  private final String region;
  private final Date shipDate;
  private String status;
  private ArrayList<Item> items;
  private String trackingCode;
  private String parcelType;
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

  public void setParcelType(Integer sizeIdx)
  {
    if (sizeIdx Idx <= 1)
      parcelType = "FLT";
    else
      parcelType = "BOX";
  }

  public void setShipDate(Date currDate)
  {
    shipDate = currDate;
  }
  private void getStatus(Date shipDate)
  {
    Date rDate = new Date();
    Date fr = new SimpleDateFormat("dd-M-yyyy").parse((String)request.getParameter(rDate);
    Date sD = new SimpleDateFormat("dd-M-yyyy").parse((String)request.getParameter(shipDate));

    long diff = (sD.getTime() - fr.getTime())/(24*60*60*10000);
    System.out.printf("\nIt has been %d days since parcel was shipped\n", diff);

    if (region.equalsIgnorecase("METRO MANILA"))
    {
      if (diff <= 0)
        status = "Processing";
      else if (diff > 0 && dif < 3)
        status = "Delivered";
    }
    else if (region.equalsIgnorecase("LUZON"))
    {
      if (diff == 0)
        status = "Processing";
      else if (diff > 0 && diff < 4)
        status = "Shipping";
      else
        status = "Delivered";
    }
    else if (region.equalsIgnorecase("VISAYAS"))
    {
      if (diff <= 0)
        status = "Processing";
      else if (diff > 0 && diff < 5)
        status = "Shipping";
      else
        status = "Delivered";
    }
    else if (region.equalsIgnorecase("MINDANAO"))
    {
      if (diff <= 0)
        status = "Processing";
      else if (diff > 0 && diff < 7)
        status = "Shipping";
      else
        status = "Delivered";
    }
  }

  public void getShipDate(Date currDate)
  {
    shipDate = currDate;
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
    }
  }
}
