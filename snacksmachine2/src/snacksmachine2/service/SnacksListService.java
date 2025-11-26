package snacksmachine2.service;

import snacksmachine2.domain.Snack;

import java.util.ArrayList;
import java.util.List;

public class SnacksListService implements ISnacksService {

    private static final List<Snack> snacks;

    // Static block - initializer
    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Chips", 70));
        snacks.add(new Snack("Cola", 50));
        snacks.add(new Snack("Burger", 120));
    }

    public void addSnack(Snack snack) {
        snacks.add(snack);
    }

    public void showSnacks() {
        String snacksInventory = "";
        for (Snack snack : snacks) {
            snacksInventory += snack.toString() + "\n";
        }

        System.out.println("--- Available Snacks ---");
        System.out.println(snacksInventory);
    }

    public List<Snack> getSnacks() {
        return snacks;
    }
}
