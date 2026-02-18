package zone_fit.data;

import zone_fit.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static zone_fit.connection.DbConnection.getConnection;

public class ClientDAO implements IClientDAO {

    @Override
    public List<Client> clientsList() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM clients ORDER BY id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setMembership(rs.getInt("membership"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving the list of clients: " + e.getMessage());
        }
        return clients;
    }

    @Override
    public boolean searchClientById(Client client) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, client.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setMembership(rs.getInt("membership"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error searching client by id: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO clients(first_name, last_name, membership) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMembership());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        String sql = "UPDATE clients SET first_name=?, last_name=?, membership=? WHERE id=?";
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMembership());
            ps.setInt(4, client.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM clients WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, client.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
        return false;
    }
}
