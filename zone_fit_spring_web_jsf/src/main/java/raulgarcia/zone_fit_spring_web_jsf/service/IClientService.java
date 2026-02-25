package raulgarcia.zone_fit_spring_web_jsf.service;

import raulgarcia.zone_fit_spring_web_jsf.model.Client;

import java.util.List;

public interface IClientService {
    List<Client> listClients();

    Client searchClientById(Integer clientId);

    Client saveClient(Client client);

    void deleteClient(Client client);
}
