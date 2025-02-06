package com.iticbcn.pau_hernandez.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lliga")
public class Lliga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_lliga;

    @Column(nullable = false, unique = true)
    private String nom_lliga;

    @Column(nullable = false)
    private String temporada;

    @OneToMany(mappedBy = "lliga", cascade = CascadeType.ALL)
    private List<Equip> equips;


    // Getters y Setters

    public int getId_lliga() {
        return id_lliga;
    }

    public void setId_lliga(int id_lliga) {
        this.id_lliga = id_lliga;
    }

    public String getNom_lliga() {
        return nom_lliga;
    }

    public void setNom_lliga(String nom_lliga) {
        this.nom_lliga = nom_lliga;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public List<Equip> getEquips() {
        return equips;
    }

    public void setEquips(List<Equip> equips) {
        this.equips = equips;
    }

    
}
