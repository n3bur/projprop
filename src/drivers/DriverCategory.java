package drivers;

import domini.Category;

import java.util.Scanner;

/**
 * @author Aleix Pellisa Cortiella
 */
public class DriverCategory {

    private static Category c = new Category("c");

    private static void writeMenu() {
        System.out.println("Driver de Category");
        System.out.println("Tria una opcio:");
        System.out.println("1 - GetId");
        System.out.println("0 - Sortir");
    }

    public static void main(String[] args) {
        writeMenu();
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        while (option != 0) {
            switch (option) {
                case (1):
                    testGetId();
                    break;
            }
            System.out.println("Tria una altra opcio o 0 per a sortir");
            option = scan.nextInt();
        }
        scan.close();
    }

    private static void testGetId() {
        System.out.println("El id de la categoria es: " + c.getId());
    }
}
