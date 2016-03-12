package drivers;


import java.util.Scanner;

/**
 * @author Rubén Marías Pérez
 */
public class DriverSolutionDomainController {
    private static void displayMenu() {
            System.out.println("Class SolutionDomainController Test Driver");
            System.out.println("Available options:");
            System.out.println("   - 1 Run SolutionDomainController Test.");
            System.out.println("   - 2 Exit driver.");
            System.out.println("Please type your choice [1/2].");
    }

    public static void main (String args[]) {
        displayMenu();
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if (option == 1) junit.textui.TestRunner.run(tests.SolutionDomainControllerTest.class);
    }
}
