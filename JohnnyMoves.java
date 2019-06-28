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

    for (i = 0; i < Parcel.region.length; i++)
      System.out.printf("[%d] "+ Parcel.region[i], i+1);

    do
    {
      System.out.println("Enter region:");
      region = sc.nextInt();
      if (region < 0 || region > 3)
        System.out.println("Invalid Action.");
    } while (region < 0 || region > 3);
    sc.nextLine();

    Parcel box = new Parcel (name, Parcel.region[region]);
    return box;
  }

  public void sendMenu()
  {
    boolean running = true; int choice;
    Parcel box; char menu;
    while (running)
    {
      do
      {
        System.out.println("Please select an option . . .");
        System.out.println("[Step 1] Add recipient");
        System.out.println("[Step 2] Add items");
        System.out.println("[Step 3] Checkout");
        choice = sc.nextInt();
        sc.nextLine();
        if (choice < 1 || choice > 3)
          System.out.println("Invalid Choice.");
      } while (choice < 1 || choice > 3);
      if (choice == 1)
      {
        box = getRecipient();
        System.out.println("Kindly place atleast 1 item.");
        getInputs(itemList, box);
      } else if (choice == 2)
        getInputs(itemList, box);
      else
        Checkout(box); // How to do this?

      do
      {
        System.out.print("Stay in this menu? (y/n): ");
        menu = sc.nextChar();
        if (menu == 'y' || menu == 'Y')
            running = true;
        else if (menu == 'n' || menu == 'N')
            running = false;
        else
            System.out.println("Invalid Action.");
      } while(menu != 'y' || menu != 'Y' || menu != 'n' || menu != 'N');
    }
  }
  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    List itemList = new ArrayList<>();
    JohnnyMoves driver = new JohnnyMoves(sc);
    int choice = 0; boolean main = true; char menu;

    while(main) {
      System.out.println("\n>>> Welcome to JohnnyMoves Services <<<\n");
      do
      {
        System.out.println("Please select an option . . .");
        System.out.println("[1] Send parcel");
        System.out.println("[2] Track a parcel");
        choice = sc.nextInt();
        sc.nextLine();
        if (choice < 1 || choice > 2)
          System.out.println("Invalid Choice.");
      } while (choice < 1 || choice > 2);

      if (choice == 1)
        driver.sendMenu();
      else
        driver.trackParcel();

      do
      {
        System.out.print("Stay in this menu? (y/n): ");
          menu = sc.nextChar();
        if (menu == 'y' || menu == 'Y')
          main = true;
        else if (menu == 'n' || menu == 'N')
          main = false;
        else
          System.out.println("Invalid Action.");
      } while(menu != 'y' || menu != 'Y' || menu != 'n' || menu != 'N');
    }

    sc.close();
  }
}
