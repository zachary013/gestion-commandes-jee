package ma.fstt.atelier1.service;

import ma.fstt.atelier1.entities.Produit;
import java.util.List;

public interface ProduitService {
    Produit getProduitById(Long id);
    void saveProduit(Produit produit);
    void updateProduit(Produit produit);
    void deleteProduit(Long id);
    List<Produit> getAllProduits();
}