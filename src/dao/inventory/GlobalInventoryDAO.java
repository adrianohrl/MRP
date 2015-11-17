/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory;

import dao.CodeableDAO;
import inventory.GlobalInventory;
import inventory.agents.Filial;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class GlobalInventoryDAO extends CodeableDAO<GlobalInventory> {

    public GlobalInventoryDAO(EntityManager em) {
        super(em, GlobalInventory.class);
    }
    
    public GlobalInventory find(Filial filial) {
        if (filial == null) {
            return null;
        }
        return find(filial.getName());
    }
    
    public GlobalInventory find(String filialName) {
        return (GlobalInventory) em.createQuery("FROM GlobalInventory gi WHERE gi.filial.name = '" + filialName + "'").getSingleResult();
    }
    
}
