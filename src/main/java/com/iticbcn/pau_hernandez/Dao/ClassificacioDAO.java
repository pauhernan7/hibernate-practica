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

    // ✅ Crear una classificació
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

    // ✅ Obtenir una classificació per ID
    public Classificacio obtenirClassificacio(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Classificacio.class, id);
        }
    }

    // ✅ Obtenir totes les classificacions
    public List<Classificacio> obtenirTotesLesClassificacions() {
        try (Session session = sessionFactory.openSession()) {
            Query<Classificacio> query = session.createQuery("from Classificacio", Classificacio.class);
            return query.list();
        }
    }

    // ✅ Obtenir la mitjana de punts de totes les classificacions (HQL amb `AVG`)
    public double obtenirMitjanaPunts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Double> query = session.createQuery("SELECT AVG(punts) FROM Classificacio", Double.class);
            return query.uniqueResult();
        }
    }

    // ✅ Actualitzar una classificació
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

    // ✅ Eliminar una classificació per ID
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
