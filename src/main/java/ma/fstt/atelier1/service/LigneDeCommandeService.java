package ma.fstt.atelier1.service;

import ma.fstt.atelier1.entities.LigneDeCommande;
import java.util.List;

public interface LigneDeCommandeService {
    LigneDeCommande getLigneDeCommandeById(Long id);
    void saveLigneDeCommande(LigneDeCommande ligneDeCommande);
    void updateLigneDeCommande(LigneDeCommande ligneDeCommande);
    void deleteLigneDeCommande(Long id);
    List<LigneDeCommande> getAllLignesDeCommande();
    List<LigneDeCommande> getLignesDeCommandeByCommandeId(Long commandeId);
}