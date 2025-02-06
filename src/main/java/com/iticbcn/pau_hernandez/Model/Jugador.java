package com.iticbcn.pau_hernandez.Model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Jugador")
public class Jugador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private int idJugador;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "cognoms", nullable = false, length = 100)
    private String cognoms;

    @ManyToOne
    @JoinColumn(name = "id_equip", foreignKey = @ForeignKey(name = "FK_JUGADOR_EQUIP"))
    private Equip equip;

    // Constructor vacío 
    public Jugador() {
    }

    // Constructor con parámetros
    public Jugador(String nom, String cognoms, Equip equip) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.equip = equip;
    }

    // Getters y Setters
    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    // Método toString (opcional, pero útil para depuración)
    @Override
    public String toString() {
        return "Jugador{" +
                "idJugador=" + idJugador +
                ", nom='" + nom + '\'' +
                ", cognoms='" + cognoms + '\'' +
                ", equip=" + (equip != null ? equip.getId_equip() : "null") +
                '}';
    }

    // Métodos equals y hashCode (opcionales, pero recomendados para comparaciones)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return idJugador == jugador.idJugador;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJugador);
    }
}