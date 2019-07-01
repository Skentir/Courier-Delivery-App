import java.util.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JohnnyMoves {
  private Scanner sc;
  private List<Parcel> parcels;

  public JohnnyMoves() {
    this.sc = new Scanner(System.in);
    this.parcels = new ArrayList<>();
  }

  public boolean compute(Parcel parcel, List<Item> items) {
    ParcelPacker packer = new ParcelPacker();
    Item[] itemsArr = items.toArray(new Item[0]);
    return packer.pack(parcel, itemsArr);
  }

  /**
   * Runs the interface for creating an irregular product.
   *
   * @return a new irregularly shaped product
   */
  public IrregularProduct addIrregular()
  {
    double length, width, height, weight; String name;

    System.out.print("Name of product: ");
    name = sc.nextLine();

    do
    {
      System.out.print("Enter length of product: ");
      length = sc.nextDouble();
      System.out.print("Enter width of product: ");
      width = sc.nextDouble();
      System.out.print("Enter height of product: ");
      height = sc.nextDouble();
    } while (length <= 0 || width <= 0 || height <= 0);

    do
    {
      System.out.print("Enter weight of product: ");
      weight = sc.nextDouble();
    } while (weight <= 0);

    return new IrregularProduct(name, length, width, height, weight);
  }

  /**
   * Runs the interface for creating a regular product.
   *
   * @return a new regular, box-shaped product
   */
  public RegularProduct addRegular()
  {
    double length, width, height, weight; String name;

    System.out.print("Name of product: ");
    name = sc.nextLine();

    do
    {
      System.out.print("Enter length of product: ");
      length = sc.nextDouble();
      System.out.print("Enter width of product: ");
      width = sc.nextDouble();
      System.out.print("Enter height of product: ");
      height = sc.nextDouble();
    } while (length <= 0 || width <= 0 || height <= 0);

    do
    {
      System.out.print("Enter weight of product: ");
      weight = sc.nextDouble();
    } while (weight <= 0);

    return new RegularProduct(name, length, width, height, weight);
  }

  /**
   * Runs the interface for creating a document.
   *
   * @return a new document
   */
  public Document addDocument()
  {
    int pages = -1;
    String name = null;
    double length, width;

    System.out.print("Name of document: ");
    name = sc.nextLine();

    do
    {
      System.out.print("Enter number of pages: ");
      pages = sc.nextInt();
    } while (pages <= 0);

    do
    {
      System.out.print("Enter length of document: ");
      length = sc.nextDouble();
      System.out.print("Enter width of document: ");
      width = sc.nextDouble();
    } while (length <= 0 || width <= 0);

    return new Document(name, length, width, pages);
  }

  public Item createItem()
  {
    String[] choices = new String[]
    {
      "Document",
      "Regular product",
      "Irregular product",
      "Cancel"
    };

    int choice = optionIndex("What type of item do you want to add?", choices);
    switch (choice)
    {
      case 1:
        return addDocument();
      case 2:
        return addRegular();
      case 3:
        return addIrregular();
      case 4:
      default:
        return null;
    }
  }

  public String regionCode(String region)
  {
    switch (region.toLowerCase())
    {
      case "metro manila": return "MML";
      case "visayas": return "VIS";
      case "mindanao": return "MIN";
      case "luzon": return "LUZ";
      default: return null;
    }
  }

  public String dateCode(Parcel parcel)
  {
    Date shipDate = parcel.getShipDate();
    SimpleDateFormat fDate = new SimpleDateFormat ("MMDD");
    return fDate.format(shipDate);
  }

  public static class ParcelComparator implements Comparator<Parcel>
  {
    @Override
    public int compare(Parcel p1, Parcel p2)
    {
      Date d1 = p1.getShipDate();
      Date d2 = p2.getShipDate();

      if(d1.before(d2))
        return -1;
      else if (d2.before(d1))
        return 1;
      else
        return 0;
    }
  }

  public int seqCode(Parcel parcel)
  {
    Date target = parcel.getShipDate();
    //Collections.sort(parcels, new ParcelComparator());
    int seq = 0, i = 0;
    boolean found = false;
    /* Find the index where the first occurance of the Month and Day of the parcel sent */

    do
    {
      seq++;
      for (int j = 0; j < parcels.size(); j++)
      {
        String trackingCode = parcels.get(j).getTrackingCode();
        String dateCode = trackingCode.
      }

    } while (!found);

    return seq;
  }

  public String generateCode(Parcel parcel)
  {
    String code, dest, itemNum, pType, seq, date;
    dest = parcel.getRegionCode(); /* Gets location code: MNL, VIS, MIN, or LUZ */
    itemNum = String.format("%02d", parcel.getItems().size()); /* Gets number of items */
    pType = parcel.getParcelType(); /* Get parcel Type: FLT or BOX */
    date = dateCode(parcel); /* Set date in string format of MMDD */
    seq = String.format("%03d", seqCode()); /* Number for the day */
    //code = "<"+pType+">"+"<"+date+">"+"<"+dest+">"+"<"+itemNum+">"+"<"+seq+">";
    code = pType + date + dest + itemNum + seq;
    //parcel.setTrackingCode(code);
    return code;
  }

  /**
   * Runs the item menu.
   *
   * @param items the list of items to be worked on
   */
  public void itemMenu(List<Item> items)
  {
    String[] commands = new String[] {
      "Add a new item",
      "View all items",
      "Remove an item",
      "Leave"
    };

    boolean running = true;

    while (running)
    {
      int option = optionIndex("Please select an option...", commands);
      switch (option)
      {
        case 1:
          Item item = createItem();
          items.add(item);
          break;
        case 2:
          System.out.println("Items currently in parcel:");
          for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
          }
          break;
        case 3:
          Item[] allItems = items.toArray(new Item[0]);
          int index = optionIndex("Select an item to remove from the parcel:", allItems);
          items.remove(index - 1);
          break;
        case 4:
          running = false;
          break;
      }
    }
  }

  /**
   * Runs the parcel sending menu.
   */
  public void sendMenu()
  {
    String[] commands = new String[] {
      "Set recipient",
      "Set insurance",
      "Add, remove or view items",
      "Checkout",
      "Cancel"
    };

    boolean running = true;
    int choice;
    char menu;
    String recipient = null;
    String region = null;
    boolean insured = false;
    ArrayList<Item> items = new ArrayList<>();

    while (running)
    {
      int option = optionIndex("Please select an option . . .", commands);
      switch (option)
      {
        case 1:
          System.out.print("Who will receive the parcel: ");
          recipient = sc.nextLine();
          region = optionValue("Which region is the recipient located?", Parcel.REGIONS);
          break;
        case 2:
          insured = yesOrNo("Should the parcel be insured?");
          break;
        case 3:
          itemMenu(items);
          break;
        case 4:
          Parcel parcel = new Parcel(recipient, region, insured);
          // TODO: add items to parcel before computing
          if (compute(parcel, items)) {
            parcel.addItems(items);
            // TODO parcel.startTracking();
            System.out.println(parcel);
            parcel.displayItems();
            System.out.println(parcel.getDimensions());
            parcels.add(parcel);
            running = false;
          } else {
            System.out.println("Your parcel cannot be made because not all items can fit inside.");
          }
          break;
        case 5:
          running = false;
          break;
      }
    }
  }

  /**
   * Asks the user a question on the console and the user is expected to type
   * Y for yes (true) and N for no (false). This method would keep running until
   * the user types in a valid input.
   *
   * @param question the question to be displayed
   *
   * @return true if the user types y, false if the user types n
   */
  public boolean yesOrNo(String question)
  {
    System.out.print(question);
    System.out.print(" (y/n): ");

    boolean valid, result = false, empty;
    do
    {
      valid = false;
      empty = false;
      String input = sc.nextLine();
      if (input != null)
      {
        if (input.length() == 1)
        {
          char c = input.charAt(0);

          valid = true;
          if (c == 'y' || c == 'Y')
            result = true;
          else if (c == 'n' || c == 'N')
            result = false;
          else
            valid = false;
        }
      }
      else
      {
        empty = true;
      }

      if (!valid && !empty)
      {
        System.out.print(question);
        System.out.print(" (y/n): ");
      }
    } while (!valid);

    return result;
  }

  /**
   * Provides the user with a list of choices and the user picks a choice by
   * typing in the number corresponding to his/her choice.
   *
   * @param message the message to be displayed, telling the user what to do
   * @param choices the choices avaiable to the user
   *
   * @return the number of the user's choice
   */
  public <T> int optionIndex(String message, T[] choices)
  {
    boolean valid = false;
    int choice = 0;

    System.out.println(message);
    for (int i = 0; i < choices.length; i++)
    {
      System.out.println("[" + (i + 1) + "] " + choices[i]);
    }
    System.out.println();

    do
    {
      System.out.print("Your choice: ");
      int answer = sc.nextInt();
      sc.nextLine();
      if (answer >= 1 && answer <= choices.length)
      {
        valid = true;
        choice = answer;
      }
    } while (!valid);

    return choice;
  }

  /**
   * Provides the user with a list of choices and the user picks a choice by
   * typing in the number corresponding to his/her choice.
   *
   * @param message the message to be displayed, telling the user what to do
   * @param choices the choices avaiable to the user
   *
   * @return the value of the user's choice
   */
  public <T> T optionValue(String message, T[] choices)
  {
    boolean valid = false;
    T choice = null;

    System.out.println(message);
    for (int i = 0; i < choices.length; i++)
    {
      System.out.println("[" + (i + 1) + "] " + choices[i]);
    }
    System.out.println();

    do
    {
      System.out.print("Your choice: ");
      int answer = sc.nextInt();
      sc.nextLine();
      if (answer >= 1 && answer <= choices.length)
      {
        valid = true;
        choice = choices[answer - 1];
      }
    } while (!valid);

    return choice;
  }

  public int findCode(String code)
  {
    int i, found = -1;
    for (i=0; i < parcels.size(); i++)
    {
      if (parcels.get(i).getTrackingCode().equalsIgnoreCase(code));
        return i;
    }
    return found;
  }

  /**
   * Runs the parcel tracking menu.
   */
  public void trackMenu()
  {
    String code; int parcelIdx = -1;
    do
    {
      System.out.println("Please enter a tracking code: ");
      code = sc.nextLine();
      //parcelIdx = codeExists(parcels, code);
      //if (parcelIdx == -1)
      //  System.out.println("Woops! It seems this code doesn't exist. Try again.");
    } while (parcelIdx != -1);

    if (parcelIdx == -1)
      return;

    System.out.println("----------------------------------");
    System.out.print("Tracking Code: ");
    System.out.println (parcels.get(parcelIdx).getTrackingCode());
    System.out.printf("Recipient: %s\n", parcels.get(parcelIdx).getRecipient());
    System.out.printf("Region: %s\n", parcels.get(parcelIdx).getRegion());
    System.out.println (parcels.get(parcelIdx).getTrackingCode());
    System.out.printf("Status: %s\n", parcels.get(parcelIdx).getStatus(getDate()));
    System.out.print("Items shipped:\n");
    parcels.get(parcelIdx).displayItems();
    System.out.println("----------------------------------\n");
  }

  public void timeMenu()
  {
    // TODO: change simulated time
  }

  /**
   * Closes any resources used by the driver class.
   */
  public void close()
  {
    sc.close();
  }

  public Date getDate()
  {
    return new Date();
  }

  public static void main(String[] args)
  {
    String[] options = new String[] {
      "Send parcel",
      "Track parcel",
      "Change time",
      "Exit app"
    };
    JohnnyMoves driver = new JohnnyMoves();
    boolean running = true;

    while (running)
    {
      System.out.println(">>> Welcome to JohnnyMoves Services <<<");
      System.out.println("The time now is " + driver.getDate());
      int choice = driver.optionIndex("Please select an option...", options);

      if (choice == 1)
        driver.sendMenu();
      else if (choice == 2)
        driver.trackMenu();
      else if (choice == 3)
        driver.timeMenu();
      else
        running = false;
    }

    driver.close();
  }
}
