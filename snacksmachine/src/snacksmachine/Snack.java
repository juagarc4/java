package snacksmachine;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {
    private static int contadorSnacks = 0;
    private final int idSnack;
    private String name;
    private double price;

    public Snack() {
        this.idSnack = ++Snack.contadorSnacks;
    }

    public Snack(String name, double price) {
        this(); // Call to the constructor internally
        this.name = name;
        this.price = price;
    }

    public static int getContadorSnack() {
        return contadorSnacks;
    }

    public int getIdSnack() {
        return this.idSnack;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof snacksmachine2.domain.Snack snack)) return false;
        return getIdSnack() == snack.getIdSnack() && Double.compare(getPrice(), snack.getPrice()) == 0 && Objects.equals(getName(), snack.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdSnack(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return "Snack {" +
                "idSnack=" + idSnack +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


}
