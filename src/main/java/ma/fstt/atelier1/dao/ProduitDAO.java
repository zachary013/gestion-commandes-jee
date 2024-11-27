package ma.fstt.atelier1.dao;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import ma.fstt.atelier1.entities.Produit;
import java.util.List;

@ApplicationScoped
@Transactional
public class ProduitDAO implements ProduitRepository {

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
    public Produit findById(Long id) {
        return entityManager.find(Produit.class, id);
    }

    @Override
    public List<Produit> findAll() {
        return entityManager.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.persist(produit);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void update(Produit produit) {
        entityManager.getTransaction().begin();
        entityManager.merge(produit);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Produit produit = findById(id);
        if (produit != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(produit);
            entityManager.getTransaction().commit();
        }
    }
}