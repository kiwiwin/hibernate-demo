package org.kiwi.hibernatedemo;

import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.*;

public class QApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = initSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Person person = (Person) session.get(Person.class, 1);
            Set notes = person.getNotes();
            for (Object obj : notes) {
                Note note = (Note) obj;
                note.display();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("failed to load person message");
        } finally {
            session.close();
        }
    }

    private static SessionFactory initSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println("failed to get the session factory, REASON: " + e.getMessage());
        }
        return sessionFactory;
    }
}
