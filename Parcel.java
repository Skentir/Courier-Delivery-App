/**
* @author Kirsten Sison and Marc Tiburcio
* CCPROG3 S12A - Nathalie Lim Cheng
*/
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import packer.Dimension;

/**
 * The Parcel class represents a parcel to be sent to a person or entity, or the
 * recipient, located in any available region.
 */
public class Parcel
{
  /**
   * Code for a flat parcel.
   */
  public static final String FLAT = "FLT";

  /**
   * Code for a box parcel.
   */
  public static final String BOX = "BOX";

  private final Recipient recipient;
  private final String region;
  private final Date shipDate;
  private boolean insured;
  private ArrayList<Item> items;

  private String trackingCode;
  private String parcelType;
  private Dimension dimensions;

  /**
   * Contains all the valid regions.
   */
  public static final String[] REGIONS = new String[]
  {
    "LUZON",
    "METRO MANILA",
    "VISAYAS",
    "MINDANAO"
  };

  /**
   * Constructs a new parcel.
   *
   * @param name who will receive the parcel
   * @param region in what region is the recipient located
   * @param insured is the parcel insured?
   */
  public Parcel(String name, String region, ArrayList<Item> items)
  {
    recipient = new Recipient(name, region);
    shipDate = new Date();
    this.region = region;
    this.items = new ArrayList<>();
    this.items = items;
  }

  /**
   * Constructs a new parcel that is not insured.
   *
   * @param recipient who will receive the parcel
   * @param region in what region is the recipient located
   */
  public Parcel(Recipient recipient, ArrayList<Item>items)
  {
    shipDate = new Date();
    this.recipient = recipient;
    this.region = recipient.getRegion();
    this.items = new ArrayList<>(items);
  }

  /**
   * Gets the status of the package. The parcels have a status of Processing
   * when it is sent within the first day.
   *
   * @param currentDate the current date
   *
   * @return the status of the package
   */
  public String getStatus(Date currentDate)
  {
    long diff = (currentDate.getTime() - shipDate.getTime())/(24*60*60*1000);
    System.out.printf("\nIt has been %d days since parcel was shipped\n", diff);

    String status = null;
    if (region.equalsIgnoreCase("METRO MANILA"))
    {
      if (diff <= 0)
        status = "Processing";
      else if (diff > 0 && diff < 1)
        status = "Shipping";
      else
        status = "Delivered";
    }
    else if (region.equalsIgnoreCase("LUZON"))
    {
      if (diff <= 0)
        status = "Processing";
      else if (diff > 0 && diff < 2)
        status = "Shipping";
      else
        status = "Delivered";
    }
    else if (region.equalsIgnoreCase("VISAYAS"))
    {
      if (diff <= 0)
        status = "Processing";
      else if (diff > 0 && diff < 4)
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

  /**
   * Gets the base price of the parcel, which is determined by the type of the
   * parcel and/or all the items inside.
   *
   * @return the base price of the parcel
   */
  public double getBasePrice()
  {
    int sizeType = parcelType.charAt(3) - '0';
    String type = parcelType.substring(0, 3);
    switch (type)
    {
      case BOX:
        double irregular = 0.0;
        double regular = 0.0;
        for (Item item : items)
        {
          if (item instanceof IrregularProduct)
          {
            double volumeBasedPrice = item.getVolume() / 305.0 * 30.0;
            double realBasedPrice = item.getWeight() * 40.0;
            irregular += Math.max(volumeBasedPrice, realBasedPrice);
          }
          else
          {
            regular += item.getWeight() * 40.0;
          }
        }
        return Math.max(irregular, regular);
      case FLAT:
        if (sizeType == 0)
          return 30.0;
        else if (sizeType == 1)
          return 50.0;
        else if (sizeType == 2)
          return 20.0;
        else
          return 0.0;
      default:
        return 0.0;
    }
  }

  public boolean getInsurance()
  {
      return insured;
  }
  public void setInsurance(boolean insured)
  {
    this.insured = insured;
  }
  /**
   * Gets the total weight of all the items in the parcel.
   *
   * @return the total sum of the weights of all items in the parcel
   */
  private double getWeight()
  {
    double weight = 0.0;
    for (Item item : items)
      weight += item.getWeight();

    return weight;
  }

  public Receipt generateReceipt()
  {
      ReceiptGroup itemsGroup = null;
      if (parcelType == null || !parcelType.contains("FLT"))
      {
          ReceiptEntry[] items = new ReceiptEntry[this.items.size()];
          for (int i = 0; i < this.items.size(); i++)
          {
              items[i] = ReceiptItem.chargeForItem(this.items.get(i));
          }

          itemsGroup = new ReceiptGroup("All items", items);
      }

      ReceiptEntry insured = null;
      if (this.insured)
            insured = ReceiptItem.chargeForInsurance(items.size());


    ReceiptEntry parcel = null;
    if (parcelType != null)
        parcel = ReceiptItem.chargeForParcel(parcelType);
    ReceiptEntry region = null;
    if (parcelType != null)
        region = ReceiptItem.chargeForRegion(recipient.getRegion(), getWeight(), fromType(parcelType).getVolume());
    ArrayList<ReceiptEntry> entries = new ArrayList<>();
    if (itemsGroup != null) entries.add(itemsGroup);
    if (insured != null) entries.add(insured);
    if (parcel != null) entries.add(parcel);
    if (region != null) entries.add(region);
    return new Receipt(recipient, entries);
  }

  public static Dimension fromType(String parcelType)
  {
      switch (parcelType)
      {
          case "FLT0": return new Dimension(9,14,1);
          case "FLT1": return new Dimension(12,18,3);
          case "FLT2": return new Dimension(4, 6, 3);
          case "BOX0": return new Dimension(12,10,5);
          case "BOX1": return new Dimension(14,11,7);
          case "BOX2": return new Dimension(18,12,9);
          case "BOX3": return new Dimension(20,16,12);
          default: return null;
      }
  }

  /**
   * Gets the total price of the parcel when insurance and service fees are
   * accounted for.
   *
   * @return the price of the parcel
   */
  public double getPrice()
  {
    double basePrice = getBasePrice();

    double insurance = this.insured ? items.size() * 5.0 : 0.0;
    double service = 0.0;

    double weight = getWeight();

    if (region.equalsIgnoreCase("METRO MANILA"))
      service = 50.0;
    else if (region.equalsIgnoreCase("LUZON"))
      service = 100.0;
    else if (region.equalsIgnoreCase("VISAYAS"))
      service = Math.max(1000.0, Math.max(0.1 * weight, 0.1 * dimensions.getVolume()));
    else if (region.equalsIgnoreCase("MINDANAO"))
      service = Math.max(3000.0, Math.max(0.25 * weight, 0.25 * dimensions.getVolume()));

    return basePrice + insurance + service;
  }

  /**
   * Gets the type of the parcel.
   *
   * @return either FLT or BOX
   */
  public String getParcelType()
  {
    return parcelType.substring(0, 3);
  }

  /**
   * Sets the type of the parcel.
   *
   * @param type the type of the parcel. Accepted values are FLTn and BOXn,
   *             where n refers to the size of the parcel
   */
  public void setParcelType(String type)
  {
    // accept only either BOX or FLT
    if (!type.startsWith(BOX) && !type.startsWith(FLAT))
      return;

    if (type.length() != 4)
      return;

    if (!Character.isDigit(type.charAt(3)))
      return;

    this.parcelType = type;
    dimensions = fromType(type);
  }

  /**
   * Gets the name of the recipient of the parcel.
   *
   * @return the name of the recipient
   */
  public Recipient getRecipient()
  {
    return recipient;
  }

  /**
   * Gets the destination region of the parcel where the recipient is located or
   * currently resides.
   *
   * @return the destination region
   */
  public String getParcelRegion()
  {
    return region;
  }

  /**
   * Gets a code representing the destination region.
   *
   * @return the region code
   */
  public String getRegionCode()
  {
    if (region.equalsIgnoreCase("METRO MANILA"))  return "MML";
    else if (region.equalsIgnoreCase("LUZON"))    return "LUZ";
    else if (region.equalsIgnoreCase("VISAYAS"))  return "VIS";
    else if (region.equalsIgnoreCase("MINDANAO")) return "MIN";
    else                                          return null;
  }

  /**
   * Gets the shipping date (date when the parcel is first entered into the
   * system).
   *
   * @return the ship date
   */
  public Date getShipDate()
  {
    return shipDate;
  }

  /**
   * Gets the tracking code used so the customer can track the parcel.
   *
   * @return a tracking code
   */
  public String getTrackingCode()
  {
    return trackingCode;
  }

  /**
   * Sets the tracking code for the parcel. The method has no effect if a
   * tracking code has been previously assigned.
   *
   * @param code the new tracking code
   */
  public void setTrackingCode(String code)
  {
    if (trackingCode != null)
      return;

    trackingCode = code;
  }

  /**
   * Gets the dimensions of the parcel.
   *
   * @return the dimensions
   */
  public Dimension getDimensions()
  {
    return this.dimensions;
  }

  /**
   * Sets the dimensions of the parcel.
   *
   * @param dimension the new dimensions of the parcel
   */
  public void setDimensions(Dimension dimension)
  {
    this.dimensions = dimension;
  }

  /**
   * Adds all the given items to the parcel. If there is at least one item, this
   * method would have no effect.
   */
  public void addItems(Collection<Item> items)
  {
    if (this.items.size() == 0)
      this.items.addAll(items);
  }

  /**
   * Retrieves all the items in the parcel.
   *
   * @return all the items
   */
  public List<Item> getItems()
  {
    return items;
  }

  /**
   * Displays all the items in the parcel to the console or terminal.
   */
  public void displayItems()
  {
    for (int i = 0; i < items.size(); i++)
    {
      Item item = items.get(i);
      System.out.printf("[%d] %s - %s", i + 1, item.getName(), item.getItemType());
      System.out.println();
    }
  }

  /**
   * Returns the string representation of the parcel.
   *
   * @return a string to identify the parcel and its recipient
   */
  @Override
  public String toString()
  {
    return String.format("%s (for %s, %s)", trackingCode, recipient, region);
  }
}
