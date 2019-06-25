import java.util.*;

public class JohnnyMoves {

  public void modifyItems(itemList) {

  }
  public void removeItems(itemList) {

  }
  public void addItems(itemList) {
  double length, width, height, weight;

  }

  public void getInputs(List itemList) {
    Scanner sc = new Scanner(System.in);
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
        sc.nextLine();
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

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List itemList = new ArrayList<>();
    JohnnyMoves driver = new JohnnyMoves();
    boolean choice = false, main = true;


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

      while (choice == 1)
        driver.getInputs(itemList);


      while (choice == 2) {

      }

    }
}
