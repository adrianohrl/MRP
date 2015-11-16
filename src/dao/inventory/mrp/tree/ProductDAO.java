/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.mrp.tree;

import inventory.mrp.tree.Product;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class ProductDAO extends MRPTreeNodeDAO<Product> {

    public ProductDAO(EntityManager em) {
        super(em, Product.class);
    }
    
}
