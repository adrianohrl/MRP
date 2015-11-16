/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.orders;

import dao.ReferenceableObjectDAO;
import inventory.orders.AbstractOrder;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <O>
 */
public abstract class AbstractOrderDAO<O extends AbstractOrder> extends ReferenceableObjectDAO<O> {

    protected AbstractOrderDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }
    
}
