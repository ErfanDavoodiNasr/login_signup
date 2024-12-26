package com.github.erfan_davoodi_nasr_hw10_maktab117.repository.impl;

import com.github.erfan_davoodi_nasr_hw10_maktab117.exception.NotUniquePhoneNumberException;
import com.github.erfan_davoodi_nasr_hw10_maktab117.model.User;
import com.github.erfan_davoodi_nasr_hw10_maktab117.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static com.github.erfan_davoodi_nasr_hw10_maktab117.util.EntityManagerProvider.getEntityManager;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User save(User user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (user.getId() == null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new NotUniquePhoneNumberException("entity info should be unique");
        } finally {
            em.close();
        }
        return user;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber",
                    User.class
            );
            query.setParameter("phoneNumber", phoneNumber);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
