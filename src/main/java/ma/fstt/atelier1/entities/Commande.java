package ma.fstt.atelier1.entities;

import java.util.Date;

public class Commande {
    private int id;
    private Date date;
    private double total;
    private String clientId;

    // Constructeurs
    public Commande() {}

    public Commande(int id, Date date, double total, String clientId) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.clientId = clientId;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
