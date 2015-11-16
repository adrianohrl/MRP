/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.orders;

import inventory.orders.Cancellation;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class CancellationDAO extends AbstractTransactionDAO<Cancellation> {

    public CancellationDAO(EntityManager em) {
        super(em, Cancellation.class);
    }
    
}
