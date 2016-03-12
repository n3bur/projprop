package drivers;

import tests.SolutionIntegrationTest;
import java.util.Scanner;

/**
 * @author Ruben Marias
 */
public class DriverSolutionIntegration {
    private static void displayMenu() {
        System.out.println("Class SolutionIntegration Test Driver");
        System.out.println("Available options:");
        System.out.println("   - 1 Run SolutionIntegration Test.");
        System.out.println("   - 2 Exit driver.");
        System.out.println("Please type your choice [1/2].");
    }

    public static void main (String args[]) {
        displayMenu();
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if (option == 1) SolutionIntegrationTest.main(null);
    }
}
