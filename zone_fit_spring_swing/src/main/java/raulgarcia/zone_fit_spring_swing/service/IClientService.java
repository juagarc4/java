package raulgarcia.zone_fit_spring_swing.service;

import raulgarcia.zone_fit_spring_swing.model.Client;

import java.util.List;

public interface IClientService {
    List<Client> listClients();

    Client searchClientById(Integer clientId);

    void saveClient(Client client);

    void deleteClient(Client client);
}
