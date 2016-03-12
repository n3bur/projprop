package drivers;

import tests.TestClique;
import tests.TestCliqueCustom;

import java.util.Scanner;

/**
 * Created by pau on 30/04/15.
 */
public class DriverClique {

    public static void writeMenu() {
        System.out.println("Class Clique Test Driver");
        System.out.println("Available options:");
        System.out.println("   - 1 Run default tests of Clique.getSolution(Graph g).");
        System.out.println("   - 2 Run custom test of Clique.getSolution(Graph g).");
        System.out.println("   - 3 Exit driver.");
        System.out.println("Please type your choice [1/2/3].");
    }

    public static void main(String[] args) {
        writeMenu();
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        while (option != 3) {
            switch (option) {
                case (1):
                    junit.textui.TestRunner.run(TestClique.class);
                    break;
                case(2):
                    TestCliqueCustom.main(null);
                    break;
                case(0):
                    writeMenu();
                    break;
            }
            System.out.print("Please type your choice [1/2/3]. Type 0 for help.\n");
            option = scan.nextInt();
        }
    }
}
