package drivers;

import tests.TestDJSets;
import tests.TestDJSetsCustom;

import java.util.Scanner;

/**
 * Created by pau on 03/05/15.
 */
public class DriverDJSets {

    public static void writeMenu() {
        System.out.println("Class DJSets test driver.");
        System.out.println("Available options:");
        System.out.println("   - 1 Run default test of DJSets' methods.");
        System.out.println("   - 2 Run custom test of DJSets.");
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
                    junit.textui.TestRunner.run(TestDJSets.class);
                    break;
                case (2):
                    TestDJSetsCustom.main(null);
                    break;
                case (0):
                    writeMenu();
                    break;
            }
            System.out.print("Please type your choice [1/2/3]. Type 0 for help.\n");
            option = scan.nextInt();
        }
    }
}
