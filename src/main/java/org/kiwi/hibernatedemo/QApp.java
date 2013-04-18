package org.kiwi.hibernatedemo;

import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.*;

public class QApp {
    public static void main(String[] args) {
        NoteManager noteManager = new NoteManager(initSessionFactory());
        List<Note> notes = noteManager.getNotes();
        for (Note note : notes) {
            note.display();
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
