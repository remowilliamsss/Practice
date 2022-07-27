package org.example;

import org.example.model.Principal;
import org.example.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Principal.class).addAnnotatedClass(School.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();


            // получите любого директора, а затем получите его школу
            Principal principal = session.get(Principal.class, 1);
            System.out.println(principal.getName());

            School school = principal.getSchool();
            System.out.println(school.getSchoolNumber());

            // получите любую школу, а затем получите ее директора
            school = session.get(School.class, 4);
            System.out.println(school.getSchoolNumber());

            principal = school.getPrincipal();
            System.out.println(principal.getName());

            // создайте нового директора и новую школу и свяжите эти сущности
            Principal newPrincipal = new Principal("Viktor", 26);
            School newSchool = new School(777);

            newPrincipal.setSchool(newSchool);
            session.save(newPrincipal);

            // смените директора у существующей школы
            newPrincipal = new Principal("Alex", 43);
            session.save(newPrincipal);

            school.setPrincipal(newPrincipal);
            System.out.println(school.getPrincipal().getName());

            // попробуйте добавить вторую школу для существующего директора и изучите возникающую ошибку
            // school.setPrincipal(session.get(Principal.class, 5));

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
