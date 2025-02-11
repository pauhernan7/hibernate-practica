package com.iticbcn.pau_hernandez.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iticbcn.pau_hernandez.Model.Classificacio;

public class ClassificacioDAO {
    private SessionFactory sessionFactory;

    public ClassificacioDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // ✅ CREATE - Crear Classificació
    public void crearClassificacio(Classificacio classificacio) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(classificacio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // ✅ READ - Obtener una classificació por ID
    public Classificacio obtenirClassificacio(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Classificacio.class, id);
        }
    }

    // ✅ READ - Obtener todas las classificacions
    public List<Classificacio> obtenirTotesLesClassificacions() {
        try (Session session = sessionFactory.openSession()) {
            Query<Classificacio> query = session.createQuery("from Classificacio", Classificacio.class);
            return query.list();
        }
    }

    // ✅ UPDATE - Modificar una classificació
    public void actualitzarClassificacio(Classificacio classificacio) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(classificacio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // ✅ DELETE - Eliminar una classificació
    public void eliminarClassificacio(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Classificacio classificacio = session.get(Classificacio.class, id);
            if (classificacio != null) {
                session.delete(classificacio);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
