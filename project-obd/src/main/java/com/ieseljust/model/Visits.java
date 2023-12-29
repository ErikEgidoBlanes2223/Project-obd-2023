package com.ieseljust.ModelER;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 *
 * @author Erik
 */
@Entity
public class Visits implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date visitDate;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animals animal;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vets vet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Animals getAnimal() {
        return animal;
    }

    public void setAnimal(Animals animal) {
        this.animal = animal;
    }

    public Vets getVet() {
        return vet;
    }

    public void setVet(Vets vet) {
        this.vet = vet;
    }
    
    
}
