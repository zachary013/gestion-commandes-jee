package ma.fstt.atelier1.dao;

import ma.fstt.atelier1.entities.Produit;
import java.util.List;

public interface ProduitRepository {
    Produit findById(Long id);
    List<Produit> findAll();
    void save(Produit produit);
    void update(Produit produit);
    void delete(Long id);
}