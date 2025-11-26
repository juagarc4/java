package snacksmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnacksMachine {
    static void main(String[] args) {
        snackMachine();
    }

    private static void snackMachine() {
        boolean exit = false;
        Scanner console = new Scanner(System.in);

        List<Snack> products = new ArrayList<>();
        System.out.println("*** Snack Machine - Welcome ***");
        Snacks.showSnacks();
        while (!exit) {
            try {
                int option = showMenu(console);
                exit = runOption(option, console, products);
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }

    }

    private static int showMenu(Scanner console) {
        System.out.print("""
                Menu:
                1. Buy snack
                2. Show ticket
                3. Add new snack
                4. Exit
                Select an option:\t """);

        return Integer.parseInt(console.nextLine());
    }

    private static boolean runOption(int option, Scanner console, List<Snack> products) {
        boolean exit = false;
        switch (option) {
            case 1 -> buySnack(console, products);
            case 2 -> showTicket(products);
            case 3 -> addSnack(console);
            case 4 -> {
                exit = true;
                System.out.println("Thank you. See you soon :)");
            }
            default -> System.out.println("Option not recognised: " + option);
        }
        return exit;
    }

    private static void buySnack(Scanner console, List<Snack> products) {
        System.out.print("Which snack you want to buy (id) ? ");
        int snackId = Integer.parseInt(console.nextLine());
        boolean snackFound = false;
        for (Snack snack : Snacks.getSnacks()) {
            if (snackId == snack.getIdSnack()) {
                products.add(snack);
                System.out.println("Snack added: " + snack);
                snackFound = true;
                break;
            }
        }
        if (!snackFound) {
            System.out.println("Snack Id not found: " + snackId);
        }
    }

    private static void showTicket(List<Snack> products) {
        String ticket = "*** Ticket ***";
        double total = 0.0;
        for (Snack product : products) {
            ticket += "\n\t" + product.getName() + "\t-\t " + product.getPrice();
            total += product.getPrice();

        }
        ticket += "\n\tTotal \t->\t1" + total;
        System.out.print(ticket);
    }

    private static void addSnack(Scanner console) {
        System.out.print("Snack name: ");
        String name = console.nextLine();
        System.out.print("Snack prince: ");
        double price = Double.parseDouble(console.nextLine());
        Snacks.addSnack(new Snack(name, price));
        System.out.println("Snack added properly");
        Snacks.showSnacks();
    }
}
