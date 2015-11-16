/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory;

import inventory.ClientInventory;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class ClientInventoryDAO extends InventoryDAO<ClientInventory> {

    public ClientInventoryDAO(EntityManager em) {
        super(em, ClientInventory.class);
    }
    
}
