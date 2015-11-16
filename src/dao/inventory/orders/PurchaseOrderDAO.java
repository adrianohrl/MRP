/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.orders;

import inventory.orders.PurchaseOrder;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class PurchaseOrderDAO extends AbstractOrderDAO<PurchaseOrder> {

    public PurchaseOrderDAO(EntityManager em) {
        super(em, PurchaseOrder.class);
    }
    
}
