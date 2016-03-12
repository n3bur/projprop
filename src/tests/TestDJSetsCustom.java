package tests;

import domini.DJSets;

import java.util.Scanner;

/**
 * Created by pau on 03/05/15.
 */
public class TestDJSetsCustom {

    private static void showMenu() {
        System.out.println("Instructions to try the methods of the DJSets ds created.");
        System.out.println("size: ds.size().");
        System.out.println("add: ds.add().");
        System.out.println("find a: ds.find(a).");
        System.out.println("union a b: ds.union(a, b)");
        System.out.println("show: print the whole ds, i.e. ds.find(i) for every 0 <= i < ds.size().");
        System.out.println("help: show this menu.");
        System.out.println("exit: exit this test.");
    }

    public static void main(String[] args) {

        System.out.println("We'll create a DJSets of n elements. Write an integer n.");
        Scanner input = new Scanner(System.in);
        DJSets ds = new DJSets(input.nextInt());
        System.out.println("Write 'help' to see the options available.");
        String inst = input.next();
        while (!inst.equals("stop")) {
            switch (inst) {
                case "help":
                    showMenu();
                    inst = input.next();
                    break;
                case "size":
                    System.out.println("Size: " + ds.size());
                    inst = input.next();
                    break;
                case "add":
                    System.out.println("ds now has size " + ds.size());
                    inst = input.next();
                    break;
                case "show":
                    for (int i = 0; i < ds.size(); ++i) System.out.print(i + ": " + ds.find(i) + "  ");
                    System.out.println();
                    inst = input.next();
                    break;
                case "find":
                    int n = input.nextInt();
                    try {
                        System.out.println(n + "belongs to the set with root " + ds.find(n));
                    } catch (IllegalArgumentException iae) {
                        iae.printStackTrace();
                    }
                    inst = input.next();
                    break;
                case "union":
                    int a = input.nextInt();
                    int b = input.nextInt();
                    if (a == b) System.out.println("Numbers must be different.");
                    ds.union(ds.find(a), ds.find(b));
                    inst = input.next();
                    break;
                case "exit":
                    System.out.println("Exiting custom test. You will go back to the driver menu.");
                    return;
                default:
                    inst = input.next();
                    break;
            }
        }
    }
}
