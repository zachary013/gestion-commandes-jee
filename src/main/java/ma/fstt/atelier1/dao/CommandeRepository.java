package ma.fstt.atelier1.dao;

import ma.fstt.atelier1.entities.Commande;
import java.util.List;

public interface CommandeRepository {
    Commande findById(Long id);
    List<Commande> findAll();
    List<Commande> findByClientId(Long clientId);
    void save(Commande commande);
    void update(Commande commande);
    void delete(Long id);
}