package ma.fstt.atelier1.dao;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import ma.fstt.atelier1.entities.Commande;
import java.util.List;

@ApplicationScoped
@Transactional
public class CommandeDAO implements CommandeRepository {

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
    public Commande findById(Long id) {
        return entityManager.find(Commande.class, id);
    }

    @Override
    public List<Commande> findAll() {
        return entityManager.createQuery("SELECT c FROM Commande c", Commande.class).getResultList();
    }

    @Override
    public List<Commande> findByClientId(Long clientId) {
        return entityManager.createQuery(
                        "SELECT c FROM Commande c WHERE c.client.id = :clientId",
                        Commande.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }

    @Override
    @Transactional
    public void save(Commande commande) {
        entityManager.getTransaction().begin();
        entityManager.persist(commande);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void update(Commande commande) {
        entityManager.getTransaction().begin();
        entityManager.merge(commande);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Commande commande = findById(id);
        if (commande != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(commande);
            entityManager.getTransaction().commit();
        }
    }
}