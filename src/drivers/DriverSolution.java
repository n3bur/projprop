package drivers;

import tests.SolutionTest;

import java.util.Scanner;

public class DriverSolution {

    private static void displayMenu() {
        System.out.println("Class Solution Test Driver");
        System.out.println("Available options:");
        System.out.println("   - 1 Run Solution Test.");
        System.out.println("   - 2 Exit driver.");
        System.out.println("Please type your choice [1/2].");
    }

    public static void main (String args[]) {
        displayMenu();
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if (option == 1) junit.textui.TestRunner.run(SolutionTest.class);
    }

}
