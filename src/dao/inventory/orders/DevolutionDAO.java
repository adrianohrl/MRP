/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.orders;

import inventory.orders.Devolution;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class DevolutionDAO extends AbstractTransactionDAO<Devolution> {

    public DevolutionDAO(EntityManager em) {
        super(em, Devolution.class);
    }
    
}
