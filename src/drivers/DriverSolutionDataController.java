package drivers;

import persistencia.SolutionDataController;
import tests.SolutionDataControllerTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ruben Marias
 */

public class DriverSolutionDataController {
    private static void displayMenu() {
        System.out.println("Class SolutionDataController Test Driver");
        System.out.println("Available options:");
        System.out.println("   - 1 Run SolutionDataController Test.");
        System.out.println("   - 2 Exit driver.");
        System.out.println("Please type your choice [1/2].");
    }

    public static void main (String args[]) {
        displayMenu();
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if (option == 1) junit.textui.TestRunner.run(SolutionDataControllerTest.class);
    }
}