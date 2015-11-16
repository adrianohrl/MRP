/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.agents;

import inventory.agents.Supplier;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class SupplierDAO extends AgentDAO<Supplier> {

    public SupplierDAO(EntityManager em) {
        super(em, Supplier.class);
    }
    
}
