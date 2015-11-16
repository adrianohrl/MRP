/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author adrianohrl
 */
public class DataSource {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mrp_PU");

    private DataSource() {
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
    
    public static void closeEntityManagerFactory() {
        emf.close();
    }
    
}
