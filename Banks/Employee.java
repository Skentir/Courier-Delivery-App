public class Employee {
  private final String name;
  private final String id;
  private int numYear;
  private double salary;

  public Employee(String name, String id, int numYear)
  {
    this.name = name;
    this.id = id;
    this.numYear = numYear;
  }

  public double computeSalary()
  {
    if (this instanceof Manager)
    {
      this.salary = numYear * ((Manager) this).getEmployees().size() * 300;
      return salary;
    }
    else
    {
      this.salary = numYear * 500;
      return salary;
    }
  }

  public int getYears()
  {
    return numYear;
  }

  public String getName()
  {
    return name;
  }

  public String getID()
  {
    return id;
  }

}
