package ma.fstt.atelier1.dao;

import ma.fstt.atelier1.entities.LigneDeCommande;
import java.util.List;

public interface LigneDeCommandeRepository {
    LigneDeCommande findById(Long id);
    List<LigneDeCommande> findAll();
    List<LigneDeCommande> findByCommandeId(Long commandeId);
    void save(LigneDeCommande ligneDeCommande);
    void update(LigneDeCommande ligneDeCommande);
    void delete(Long id);
}