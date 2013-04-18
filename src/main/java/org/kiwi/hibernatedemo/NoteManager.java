package org.kiwi.hibernatedemo;

import org.hibernate.*;

import java.util.*;

public class NoteManager {
    private SessionFactory sessionFactory;

    public NoteManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private void deleteNote(Integer id) {
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

    private void updateAnswer(Integer id, String answer) {
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

    private void deleteNote(Note note) {
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

    private void updateNote(Note note) {
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

    private Note getNote(Integer id) {
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

    private void insertNote(Note note) {
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

    public List<Note> getNotes() {
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
}
