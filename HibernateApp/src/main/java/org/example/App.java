package org.example;

import org.example.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
            System.out.println("Получили человека из таблицы");

            session.getTransaction().commit();
            // session.close(); - сессия автоматически закрывается после коммита
            System.out.println("Сессия закончилась");

            // Открываем сессию и транзакцию еще раз (в любом другом месте в коде)
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Внутри второй транзакции");

            List<Item> items = session.createQuery("select i from Item i where i.owner.id=:personId", Item.class)
                    .setParameter("personId", person.getId()).getResultList();

            System.out.println(items);

            session.getTransaction().commit();

            System.out.println("Вне второй сессии");
        }
    }
}
