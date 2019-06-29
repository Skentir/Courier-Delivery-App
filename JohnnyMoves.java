import java.util.*;
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

  public void itemMenu(List<Item> items)
  {

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
    JohnnyMoves driver = new JohnnyMoves(sc);
    String code; int parcelIdx = -1;
    do
    {
      System.out.println("Please enter a tracking code: ");
      code = driver.sc.nexLine();
      parcelIdx = driver.codeExists(parcels, code);
      if (parcelIdx == -1)
        System.out.println("Woops! It seems this code doesn't exist. Try again.");
    } while (parcelIdx != -1);

    System.out.println("----------------------------------");
    System.out.print("Tracking Code: ");
    System.out.println (parcel.get(parcelIdx).getTrackingCode());
    System.out.printf("Recipient: %s\n", parcel.get(parcelIdx).getRecipient());
    System.out.printf("Region: %s\n", parcel.get(parcelIdx).getRegion());
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
        driver.sendMenu(parcels);
      else if (choice == 2)
        driver.trackParcel(parcels);
      else
        running = false;
    }

    sc.close();
  }
}
