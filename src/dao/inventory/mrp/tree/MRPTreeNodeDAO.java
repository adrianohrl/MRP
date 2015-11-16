/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.mrp.tree;

import dao.CodeableDAO;
import inventory.mrp.tree.MRPTreeNode;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <N>
 */
public abstract class MRPTreeNodeDAO<N extends MRPTreeNode> extends CodeableDAO<N> {

    protected MRPTreeNodeDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }
    
}
