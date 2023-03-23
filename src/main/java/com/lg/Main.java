package com.lg;

import com.lg.entities.Role;
import com.lg.entities.Sex;
import com.lg.entities.User;
import com.lg.entities.UsersGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("JPA project");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Repository.addListToDB(em, Constants.users);
        Repository.addListToDB(em, Constants.roles);

        Repository.findIdAndUpdatePassword(em, 1L);

        Repository.removeElementById(em, 5L, Role.class);

        List<User> kowalskis = Repository.getUsersByLastName(em, "Kowalski");
        System.out.println("Kowalscy");
        printUsers(kowalskis);

        List<User> women = Repository.getWomen(em);
        System.out.println("Kobiety");
        printUsers(women);

        User u = new User(null, "UserWithRole", "password", "Jan", "Kowalski", Sex.MALE);
        u.addRole(em.find(Role.class, 1L));
        u.addRole(em.find(Role.class, 2L));
        em.persist(u);

        UsersGroup group = new UsersGroup(null, kowalskis);
        em.persist(group);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    private static void printUsers(List<User> users){
        if(users.isEmpty()){
            System.out.println("No one here");
            return;
        }
        for(User u: users){
            System.out.println(u.toString());
        }
    }
}
