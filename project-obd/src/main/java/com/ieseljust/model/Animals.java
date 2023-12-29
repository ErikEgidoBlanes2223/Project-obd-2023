package com.ieseljust.ModelER;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 *
 * @author Erik
 */
@Entity
public class Animals implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date dateOfBirth;
    private int escapeAttempts;
    private boolean neutered;
    private double weightKg;

    @ManyToOne
    @JoinColumn(name = "especies_id")
    private Species species;

    @ManyToOne
    @JoinColumn(name = "propietari_id")
    private Owners owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getEscapeAttempts() {
        return escapeAttempts;
    }

    public void setEscapeAttempts(int escapeAttempts) {
        this.escapeAttempts = escapeAttempts;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public Species getSpeciesId() {
        return species;
    }

    public void setSpeciesId(Species species) {
        this.species = species;
    }

    public Owners getOwner() {
        return owner;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }
    
    
}
