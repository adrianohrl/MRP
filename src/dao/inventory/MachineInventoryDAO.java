/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory;

import inventory.MachineInventory;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class MachineInventoryDAO extends InventoryDAO<MachineInventory> {

    public MachineInventoryDAO(EntityManager em) {
        super(em, MachineInventory.class);
    }
    
}
