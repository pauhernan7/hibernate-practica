package com.iticbcn.pau_hernandez.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iticbcn.pau_hernandez.Model.Equip;
import com.iticbcn.pau_hernandez.Model.Lliga;

public class EquipDAO {
    private SessionFactory sessionFactory;

    public EquipDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearEquip(Equip equip, int idLliga) {
    Transaction transaction = null;
    Session session = null;

    try {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        // Verificar si la liga existe
        Lliga lliga = session.get(Lliga.class, idLliga);
        if (lliga == null) {
            throw new Exception("La liga con ID " + idLliga + " no existe.");
        }
        
        equip.setLliga(lliga); // Asocia el equipo con la liga
        session.persist(equip); // Guarda el equipo en la base de datos

        transaction.commit();
        System.out.println("✅ Equip creat correctament!");
    } catch (Exception e) {
        if (transaction != null && transaction.getStatus().canRollback()) {
            transaction.rollback();
        }
        System.err.println("⚠ Error en crearEquip: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}


    
    

    // ✅ READ - Obtener un equip por ID
    public Equip obtenirEquip(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Equip.class, id);
        }
    }

    // ✅ READ - Obtener todos los equips
    public List<Equip> obtenirTotsElsEquips() {
        try (Session session = sessionFactory.openSession()) {
            Query<Equip> query = session.createQuery("from Equip", Equip.class);
            return query.list();
        }
    }

    // ✅ UPDATE - Modificar un equip
    public void actualitzarEquip(Equip equip) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(equip);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // ✅ DELETE - Eliminar un equip
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
