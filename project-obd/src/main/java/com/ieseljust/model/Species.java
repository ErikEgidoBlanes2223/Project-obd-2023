package com.ieseljust.ModelER;

import java.io.Serializable;
import javax.persistence.*;


/**
 *
 * @author Erik
 */
@Entity
public class Species implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

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
    
    
}
