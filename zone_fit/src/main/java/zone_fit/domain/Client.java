package zone_fit.domain;

import java.util.Objects;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private int membership;

    public Client() {
    }

    public Client(int id) {
        this.id = id;
    }

    public Client(String firstName, String lastName, int membership) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.membership = membership;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMembership() {
        return this.membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.id +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", membership=" + this.membership +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Client client)) return false;
        return id == client.id && this.membership == client.membership && Objects.equals(this.firstName, client.firstName) && Objects.equals(this.lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, membership);
    }
}
