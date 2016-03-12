package drivers;

import domini.Page;

import java.util.Scanner;

/**
 * @author Aleix Pellisa Cortiella
 */
public class DriverPage {

    private static Page p = new Page("p");

    private static void writeMenu() {
        System.out.println("Driver de Page");
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
        System.out.println("El id de la pagina es: " + p.getId());
    }
}
