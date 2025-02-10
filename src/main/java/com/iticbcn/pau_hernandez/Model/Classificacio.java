package com.iticbcn.pau_hernandez.Model;

public class Classificacio {
    private Long id_classificacio;
    private Equip equip;
    private int punts;
    private int partits_jugats;
    private int victories;

    // Constructor vacío (requerido por Hibernate)
    public Classificacio() {}

    // Getters y Setters
    public Long getId_classificacio() {
        return id_classificacio;
    }

    public void setId_classificacio(Long id_classificacio) {
        this.id_classificacio = id_classificacio;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getPartits_jugats() {
        return partits_jugats;
    }

    public void setPartits_jugats(int partits_jugats) {
        this.partits_jugats = partits_jugats;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }
}
