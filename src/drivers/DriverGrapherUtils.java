package drivers;

import domini.GrapherUtils;
import tests.GrapherUtilsTest;

import java.util.Scanner;

/**
 * @author Oriol Munoz Princep
 */
public class DriverGrapherUtils {
    private static void writeMenu() {
        System.out.println("Driver de GrapherUtils");
        System.out.println("Aquesta classe només inclou un mètode per testejar l'algorisme de similaritat de noms");
        System.out.println("L'algorisme ha sigut adaptat d'acord a diversos tests (ja que la similaritat és subjectiva");
        System.out.println("Tria una opció:");
        System.out.println("1 - Introduir dos Strings i veure si són similars segons l'algorisme");
        System.out.println("2 - Tests automàtics");
        System.out.println("0 - Sortir");
    }

    public static void main(String[] args) {
        writeMenu();
        Scanner scan = new Scanner(System.in);
        int opcio = scan.nextInt();
        while (opcio != 0) {
            switch (opcio) {
                case(1):
                    System.out.println("Escriu els dos Strings a comparar");
                    String s1 = scan.next();
                    String s2 = scan.next();
                    testAreSimilar(s1, s2);
                    break;
                case(2):
                    junit.textui.TestRunner.run(GrapherUtilsTest.class);
                    break;
            }
            System.out.println("Tria una altra opció o 0 per a sortir");
            opcio = scan.nextInt();
        }
    }

    private static void testAreSimilar(String s1, String s2) {
        if (GrapherUtils.areSimilar(s1, s2)) System.out.println("Son similars");
        else System.out.println("No son similars");
    }
}
