package raulgarcia.zone_fit_spring_web_jsf.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import raulgarcia.zone_fit_spring_web_jsf.model.Client;
import raulgarcia.zone_fit_spring_web_jsf.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Override
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client searchClientById(Integer clientId) {
        return clientRepository
                .findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client with id " + clientId + " not found."));
    }

    @Override
    public Client saveClient(Client client) {
        try {
            Client clientSaved = clientRepository.save(client);
            logger.info("Client saved successfully");
            return clientSaved;
        } catch (Exception e) {
            throw new RuntimeException("Error saving client" + e.getMessage());
        }
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
        try {
            clientRepository.delete(client);
            logger.info("Client deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting client" + e.getMessage());
        }
    }
}
