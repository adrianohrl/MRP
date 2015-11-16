/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.mrp.tree;

import inventory.mrp.tree.ProductItem;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class ProductItemDAO extends ProductComponentDAO<ProductItem> {

    public ProductItemDAO(EntityManager em) {
        super(em, ProductItem.class);
    }
    
}
