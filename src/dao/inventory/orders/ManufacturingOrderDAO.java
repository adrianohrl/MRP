/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.orders;

import inventory.orders.ManufacturingOrder;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class ManufacturingOrderDAO extends AbstractOrderDAO<ManufacturingOrder> {

    public ManufacturingOrderDAO(EntityManager em) {
        super(em, ManufacturingOrder.class);
    }
    
}
