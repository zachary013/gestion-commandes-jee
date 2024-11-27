package ma.fstt.atelier1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.fstt.atelier1.dao.ClientRepository;
import ma.fstt.atelier1.entities.Client;
import java.util.List;

@ApplicationScoped
public class ClientServiceImpl implements ClientService {

    @Inject
    private ClientRepository clientRepository;

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void saveClient(Client client) {
        if (client.getEmail() == null || !client.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email invalide");
        }
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client) {
        if (client.getId() == null) {
            throw new IllegalArgumentException("ID client requis pour la mise à jour");
        }
        clientRepository.update(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.delete(id);
    }
}