import java.util.ArrayList;

public class Manager extends Employee
{
    private ArrayList<Employee> employeeList;

    public Manager(String name, String id, int numYear)
    {
        super(name, id, numYear);
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee person)
    {
        employeeList.add(person);
    }

    public ArrayList<Employee> getEmployees()
    {
        return employeeList;
    }
}
