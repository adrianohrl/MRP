/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.orders;

import inventory.orders.SaleOrder;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class SaleOrderDAO extends AbstractOrderDAO<SaleOrder> {

    public SaleOrderDAO(EntityManager em) {
        super(em, SaleOrder.class);
    }
    
}
