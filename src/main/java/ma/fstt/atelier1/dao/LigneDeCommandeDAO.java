package ma.fstt.atelier1.dao;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import ma.fstt.atelier1.entities.LigneDeCommande;
import java.util.List;

@ApplicationScoped
@Transactional
public class LigneDeCommandeDAO implements LigneDeCommandeRepository {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    public void close() {
        if (this.entityManager != null && this.entityManager.isOpen()) {
            this.entityManager.close();
        }
    }

    @Override
    public LigneDeCommande findById(Long id) {
        return entityManager.find(LigneDeCommande.class, id);
    }

    @Override
    public List<LigneDeCommande> findAll() {
        return entityManager.createQuery("SELECT l FROM LigneDeCommande l", LigneDeCommande.class).getResultList();
    }

    @Override
    public List<LigneDeCommande> findByCommandeId(Long commandeId) {
        return entityManager.createQuery(
                        "SELECT l FROM LigneDeCommande l WHERE l.commande.id = :commandeId",
                        LigneDeCommande.class)
                .setParameter("commandeId", commandeId)
                .getResultList();
    }

    @Override
    @Transactional
    public void save(LigneDeCommande ligneDeCommande) {
        entityManager.getTransaction().begin();
        entityManager.persist(ligneDeCommande);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void update(LigneDeCommande ligneDeCommande) {
        entityManager.getTransaction().begin();
        entityManager.merge(ligneDeCommande);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LigneDeCommande ligneDeCommande = findById(id);
        if (ligneDeCommande != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(ligneDeCommande);
            entityManager.getTransaction().commit();
        }
    }
}