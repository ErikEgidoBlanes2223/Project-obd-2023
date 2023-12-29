package com.ieseljust.ModelER;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Erik
 */
@Entity
public class Specializations implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vets vet;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vets getVet() {
        return vet;
    }

    public void setVet(Vets vet) {
        this.vet = vet;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
    
    
}
