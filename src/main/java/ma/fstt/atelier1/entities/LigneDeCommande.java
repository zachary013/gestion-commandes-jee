package ma.fstt.atelier1.entities;

public class LigneDeCommande {
    private int id;
    private int commandeId;
    private int produitId;
    private int quantite;

    // Constructeurs
    public LigneDeCommande() {}

    public LigneDeCommande(int id, int commandeId, int produitId, int quantite) {
        this.id = id;
        this.commandeId = commandeId;
        this.produitId = produitId;
        this.quantite = quantite;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
