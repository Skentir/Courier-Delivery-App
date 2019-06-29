import java.util.*;

public class JohnnyMoves {
  private Scanner sc;

  public JohnnyMoves(Scanner sc) {
    this.sc = sc;
  }

  public void Compute(Parcel box) {
      // To be coded soon
  }

  public void removeItems(List itemList, Parcel box)
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

  public void addInsurance(Parcel box)
  {
    /* boolean false; */char reply;
    do
    {
      System.out.println("Do you want to insure your parcel? (y/n)");
      reply = sc.nextLine().charAt(0);
    } while(reply != 'y' || reply != 'Y' || reply != 'n' || reply != 'N');
    if (reply == 'y' || reply == 'Y')
      box.setInsurance(true);
    else
      box.setInsurance(false);
  }

  public void addIrregular(List itemList)
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

    itemList.add(new IrregularProduct(name, length, width, height, weight));
  }

  public void addRegular(List itemList)
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
    itemList.add(new RegularProduct(name, dimension, dimension, dimension, weight));
  }

  public void addDocument(List itemList)
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
    itemList.add(new Document(name, length, width, pages));
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
          addDocument(itemList); break;
      case 2:
          addRegular(itemList); break;
      case 3:
          addIrregular(itemList); break;
    }
  }

  public void getInputs(List itemList, Parcel box)
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
        System.out.println("[3] Add Insurance to Parcel");
        choice = sc.nextInt();
        sc.nextLine();
        if(choice < 1 || choice > 4)
          System.out.println("Invalid Action.");
      } while(running || choice < 1 || choice > 3);

      switch (choice)
      {
        case 1: addItems(itemList); break;
        case 2: removeItems(itemList, box); break;
        /*case 2: modifyItems(itemList); break;*/
        case 3: addInsurance(box); break;
      }

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

  public Parcel sendMenu()
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

    Parcel parcel = null;
    String recipient = null;
    String region = null;
    boolean insured = false;
    ArrayList<Item> itemList = new ArrayList<>();

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
          parcel = new Parcel(recipient, region, insured);


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
      if (answer >= 1 && answer <= choices.length)
      {
        valid = true;
        choice = choices[answer - 1];
      }
    } while (!valid);

    return choice;
  }

  public void trackParcel()
  {

  }

  public static void main(String[] args)
  {
    String[] options = new String[] {
      "Send parcel",
      "Track parcel",
      "Exit app"
    };

    Scanner sc = new Scanner(System.in);
    List itemList = new ArrayList<>();
    JohnnyMoves driver = new JohnnyMoves(sc);
    boolean running = true;

    while (running)
    {
      System.out.println("\n>>> Welcome to JohnnyMoves Services <<<\n");
      int choice = driver.optionIndex("Please select an option . . .", options);

      if (choice == 1)
        driver.sendMenu();
      else if (choice == 2)
        driver.trackParcel();
      else
        running = false;
    }

    sc.close();
  }
}
