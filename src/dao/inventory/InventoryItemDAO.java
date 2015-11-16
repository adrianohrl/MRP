/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory;

import dao.CodeableDAO;
import inventory.InventoryItem;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class InventoryItemDAO extends CodeableDAO<InventoryItem> {

    public InventoryItemDAO(EntityManager em) {
        super(em, InventoryItem.class);
    }
    
}
