package org.kiwi.hibernatedemo;

import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.*;

public class NoteManager {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();
    }

    private static void deleteNote(Note note) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(note);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("delete note failed: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    private static void updateNote(Note note) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(note);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("update note failed: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    private static void printNote(Note note) {
        System.out.println("Q: " + note.getQuestion() + ", A: " + note.getAnswer());
    }

    private static Note getNote(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Note note = null;
        try {
            transaction = session.beginTransaction();
            note = (Note) session.get(Note.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("get note failed: " + e.getMessage());
        } finally {
            session.close();
        }
        return note;
    }

    private static void insertNote(Note note) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(note);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("insert note failed: " + e.getMessage());
        } finally {
            session.close();
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
