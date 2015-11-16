/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory;

import dao.ReferenceableObjectDAO;
import inventory.GlobalInventoryItem;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class GlobalInventoryItemDAO extends ReferenceableObjectDAO<GlobalInventoryItem> {

    public GlobalInventoryItemDAO(EntityManager em) {
        super(em, GlobalInventoryItem.class);
    }
    
}
