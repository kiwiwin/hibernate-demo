package org.kiwi.hibernatedemo;

import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.*;

public class NoteManager {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();
        List<Note> notes = getNotes();
        for (Note note : notes) {
            System.out.println("Q: " + note.getQuestion() + ", A: " + note.getAnswer());
        }
    }

    private static List<Note> getNotes() {
        List<Note> notes = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            notes = (List<Note>) session.createQuery("FROM Note").list();
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return notes;
    }

    private static void initSessionFactory() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println("failed to get the session factory, REASON: " + e.getMessage());
        }
    }
}
