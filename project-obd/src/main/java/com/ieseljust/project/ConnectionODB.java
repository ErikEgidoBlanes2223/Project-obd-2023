package com.ieseljust.proyectoodb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


/**
 *
 * @author Erik
 */
public class ConnectionODB {

    private EntityManagerFactory emf;
    private EntityManager em;
    private String nombreDB;

    public ConnectionODB(String nombre) {
        this.emf = null;
        this.em = null;
        this.nombreDB = nombre;
    }

    public void conectar() {
        emf = Persistence.createEntityManagerFactory(nombreDB);
        try {
            em = emf.createEntityManager();
        } catch (PersistenceException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void desconectar() {
        if (this.em != null) {
            this.em.close();
        }
        if (this.emf != null) {
            this.emf.close();
        }
        this.em = null;
        this.emf = null;
    }

    public EntityManager getEM() {
        if (em == null) {
            conectar();
        }
        return em;
    }

}
