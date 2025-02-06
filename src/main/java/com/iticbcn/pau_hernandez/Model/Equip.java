package com.iticbcn.pau_hernandez.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Equip")
public class Equip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_equip;

    @Column(nullable = false)
    private String nom_equip;

    @Column(nullable = false)
    private String ciutat;

    @ManyToOne
    @JoinColumn(name = "id_lliga", nullable = false)
    private Lliga lliga;


    // Getters y Setters


    public int getId_equip() {
        return id_equip;
    }

    public void setId_equip(int id_equip) {
        this.id_equip = id_equip;
    }

    public String getNom_equip() {
        return nom_equip;
    }

    public void setNom_equip(String nom_equip) {
        this.nom_equip = nom_equip;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public Lliga getLliga() {
        return lliga;
    }

    public void setLliga(Lliga lliga) {
        this.lliga = lliga;
    }

}

