package snacksmachine;

import java.util.ArrayList;
import java.util.List;

public class Snacks {

    private static final List<Snack> snacks;

    // Static block - initializer
    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Chips", 70));
        snacks.add(new Snack("Cola", 50));
        snacks.add(new Snack("Burger", 120));
    }

    public static void addSnack(Snack snack) {
        snacks.add(snack);
    }

    public static void showSnacks() {
        String snacksInventory = "";
        for (Snack snack : snacks) {
            snacksInventory += snack.toString() + "\n";
        }

        System.out.println("--- Available Snacks ---");
        System.out.println(snacksInventory);
    }

    public static List<Snack> getSnacks() {
        return snacks;
    }
}
