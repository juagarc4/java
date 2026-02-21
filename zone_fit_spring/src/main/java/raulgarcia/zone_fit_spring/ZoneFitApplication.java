package raulgarcia.zone_fit_spring;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import raulgarcia.zone_fit_spring.model.Client;
import raulgarcia.zone_fit_spring.service.IClientService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class ZoneFitApplication implements CommandLineRunner {
    private final IClientService clientService;
    private static final Logger logger = LoggerFactory.getLogger(ZoneFitApplication.class);
    String nl = System.lineSeparator();

    static void main(String[] args) {
        logger.info("Initializing App");
        SpringApplication.run(ZoneFitApplication.class, args);
        logger.info("App finalized");
    }

    @Override
    public void run(String @NonNull ... args) {
        zoneFitApp();
    }

    private void zoneFitApp() {
        boolean exit = false;
        Scanner console = new Scanner(System.in);
        while (!exit) {
            try {
                int option = showMenu(console);
                exit = runOption(option, console);
            } catch (Exception e) {
                logger.error("Error occurred: {}", e.getMessage());
            } finally {
                logger.info(nl);
            }
        }
    }

    private int showMenu(Scanner console) {
        logger.info("""
                *** APPLICATION ZONE FIT (GYM) ****
                -----------------------------------
                1. List clients
                2. Search client
                3. Add client
                4. Modify client
                5. Delete client
                6. Exit""");

        System.out.print("Select an option: ");
        return Integer.parseInt(console.nextLine());
    }

    private boolean runOption(int option, Scanner console) {
        boolean exit = false;
        switch (option) {
            case 1 -> listClients();
            case 2 -> searchClientById(console);
            case 3 -> addClient(console);
            case 4 -> updateClient(console);
            case 5 -> deleteClient(console);
            case 6 -> {
                exit = true;
                logger.info("Thank you. See you soon :)");
            }
            default -> System.out.println("Option not recognised: " + option);
        }
        return exit;
    }

    private void listClients() {
        logger.info("{}--- List of clients ---", nl);
        List<Client> clients = this.clientService.listClients();
        clients.forEach(client -> logger.info(client.toString()));
    }

    private void searchClientById(Scanner console) {
        logger.info("{}--- Search client by Id--", nl);
        System.out.print("Enter a client ID: ");
        int clientId = Integer.parseInt(console.nextLine());
        Client client = this.clientService.searchClientById(clientId);
        logger.info(client.toString());
    }

    private void addClient(Scanner console) {

        logger.info("{}--- Add client--", nl);
        System.out.print("First Name: ");
        String firstName = console.nextLine();
        System.out.print("Last Name: ");
        String lastName = console.nextLine();
        System.out.print("Membership: ");
        int membership = Integer.parseInt(console.nextLine());

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setMembership(membership);
        this.clientService.saveClient(client);
        listClients();
    }

    private void updateClient(Scanner console) {

        logger.info("{}--- Update client--", nl);
        System.out.print("Enter the ID of the user you want to update: ");
        Integer clientId = Integer.parseInt(console.nextLine());
        Client client = clientService.searchClientById(clientId);
        if (client != null) {
            logger.info("{}--- Client to be updated found --", nl);
            logger.info(client.toString());
            logger.info("{}--- Enter new data o let empty to maintain current data --", nl);
            System.out.print("First Name: ");
            String firstName = console.nextLine();
            if (!Objects.equals(firstName, "")) {
                client.setFirstName(firstName);
            }
            System.out.print("Last Name: ");
            String lastName = console.nextLine();
            if (!Objects.equals(lastName, "")) {
                client.setLastName(lastName);
            }
            System.out.print("Membership: ");
            String membership = console.nextLine();
            if (!Objects.equals(membership, "")) {
                client.setMembership(Integer.parseInt(membership));
            }
            logger.info("{}--- The follow client will be updated with the new data --", nl);
            logger.info(client.toString());
            System.out.print("Proceed (Y/N): ");
            String proceedOption = console.nextLine();
            if ("y".equalsIgnoreCase(proceedOption)) {
                this.clientService.saveClient(client);
            } else {
                logger.info("{}INFO: Client update has been canceled. All changes have been ignored", nl);
            }
        }

        listClients();
    }

    private void deleteClient(Scanner console) {
        listClients();
        logger.info("{}--- Delete client--", nl);
        System.out.print("Enter the ID of the user you want to delete: ");
        int clientId = Integer.parseInt(console.nextLine());
        logger.info("{}THIS OPERATION CANNOT BE UNDONE.", nl);
        System.out.print("Proceed with deletion(Y/N): ");
        String proceedOption = console.nextLine();
        if ("y".equalsIgnoreCase(proceedOption)) {
            Client client = this.clientService.searchClientById(clientId);
            this.clientService.deleteClient(client);
        } else {
            logger.info("{}INFO: Client removal has been canceled.", nl);
        }
        listClients();
    }
}
