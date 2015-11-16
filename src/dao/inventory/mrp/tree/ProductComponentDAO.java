/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.mrp.tree;

import inventory.mrp.tree.ProductComponent;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <C>
 */
public abstract class ProductComponentDAO<C extends ProductComponent> extends MRPTreeNodeDAO<C> {

    protected ProductComponentDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }
    
}
