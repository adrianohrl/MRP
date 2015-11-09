/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import inventory.agents.Sector;
import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class SectorInventory extends Inventory<Sector> {

    public SectorInventory() {
    }

    public SectorInventory(GlobalInventory globalInventory, Sector agent) {
        super(globalInventory, agent);
    }
    
}
