package com.github.erfan_davoodi_nasr_hw10_maktab117.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
    private static EntityManagerFactory emf;

    private EntityManagerProvider() {

    }

    public static synchronized EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("jdbc-postgres");
        }

        return emf.createEntityManager();
    }
}
