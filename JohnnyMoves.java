import java.util.*;

public class JohnnyMoves {

  public void modifyItems(List itemList) {

  }
  public void removeItems(List itemList) {

  }

  public void addIrregular(List itemList)
  {
    JohnnyMoves driver = new JohnnyMoves();
    double length, width, height, weight;

    do {
      System.out.print("\nEnter length of document: ");
      length = driver.sc.nextDouble();
      System.out.print("\nEnter width of document: ");
      width = driver.sc.nextDouble();
    } while (length < 0 || width < 0);

    do {
      System.out.print("\nEnter height of document: ");
      height = driver.sc.nextDouble();
      System.out.print("\nEnter weight of document: ");
      weight = driver.sc.nextDouble();
    } while (height < 0 || weight < 0 || );

    itemList.add(new IrregularProduct(length, width, heigh, weight));
  }

  public void addRegular(List itemList)
  {
    JohnnyMoves driver = new JohnnyMoves();
    double length, width, height, weight;

    do {
      System.out.print("\nEnter length of document: ");
      length = driver.sc.nextDouble();
      System.out.print("\nEnter width of document: ");
      width = driver.sc.nextDouble();
    } while (length < 0 || width < 0);

    do {
      System.out.print("\nEnter height of document: ");
      height = driver.sc.nextDouble();
      System.out.print("\nEnter weight of document: ");
      weight = driver.sc.nextDouble();
    } while (height < 0 || weight < 0 || );

    itemList.add(new RegularProduct(length, width, heigh, weight));
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

  public void getInputs(List itemList) {
    JohnnyMoves driver = new JohnnyMoves();
    boolean running = true; int choice = 0; char menu;
    while (running) {
      do
      {
        System.out.println("\nChooose an option:");
        System.out.println("[1] Add an item");
        System.out.println("[2] Remove an item");
        System.out.println("[3] Modify an item");
        choice = sc.nextInt();
        driver.sc.nextLine();
        if(choice < 1 || choice > 3)
          System.out.println("Invalid Action.");
      } while(running || choice < 1 || choice > 3)

      switch (choice)
      {
        case 1: driver.addItems(itemList); break;
        case 2: driver.removeItems(itemList); break;
        case 3: driver.modifyItems(itemList); break;
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

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List itemList = new ArrayList<>();
    JohnnyMoves driver = new JohnnyMoves();
    int choice = 0; boolean main = true;


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
      } while (choice != 1 || choice != 2);

      choice == 1 ? driver.getInputs(itemList): driver.trackParcel();
    }

    sc.close();
}
