package com.iticbcn.pau_hernandez.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iticbcn.pau_hernandez.Model.Lliga;

public class LligaDAO {
    private SessionFactory sessionFactory;

    public LligaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // ✅ CREATE - Crear Lliga
    public void crearLliga(Lliga lliga) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(lliga);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // ✅ READ - Obtener una lliga por ID
    public Lliga obtenirLliga(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Lliga.class, id);
        }
    }

    // ✅ READ - Obtener todas las lligues
    public List<Lliga> obtenirTotesLesLligues() {
        try (Session session = sessionFactory.openSession()) {
            Query<Lliga> query = session.createQuery("from Lliga", Lliga.class);
            return query.list();
        }
    }

    // ✅ UPDATE - Modificar una lliga
    public void actualitzarLliga(Lliga lliga) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(lliga);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // ✅ DELETE - Eliminar una lliga
    public void eliminarLliga(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Lliga lliga = session.get(Lliga.class, id);
            if (lliga != null) {
                session.delete(lliga);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
