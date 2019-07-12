public class Driver
{
    public static void main(String[] args)
    {
        Teller pedro = new Teller("Pedro", "M421", 3);
        Teller juan = new Teller("Juan", "J409", 2);
        Teller juanita = new Teller("Juanita", "L442", 4);
        Teller pedrita = new Teller("Pedrita", "PD112", 1);
        Teller jojo = new Teller("Jojo", "PP492", 3);

        Manager marco = new Manager("Marco", "MC113", 5);
        Manager lola = new Manager("Lola", "LP322", 6);

        marco.addEmployee(pedro);
        marco.addEmployee(pedrita);

        lola.addEmployee(juan);
        lola.addEmployee(juanita);
        lola.addEmployee(jojo);

        Bank BBO = new Bank("BBO");
        Bank PPI = new Bank("PPI");

        BBO.addEmployee(marco);
        BBO.addEmployee(pedro);
        BBO.addEmployee(pedrita);

        PPI.addEmployee(lola);
        PPI.addEmployee(juan);
        PPI.addEmployee(juanita);
        PPI.addEmployee(jojo);

        BBO.displayEmployees();
        PPI.displayEmployees();
    }
}
