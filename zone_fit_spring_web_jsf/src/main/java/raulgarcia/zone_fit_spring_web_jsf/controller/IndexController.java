package raulgarcia.zone_fit_spring_web_jsf.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raulgarcia.zone_fit_spring_web_jsf.model.Client;
import raulgarcia.zone_fit_spring_web_jsf.service.IClientService;

import java.util.List;

@Component
@Data
@ViewScoped
@NoArgsConstructor
public class IndexController {
    private IClientService clientService;
    private List<Client> clients;
    private Client clientSelected;
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    // Injection by Setter: IntelliJ doesn't complain and JSF has its empty constructor.
    @Autowired
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostConstruct
    public void init() {
        loadClients();
    }

    public void loadClients() {
        this.clients = clientService.listClients();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Data reloaded."));
    }

    public void addClient() {
        this.clientSelected = new Client();
    }

    public void editClient(Client client) {
        this.clientSelected = client;
    }

    public void deleteClient() {
        if (this.clientSelected != null) {
            this.clientService.deleteClient(this.clientSelected);
            this.clients.remove(this.clientSelected);
            this.clientSelected = null;
            PrimeFaces.current().ajax().update("form-clients:messages", "form-clients:table-clients");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Deleted", "Client deleted successfully"));
        }
    }

    public void saveClient() {
        Client clientSaved = this.clientService.saveClient(this.clientSelected);
        if (!this.clients.contains(clientSaved)) {
            this.clients.add(clientSaved);
        }
        this.clientSelected = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved", "Client saved successfully!"));

        PrimeFaces.current().executeScript("PF('clientDialog').hide()");
        PrimeFaces.current().ajax().update("form-clients:messages", "form-clients:table-clients");
    }
}
