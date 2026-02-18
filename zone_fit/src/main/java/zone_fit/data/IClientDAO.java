package zone_fit.data;

import zone_fit.domain.Client;

import java.util.List;

public interface IClientDAO {
    List<Client> clientsList();

    boolean searchClientById(Client client);

    boolean addClient(Client client);

    boolean updateClient(Client client);

    boolean deleteClient(Client client);
}
