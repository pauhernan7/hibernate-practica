package com.iticbcn.pau_hernandez.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iticbcn.pau_hernandez.Model.Equip;

public class EquipDAO {
    private SessionFactory sessionFactory;

    public EquipDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearEquip(Equip equip) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(equip);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Equip obtenirEquip(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Equip.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Equip> obtenirTotsElsEquips() {
        try (Session session = sessionFactory.openSession()) {
            Query<Equip> query = session.createQuery("from Equip", Equip.class);
            return query.list();
        }
    }

    public void eliminarEquip(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Equip equip = session.get(Equip.class, id);
            if (equip != null) {
                session.delete(equip);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
