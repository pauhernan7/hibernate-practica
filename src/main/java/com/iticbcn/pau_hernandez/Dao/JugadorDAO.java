package com.iticbcn.pau_hernandez.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iticbcn.pau_hernandez.Model.Jugador;

public class JugadorDAO {
    private SessionFactory sessionFactory;

    public JugadorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // ✅ CREATE - Crear Jugador
    public void crearJugador(Jugador jugador) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(jugador);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
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
