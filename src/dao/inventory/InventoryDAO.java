/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory;

import dao.CodeableDAO;
import inventory.Inventory;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <I>
 */
public abstract class InventoryDAO<I extends Inventory> extends CodeableDAO<I> {

    protected InventoryDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }
    
}
