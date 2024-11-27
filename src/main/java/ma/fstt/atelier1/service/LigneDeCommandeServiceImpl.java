package ma.fstt.atelier1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.fstt.atelier1.dao.LigneDeCommandeRepository;
import ma.fstt.atelier1.entities.LigneDeCommande;
import java.util.List;

@ApplicationScoped
public class LigneDeCommandeServiceImpl implements LigneDeCommandeService {

    @Inject
    private LigneDeCommandeRepository ligneDeCommandeRepository;

    @Override
    public LigneDeCommande getLigneDeCommandeById(Long id) {
        return ligneDeCommandeRepository.findById(id);
    }

    @Override
    public List<LigneDeCommande> getAllLignesDeCommande() {
        return ligneDeCommandeRepository.findAll();
    }

    @Override
    public List<LigneDeCommande> getLignesDeCommandeByCommandeId(Long commandeId) {
        return ligneDeCommandeRepository.findByCommandeId(commandeId);
    }

    @Override
    public void saveLigneDeCommande(LigneDeCommande ligneDeCommande) {
        if (ligneDeCommande.getQuantite() <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à 0");
        }
        if (ligneDeCommande.getPrix() <= 0) {
            throw new IllegalArgumentException("Le prix doit être supérieur à 0");
        }
        ligneDeCommandeRepository.save(ligneDeCommande);
    }

    @Override
    public void updateLigneDeCommande(LigneDeCommande ligneDeCommande) {
        if (ligneDeCommande.getId() == null) {
            throw new IllegalArgumentException("ID ligne de commande requis pour la mise à jour");
        }
        ligneDeCommandeRepository.update(ligneDeCommande);
    }

    @Override
    public void deleteLigneDeCommande(Long id) {
        ligneDeCommandeRepository.delete(id);
    }
}