import java.util.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class JohnnyMoves {
  private Scanner sc;

  public JohnnyMoves(Scanner sc) {
    this.sc = sc;
  }

  public void Compute(Parcel box) {
      // To be coded soon
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

  public void addRegular()
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

  public void addDocument()
  {
    int pages = -1; String name;
    double length, width;

    System.out.print("Name of document: ");
    name = sc.nextLine();

    do
    {
      System.out.print("\nEnter number of pages: ");
      pages = sc.nextInt();
    } while (pages < 0);

    do
    {
      System.out.print("\nEnter length of document: ");
      length = sc.nextDouble();
      System.out.print("\nEnter width of document: ");
      width = sc.nextDouble();
    } while (length < 0 || width < 0);
    Parcel.add(new Document(name, length, width, pages));
  }

  public void addItems(List itemList)
  {
    int choice = 0;
    do
    {
      System.out.println("What type of item item are you gonna add?");
      System.out.println("[1] Document");
      System.out.println("[2] Regular");
      System.out.println("[3] Irregular");
      System.out.printf("Choice: ");
      choice = sc.nextInt();
    } while(choice < 1 || choice > 3);
    sc.nextLine();

    switch(choice)
    {
      case 1: // Document
          addDocument(); break;
      case 2:
          addRegular(); break;
      case 3:
          addIrregular(); break;
    }
  }

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
        /*System.out.println("[3] Modify an item");*/
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

  public Parcel getRecipient()
  {
    String name; int region, i;
    do
    {
      System.out.println("Enter name of recipient:");
      name = sc.nextLine();
      if (name.length() < 4)
        System.out.println("Name too short!");
      else if (name.length() > 30)
      System.out.println("Name too long!");
    } while (name.length() < 4 || name.length() > 30);

    for (i = 0; i < Parcel.REGIONS.length; i++)
      System.out.printf("[%d] "+ Parcel.REGIONS[i], i+1);

    do
    {
      System.out.println("Enter region:");
      region = sc.nextInt();
      if (region < 0 || region > 3)
        System.out.println("Invalid Action.");
    } while (region < 0 || region > 3);
    sc.nextLine();

    Parcel box = new Parcel (name, Parcel.REGIONS[region]);
    return box;
  }

  public void itemMenu(List<Item> items)
  {

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
    Parcel parcel = parcelList.get(parcelList.size()-1)
    String code, dest, itemNum, pType;
    dest = regionCode(parcel.getRegion()); /* Gets location code: MNL, VIS, MIN, or LUZ */
    itemNum = parcel.items.size(); /* Gets number of items */
    pType = parcel.getParcelType(); /* Get parcel Type: FLT or BOX */
    date = dateCode(parcelList); /* Get date in string format of MMDD */
    seq = seqCode(parcelList)/* TO DO: number for da day */
    code = "<"+pType+">"+"<"+date+">"+"<"+dest+">"+"<"+itemNum+">"+"<"+seq+">";
    parcel.setTrackingCode(code);
  }

  public Parcel sendMenu(ArrayList<Parcel> parcels)
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
          parcels.add(new Parcel(recipient, region, insured));
          generateCode(parcels);
          running = false;
          break;
        case 5:
          running = false;
          break;
      }
    };

    return parcel;
  }

  public boolean yesOrNo(String message)
  {
    System.out.print(message);
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
        System.out.print(message);
        System.out.print(" (y/n): ");
      }
    } while (!valid);

    return result;
  }

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

  public int codeExists(ArrayList<Parcel> parcels, String code)
  {
    int i, found = -1;
    for (i=0; i < parcels.size(); i++)
    {
      if (parcels.get(i) != null)
        if (parcels.get(i).getTrackingCode().equalsIgnorecase(code));
          return i;
    }
    return found;
  }

  public void trackParcel(ArrayList<Parcel> parcels)
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
    System.out.println (parcels.get(parcelIdx).getTrackingCode());
    System.out.printlf("Status: %s", parcels.get(parcelIdx).getStatus());
    System.out.printf("Recipient: %s\n", parcels.get(parcelIdx).getRecipient());
    System.out.printf("Region: %s\n", parcels.get(parcelIdx).getRegion());
    System.out.print("Items shipped:\n");
    parcel.get(i).displayItems();
    System.out.println("----------------------------------\n");
  }

  public static void main(String[] args)
  {
    String[] options = new String[] {
      "Send parcel",
      "Track parcel",
      "Exit app"
    };
    ArrayList<Parcel> parcels = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    JohnnyMoves driver = new JohnnyMoves(sc);
    boolean running = true;

    while (running)
    {
      System.out.println("\n>>> Welcome to JohnnyMoves Services <<<\n");
      int choice = driver.optionIndex("Please select an option . . .", options);

      if (choice == 1)
        driver.sendMenu(ArrayList<Parcel> parcels);
      else if (choice == 2)
        driver.trackParcel(ArrayList<Parcel> parcels;
      else
        running = false;
    }

    sc.close();
  }
}
