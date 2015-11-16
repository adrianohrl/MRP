/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.orders;

import inventory.orders.Delivery;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class DeliveryDAO extends AbstractTransactionDAO<Delivery> {

    public DeliveryDAO(EntityManager em) {
        super(em, Delivery.class);
    }
    
}
