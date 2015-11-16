/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory;

import inventory.SectorInventory;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class SectorInventoryDAO extends InventoryDAO<SectorInventory> {

    public SectorInventoryDAO(EntityManager em) {
        super(em, SectorInventory.class);
    }
    
}
