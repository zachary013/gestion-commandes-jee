package ma.fstt.atelier1.dao;

import ma.fstt.atelier1.entities.Client;
import java.util.List;

public interface ClientRepository {
    Client findById(Long id);
    List<Client> findAll();
    void save(Client client);
    void update(Client client);
    void delete(Long id);
}