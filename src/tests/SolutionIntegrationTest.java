package tests;

/**
 * @author Ruben Marias
 */

import domini.*;
import persistencia.SolutionDataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SolutionIntegrationTest {
    private static void displayMenu() {
        for (int i = 0; i < 2; i++) System.out.println();
        System.out.println("Available options:");
        System.out.println("   - 1 Create Solution.");
        System.out.println("   - 2 Store Solution (if previously created).");
        System.out.println("   - 3 Display IDS of stored solutions.");
        System.out.println("   - 4 Display number of stored solutions.");
        System.out.println("   - 5 Load solution (if previously stored).");
        System.out.println("   - 6 View Solution (if currently loaded).");
        System.out.println("   - 7 Delete solution.");
        System.out.println("   - 8 Delete all solutions.");
        System.out.println("   - 9 Exit.");
        System.out.println("Please type your choice [1/2/3/4/5/6/7/8/9].");
    }

    private static void cleanFolder() {
        try {
            ArrayList<String> ids = SolutionDataController.solutionIds();
            for (String s : ids) SolutionDataController.deleteSolution(s);
        } catch (Exception e) {
            System.out.println("Error when deleting solution");
        }
    }

    public static void main (String args[]) {
        cleanFolder();
        System.out.println("Class SolutionIntegration Test");
        displayMenu();
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        Solution S = new Solution();
        while (option != 9) {
            try {
                switch (option) {

                    case 1:
                        S = new Solution();
                        S.setTime(16.0);
                        S.setAlg('L');
                        break;

                    case 2:
                        SolutionDomainController.saveSolution(S);
                        break;

                    case 3:
                        ArrayList<String> ids = SolutionDomainController.getSolutionIds();
                        for (String s: ids) System.out.println(s);
                        break;

                    case 4:
                        System.out.println(SolutionDomainController.numSolutions());
                        break;

                    case 5:
                        System.out.println("Enter ID of the Solution to be loaded");
                        Scanner scan2 = new Scanner(System.in);
                        String id = scan2.nextLine();
                        System.out.println("ID read: "+id);
                        S = SolutionDomainController.loadSolution(id);
                        break;

                    case 6:
                        System.out.println(S.getId());
                        System.out.println(S.getAlg());
                        System.out.println(S.getTime());
                        break;

                    case 7:
                        System.out.println("Enter ID of the Solution to be deleted");
                        Scanner scan3 = new Scanner(System.in);
                        id = scan3.nextLine();
                        System.out.println("ID read: "+id);
                        S = SolutionDomainController.loadSolution(id);
                        SolutionDomainController.deleteSolution(S);
                        System.out.println("Enter new ID of the Solution");
                        id = scan3.nextLine();
                        S.setId(id);
                        SolutionDomainController.saveSolution(S);
                        break;

                    case 8:
                        cleanFolder();
                        System.out.println("Solutions deleted");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                displayMenu();
                option = scan.nextInt();
            }
        }
        cleanFolder();
    }
}

