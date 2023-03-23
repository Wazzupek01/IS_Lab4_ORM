package com.lg;

import com.lg.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class Repository {

    public static void addListToDB(EntityManager em, List<?> objects){
        for(Object o: objects){
            em.persist(o);
        }
    }

    public static void findIdAndUpdatePassword(EntityManager em, Long id){
        User found = em.find(User.class, 10);
        if(found != null){
            found.setPassword("Changed123");
            em.merge(found);
        }
    }

    public static void removeElementById(EntityManager em, Long id, Class<?> type){
        Object found = em.find(type, id);
        em.remove(found);
    }

    public static List<User> getUsersByLastName(EntityManager em, String lastName){
        Query query = em.createQuery("SELECT u FROM User u WHERE u.lastName = '" + lastName + "'");
        List<User> found = query.getResultList();
        return found;
    }
    public static List<User> getWomen(EntityManager em){
        Query query = em.createQuery("SELECT u FROM User u WHERE u.sex = 'FEMALE'");
        List<User> found = query.getResultList();
        return found;
    }
}
