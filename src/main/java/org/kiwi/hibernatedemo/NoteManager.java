package org.kiwi.hibernatedemo;

import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.*;

public class NoteManager {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        initSessionFactory();

        //code goes from here
    }

    private static void deleteNote(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Note where id = ?");
            query.setParameter(0, id);
            query.executeUpdate();
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

    private static void updateAnswer(Integer id, String answer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Note set answer = ? where id = ?");
            query.setParameter(0, answer);
            query.setParameter(1, id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
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
