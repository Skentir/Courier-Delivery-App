import java.util.*;

public class JohnnyMoves {

  public void Compute(Parcel box) {
      // To be coded soon
  }

  public void removeItems(List itemList, Parcel box)
  {
    JohnnyMoves driver = new JohnnyMoves();
    int choice = 0, numItems = box.items.size();
    if (numItems != 0)
    {
      itemList.displayItems();
      do
      {
        System.out.println("\nWhich item are you removing?");
        System.out.printf("Choice: ");
        choice = driver.sc.nextInt();
        choice--; // Indexing from 0 to n-1
      } while(choice < 0 || choice >= numItems);
      box.items.remove(choice);
      driver.sc.nextLine();
    } else
      System.out.println("Box is empty. Add items first.");
  }

  public void addInsurance(Parcel box)
  {
    JohnnyMoves driver = new JohnnyMoves();
    boolean false; char reply;
    do
    {
      System.out.println("Do you want to insure your parcel? (y/n)");
      reply = driver.sc.nextChar();
    } while(reply != 'y' || reply != 'Y' || reply != 'n' || reply != 'N');
    if (reply == 'y' || reply == 'Y')
      box.setInsurance(true);
    else
      box.setInsurance(false);
  }

  public void addIrregular(List itemList)
  {
    JohnnyMoves driver = new JohnnyMoves();
    double length, width, height, weight;

    do
    {
      System.out.print("\nEnter length of document in inches: ");
      length = driver.sc.nextDouble();
      System.out.print("\nEnter width of document in inches: ");
      width = driver.sc.nextDouble();
      System.out.print("\nEnter height of document in inches: ");
      height = driver.sc.nextDouble();
    } while (length < 0 || width < 0 || height);
    weight = length * width * height / 305;

    itemList.add(new IrregularProduct(length, width, height, weight));
  }

  public void addRegular(List itemList)
  {
    JohnnyMoves driver = new JohnnyMoves();
    double dimension, weight;

    do
    {
      System.out.print("\nEnter dimension of product in inches: ");
      dimension = driver.sc.nextDouble();
    } while (dimension < 0);
    weight = Math.pow(dimension, 3)/305;
    itemList.add(new RegularProduct(dimension, dimension, dimension, weight));
  }

  public void addDocument(List itemList)
  {
    JohnnyMoves driver = new JohnnyMoves();
    int pages = -1;
    double length, width;
    do
    {
      System.out.print("\nEnter number of pages: ");
      pages = driver.sc.nextInt();
    } while (pages < 0);

    do {
      System.out.print("\nEnter length of document: ");
      length = driver.sc.nextDouble();
      System.out.print("\nEnter width of document: ");
      width = driver.sc.nextDouble();
    } while (length < 0 || width < 0);
    itemList.add(new Document(pages, length, width));
  }

  public void addItems(List itemList)
  {
    JohnnyMoves driver = new JohnnyMoves();
    int choice = 0;
    do
    {
      System.out.println("What type of item item are you gonna add?");
      System.out.println("[1] Document");
      System.out.println("[2] Regular");
      System.out.println("[3] Irregular");
      System.out.printf("Choice: ");
      choice = driver.sc.nextInt();
    } while(choice < 1 || choice > 3);
    sc.nextLine();

    switch(choice)
    {
      case 1: // Document
          driver.addDocument(itemList); break;
      case 2:
          driver.addRegular(itemList); break;
      case 3:
          driver.addIrregular(itemList); break;
    }
  }

  public void getInputs(List itemList, Parcel box) {
    JohnnyMoves driver = new JohnnyMoves();
    boolean running = true; int choice = 0; char menu;
    while (running) {
      do
      {
        System.out.println("\nChooose an option:");
        System.out.println("[1] Add an item");
        System.out.println("[2] Remove an item");
        System.out.println("[3] Modify an item");
        System.out.println("[4] Add Insurance to Parcel");
        choice = sc.nextInt();
        driver.sc.nextLine();
        if(choice < 1 || choice > 4)
          System.out.println("Invalid Action.");
      } while(running || choice < 1 || choice > 3)

      switch (choice)
      {
        case 1: driver.addItems(itemList); break;
        case 2: driver.removeItems(itemList); break;
        case 3: driver.modifyItems(itemList); break;
        case 4: driver.setInsurance(box); break;
      }

      do
      {
        System.out.print("Stay in this menu? (y/n): ");
        menu = driver.sc.nextChar();
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
    JohnnyMoves driver = new JohnnyMoves();
    String name; int region, i;
    do
    {
      System.out.println("Enter name of recipient:");
      name = driver.sc.nextLine();
      if (name.length() < 4)
        System.out.println("Name too short!");
      else if (name.length() > 30)
      System.out.println("Name too long!");
    } while (name.length() < 4 || name.length() > 30);

    for (i = 0; i < Parcel.region.size(); i++)
      System.out.printf("[%d] "+ Parcel.region[i], i+1);

    do
    {
      System.out.println("Enter region:");
      region = driver.sc.nextInt();
      if (region < 0 || region > 3)
        System.out.println("Invalid Action.");
    } while (region < 0 || region > 3);
    driver.sc.nextLine();

    Parcel box = new Parcel (name, region);
    return box;
  }

  public void sendMenu()
  {
      JohnnyMoves driver = new JohnnyMoves();
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
          choice = driver.sc.nextInt();
          driver.sc.nextLine();
          if (choice < 1 || choice > 3)
            System.out.println("Invalid Choice.");
        } while (choice < 1 || choice > 3);
        if (choice == 1)
        {
            box = driver.getRecipient();
            System.out.println("Kindly place atleast 1 item.");
            driver.getInputs(itemList, box);
        } else if (choice == 2)
            driver.getInputs(itemList, box);
        else
            driver.Checkout(box); // How to do this?

        do
        {
          System.out.print("Stay in this menu? (y/n): ");
          menu = driver.sc.nextChar();
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
    JohnnyMoves driver = new JohnnyMoves();
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
