/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.orders;

import dao.CodeableDAO;
import inventory.orders.AbstractTransaction;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <T>
 */
public abstract class AbstractTransactionDAO<T extends AbstractTransaction> extends CodeableDAO<T> {

    protected AbstractTransactionDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }
    
}
