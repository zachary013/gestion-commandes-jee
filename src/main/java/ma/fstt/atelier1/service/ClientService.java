package ma.fstt.atelier1.service;

import ma.fstt.atelier1.entities.Client;
import java.util.List;

public interface ClientService {
    Client getClientById(Long id);
    void saveClient(Client client);
    void updateClient(Client client);
    void deleteClient(Long id);
    List<Client> getAllClients();
}