package com.iticbcn.pau_hernandez.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iticbcn.pau_hernandez.Model.Equip;
import com.iticbcn.pau_hernandez.Model.Jugador;

public class JugadorDAO {
    private SessionFactory sessionFactory;

    public JugadorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearJugador(Jugador jugador, int idEquip) {
    Transaction transaction = null;
    Session session = null;

    try {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        // Verificar si el equipo existe
        Equip equip = session.get(Equip.class, idEquip);
        if (equip == null) {
            throw new Exception("El equipo con ID " + idEquip + " no existe.");
        }

        jugador.setEquip(equip); // Asocia el jugador al equipo
        session.persist(jugador); // Guarda el jugador en la base de datos

        transaction.commit();
        System.out.println("✅ Jugador creat correctament!");
    } catch (Exception e) {
        if (transaction != null && transaction.getStatus().canRollback()) {
            transaction.rollback();
        }
        System.err.println("⚠ Error en crearJugador: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}


    // ✅ READ - Obtener un jugador por ID
    public Jugador obtenirJugador(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Jugador.class, id);
        }
    }

    // ✅ READ - Obtener todos los jugadores
    public List<Jugador> obtenirTotsElsJugadors() {
        try (Session session = sessionFactory.openSession()) {
            Query<Jugador> query = session.createQuery("from Jugador", Jugador.class);
            return query.list();
        }
    }

    // ✅ UPDATE - Modificar un jugador
    public void actualitzarJugador(Jugador jugador) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(jugador);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // ✅ DELETE - Eliminar un jugador
    public void eliminarJugador(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Jugador jugador = session.get(Jugador.class, id);
            if (jugador != null) {
                session.delete(jugador);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
