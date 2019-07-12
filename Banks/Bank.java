import java.util.ArrayList;

public class Bank
{
    private final String name;
    private ArrayList<Employee> employeeList;

    public Bank(String name)
    {
        this.name = name;
        this.employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee person)
    {
        employeeList.add(person);
    }

    public void displayEmployees()
    {
        System.out.println("BANK: " + name);
        Manager manager = null;
        for (int i = 0; i < employeeList.size(); i++)
        {
            Employee employee = employeeList.get(i);
            if (employee instanceof Manager)
                manager = (Manager) employee;
        }

        if (manager != null)
        {
            System.out.println("Manager:");
            System.out.println(
                manager.getName() + "\t" +
                manager.getID() + "\t" +
                manager.getYears() + "\tPhp " +
                manager.computeSalary());
        }
        else
        {
            System.out.println("This bank has no manager.");
        }

        System.out.println();
        System.out.println("Employees:");
        for (int i = 0; i < employeeList.size(); i++)
        {
            Employee employee = employeeList.get(i);
            if (!(employee instanceof Manager))
            {
                System.out.println(
                    employee.getName() + "\t" +
                    employee.getID() + "\t" +
                    employee.getYears() + "\tPhp " +
                    employee.computeSalary());
            }
        }
        System.out.println();
        System.out.println();
    }
}
