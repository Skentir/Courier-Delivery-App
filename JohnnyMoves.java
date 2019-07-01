import java.util.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class JohnnyMoves {
  private Scanner sc;
  private List<Parcel> parcel;

  public JohnnyMoves() {
    this.sc = new Scanner(System.in);
    this.parcel = new ArrayList<>();
  }

  public boolean compute(Parcel parcel) {
    ParcelPacker packer = new ParcelPacker();
    return packer.pack(parcel, parcel.getItems());
  }

  public void removeItems(Parcel box)
  {
    int choice = 0, numItems = box.getItems().size();
    if (numItems != 0)
    {
      box.displayItems();
      do
      {
        System.out.println("\nWhich item are you removing?");
        System.out.printf("Choice: ");
        choice = sc.nextInt();
        choice--; // Indexing from 0 to n-1
      } while(choice < 0 || choice >= numItems);
      box.getItems().remove(choice);
      sc.nextLine();
    } else
      System.out.println("Box is empty. Add items first.");
  }

  public void addIrregular()
  {
    double length, width, height, weight; String name;

    System.out.print("Name of product: ");
    name = sc.nextLine();

    do
    {
      System.out.print("\nEnter length of document in inches: ");
      length = sc.nextDouble();
      System.out.print("\nEnter width of document in inches: ");
      width = sc.nextDouble();
      System.out.print("\nEnter height of document in inches: ");
      height = sc.nextDouble();
    } while (length < 0 || width < 0 || height < 0);
    weight = length * width * height / 305;

    Parcel.add(new IrregularProduct(name, length, width, height, weight));
  }

  public RegularProduct addRegular()
  {
    double dimension, weight; String name;

    System.out.print("Name of product: ");
    name = sc.nextLine();

    do
    {
      System.out.print("\nEnter dimension of product in inches: ");
      dimension = sc.nextDouble();
    } while (dimension < 0);
    weight = Math.pow(dimension, 3)/305;
    Parcel.add(new RegularProduct(name, dimension, dimension, dimension, weight));
  }

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

    int choice = optionIndex("What type of item do you want to add?");
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
/*
  public void getInputs(Parcel box)
  {
    boolean running = true; int choice = 0; char menu;
    while (running)
    {
      do
      {
        System.out.println("\nChooose an option:");
        System.out.println("[1] Add an item");
        System.out.println("[2] Remove an item");
        System.out.println("[3] Modify an item");
        choice = sc.nextInt();
        sc.nextLine();
        if(choice < 1 || choice > 2)
          System.out.println("Invalid Action.");
      } while(running || choice < 1 || choice > 2);

      if (choice == 1)
        addItems();
      else
        removeItems(box);

      do
      {
        System.out.print("Stay in this menu? (y/n): ");
        menu = sc.nextLine().charAt(0);
        if (menu == 'y' || menu == 'Y')
          running = true;
        else if (menu == 'n' || menu == 'N')
          running = false;
        else
          System.out.println("Invalid Action.");
      } while(menu != 'y' || menu != 'Y' || menu != 'n' || menu != 'N');
    }
  }
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

  public String regionCode(String region)
  {
    if (region.equalsIgnorecase("Metro Manila"))
      return "MML";
    else if (region.equalsIgnorecase("Visayas"))
      return "VIS";
    else if (region.equalsIgnorecase("Mindanao"))
      return "MIN";
    else if (region.equalsIgnorecase("Luzon"))
      return "LUZ";
  }

  public String dateCode(Parcel parcel)
  {
    Date shipDate = parcel.getShipDate();
    SimpleDateFormat fDate = new SimpleDateFormat ("MMDD");
    return fDate.format(shipDate);
  }

  public String seqCode(ArrayList<Parcel> parcelList)
  {
    int i, count;
    for (i = 0; i < parcelList.size(); i++)
    {

    }
    // Just compare dates
  }

  public  void generateCode(ArrayList<Parcel> parcelList)
  {
    Parcel parcel = parcelList.get(parcelList.size()-1);
    String code, dest, itemNum, pType;
    dest = regionCode(parcel.getRegion()); /* Gets location code: MNL, VIS, MIN, or LUZ */
    itemNum = parcel.items.size(); /* Gets number of items */
    pType = parcel.getParcelType(); /* Get parcel Type: FLT or BOX */
    date = dateCode(parcelList); /* Get date in string format of MMDD */
    seq = seqCode(parcelList);/* TODO: number for da day */
    code = "<"+pType+">"+"<"+date+">"+"<"+dest+">"+"<"+itemNum+">"+"<"+seq+">";
    parcel.setTrackingCode(code);
  }

  /**
   * Runs the parcel sending menu.
   */
  public void sendMenu(List<Parcel> parcels)
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

    while (running)
    {
      int option = optionIndex("Please select an option . . .", commands);
      switch (option)
      {
        case 1:
          System.out.println("Who will receive the parcel: ");
          recipient = sc.nextLine();
          region = optionValue("Which region is the recipient located?", Parcel.REGIONS);
          break;
        case 2:
          insured = yesOrNo("Should the parcel be insured?");
          break;
        case 3:
          itemMenu(itemList);
          break;
        case 4:
          Parcel parcel = new Parcel(recipient, region, insured);
          // TODO: add items to parcel before computing
          if (compute(parcel)) {
            parcel.startTracking();
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
      code = sc.nexLine();
      parcelIdx = driver.codeExists(parcels, code);
      if (parcelIdx == -1)
        System.out.println("Woops! It seems this code doesn't exist. Try again.");
    } while (parcelIdx != -1);

    System.out.println("----------------------------------");
    System.out.print("Tracking Code: ");
    System.out.println(parcels.get(parcelIdx).getTrackingCode());
    System.out.printlf("Status: %s", parcels.get(parcelIdx).getStatus());
    System.out.printf("Recipient: %s\n", parcels.get(parcelIdx).getRecipient());
    System.out.printf("Region: %s\n", parcels.get(parcelIdx).getRegion());
    System.out.print("Items shipped:\n");
    parcel.get(i).displayItems();
    System.out.println("----------------------------------\n");
  }

  /**
   * Closes any resources used by the driver class.
   */
  public void close()
  {
    sc.close();
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
