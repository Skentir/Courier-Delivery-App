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

    public String[] displayEmployees()
    {
        return new String[0];
    }
}
