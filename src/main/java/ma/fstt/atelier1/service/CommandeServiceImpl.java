package ma.fstt.atelier1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.fstt.atelier1.dao.CommandeRepository;
import ma.fstt.atelier1.entities.Commande;
import ma.fstt.atelier1.entities.LigneDeCommande;
import java.util.List;

@ApplicationScoped
public class CommandeServiceImpl implements CommandeService {

    @Inject
    private CommandeRepository commandeRepository;

    @Inject
    private ProduitService produitService;

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public List<Commande> getCommandesByClientId(Long clientId) {
        return commandeRepository.findByClientId(clientId);
    }

    @Override
    public void saveCommande(Commande commande) {
        // Vérifier le stock disponible
        for (LigneDeCommande ligne : commande.getLignes()) {
            var produit = produitService.getProduitById(ligne.getProduit().getId());
            if (produit.getStock() < ligne.getQuantite()) {
                throw new IllegalStateException("Stock insuffisant pour " + produit.getNom());
            }
            // Mettre à jour le stock
            produit.setStock(produit.getStock() - ligne.getQuantite());
            produitService.updateProduit(produit);
        }
        commandeRepository.save(commande);
    }

    @Override
    public void updateCommande(Commande commande) {
        if (commande.getId() == null) {
            throw new IllegalArgumentException("ID commande requis pour la mise à jour");
        }
        commandeRepository.update(commande);
    }

    @Override
    public void deleteCommande(Long id) {
        // Restaurer le stock avant suppression
        Commande commande = getCommandeById(id);
        for (LigneDeCommande ligne : commande.getLignes()) {
            var produit = ligne.getProduit();
            produit.setStock(produit.getStock() + ligne.getQuantite());
            produitService.updateProduit(produit);
        }
        commandeRepository.delete(id);
    }
}