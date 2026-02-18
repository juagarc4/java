package zone_fit.presentation;

import zone_fit.data.ClientDAO;
import zone_fit.data.IClientDAO;
import zone_fit.domain.Client;

import java.util.List;
import java.util.Scanner;

public class ZoneFit {
    static void main() {
        zoneFit();
    }

    private static void zoneFit() {
        boolean exit = false;
        Scanner console = new Scanner(System.in);
        IClientDAO clientDAO = new ClientDAO();
        System.out.println("*** Gym Administration - Welcome ***");
        while (!exit) {
            try {
                int option = showMenu(console);
                exit = runOption(option, console, clientDAO);
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
                1. List clients
                2. Search client
                3. Add client
                4. Modify client
                5. Delete client
                6. Exit
                Select an option:\s""");

        return Integer.parseInt(console.nextLine());
    }

    private static boolean runOption(int option, Scanner console, IClientDAO clientDAO) {
        boolean exit = false;
        switch (option) {
            case 1 -> listClients(clientDAO);
            case 2 -> searchClientById(console, clientDAO);
            case 3 -> addClient(console, clientDAO);
            case 4 -> modifyClient(console, clientDAO);
            case 5 -> deleteClient(console, clientDAO);
            case 6 -> {
                exit = true;
                System.out.println("Thank you. See you soon :)");
            }
            default -> System.out.println("Option not recognised: " + option);
        }
        return exit;
    }

    private static void listClients(IClientDAO clientDAO) {
        List<Client> clients = clientDAO.clientsList();

        System.out.println("Client list");
        System.out.println("-----------------");
        clients.forEach(System.out::println);
    }

    private static void searchClientById(Scanner console, IClientDAO clientDAO) {
        System.out.println("Search client");
        System.out.println("-----------------");
        System.out.print("Enter the client ID:");
        int clientId = Integer.parseInt(console.nextLine());
        Client client = new Client(clientId);
        boolean found = clientDAO.searchClientById(client);
        if (found) {
            System.out.println("Client found: " + client);
        } else {
            System.out.println("Client with id " + clientId + " not found.");
        }
    }

    private static void addClient(Scanner console, IClientDAO clientDAO) {
        System.out.println("Add client");
        System.out.println("-----------------");
        System.out.print("First Name: ");
        String firstName = console.nextLine();
        System.out.print("Last Name: ");
        String lastName = console.nextLine();
        System.out.print("Membership: ");
        int membership = Integer.parseInt(console.nextLine());
        Client client = new Client(firstName, lastName, membership);
        boolean added = clientDAO.addClient(client);
        if (added) {
            System.out.println("Client added successfully: " + client);
        }
    }

    private static void modifyClient(Scanner console, IClientDAO clientDAO) {
        System.out.println("Modify client");
        System.out.println("-----------------");
        System.out.print("Enter ID of the client: ");
        int clientId = Integer.parseInt(console.nextLine());
        Client client = new Client(clientId);
        boolean found = clientDAO.searchClientById(client);
        if (found) {
            System.out.print("First Name: ");
            String firstName = console.nextLine();
            System.out.print("Last Name: ");
            String lastName = console.nextLine();
            System.out.print("Membership: ");
            int membership = Integer.parseInt(console.nextLine());
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setMembership(membership);
            boolean updated = clientDAO.updateClient(client);
            if (updated) {
                System.out.println("Client updated successfully: " + client);
            }
        } else {
            System.out.println("Client with id " + clientId + " not found.");
        }
    }

    private static void deleteClient(Scanner console, IClientDAO clientDAO) {
        System.out.println("Delete client");
        System.out.println("-----------------");
        System.out.print("Enter the client ID: ");
        int clientId = Integer.parseInt(console.nextLine());
        Client client = new Client(clientId);
        boolean found = clientDAO.searchClientById(client);
        if (found) {
            boolean deleted = clientDAO.deleteClient(client);
            if (deleted) {
                System.out.println("Client with id " + clientId + " deleted.");
            }
        } else {
            System.out.println("Client with id " + clientId + " not found.");
        }
    }
}
