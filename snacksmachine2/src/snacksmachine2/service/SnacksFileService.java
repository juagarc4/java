package snacksmachine2.service;

import snacksmachine2.domain.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SnacksFileService implements ISnacksService {

    private final String FILE_NAME = "./snacksmachine2/snacks.txt";
    private List<Snack> snacks = new ArrayList<>();

    public SnacksFileService() {
        File file = new File(FILE_NAME);
        boolean exists = file.exists();

        try {
            if (exists) {
                this.snacks = getSnacksFromFile();
            } else {
                PrintWriter output = new PrintWriter(new FileWriter(file));
                output.close();
                System.out.println("File created successfully!");
                loadDefaultSnacks();
            }
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    private void loadDefaultSnacks() {
        this.addSnack(new Snack("Chips", 10));
        this.addSnack(new Snack("Cola", 50));
        this.addSnack(new Snack("Sandwich", 120));
    }

    @Override
    public void addSnack(Snack snack) {
        // 1. Add snack to the list in memory
        this.snacks.add(snack);
        // 2. Save snack in the file
        this.addSnackToFile(snack);

    }

    private void addSnackToFile(Snack snack) {
        File file = new File(FILE_NAME);
        boolean append = file.exists();

        try {
            PrintWriter output = new PrintWriter((new FileWriter(file, append)));
            output.println(snack.getAsCsv());
            output.close();
        } catch (Exception e) {
            System.out.println("Error adding snack to a file: " + e.getMessage());
        }

    }

    @Override
    public void showSnacks() {
        String snacksInventory = "";
        for (Snack snack : this.snacks) {
            snacksInventory += snack.toString() + "\n";
        }

        System.out.println("--- Available Snacks ---");
        System.out.println(snacksInventory);
    }

    public List<Snack> getSnacksFromFile() {
        ArrayList<Snack> snacks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (String line : lines) {
                String[] snackData = line.split(",");
                String idSnack = snackData[0]; // not used
                String name = snackData[1];
                double price = Double.parseDouble(snackData[2]);
                Snack snack = new Snack(name, price);
                snacks.add(snack);
            }
        } catch (Exception e) {
            System.out.println("Error getting snacks from file: " + e.getMessage());
            e.printStackTrace();
        }
        return snacks;
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
