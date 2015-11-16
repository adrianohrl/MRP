/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.mrp.tree;

import inventory.mrp.tree.ProductModule;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class ProductModuleDAO extends ProductComponentDAO<ProductModule> {

    public ProductModuleDAO(EntityManager em) {
        super(em, ProductModule.class);
    }
    
}
