package edu.kata.task231.repository;

import edu.kata.task231.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public User findOne(Long id) {
        return (User) entityManager
                .createQuery("SELECT usr FROM User usr WHERE usr.id = ?1")
                .setParameter(1, id)
                .getSingleResult();
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT usr FROM User usr").getResultList();
    }

    public User save(User user) {
        entityManager.getTransaction().begin();
        user = entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user;
    }

    public void remove(Long id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
