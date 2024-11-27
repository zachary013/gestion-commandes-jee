package ma.fstt.atelier1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.fstt.atelier1.dao.ProduitRepository;
import ma.fstt.atelier1.entities.Produit;
import java.util.List;

@ApplicationScoped
public class ProduitServiceImpl implements ProduitService {

    @Inject
    private ProduitRepository produitRepository;

    @Override
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public void saveProduit(Produit produit) {
        if (produit.getPrix() <= 0) {
            throw new IllegalArgumentException("Le prix doit être supérieur à 0");
        }
        if (produit.getStock() < 0) {
            throw new IllegalArgumentException("Le stock ne peut pas être négatif");
        }
        produitRepository.save(produit);
    }

    @Override
    public void updateProduit(Produit produit) {
        if (produit.getId() == null) {
            throw new IllegalArgumentException("ID produit requis pour la mise à jour");
        }
        produitRepository.update(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.delete(id);
    }
}