/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import inventory.agents.Machine;
import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class MachineInventory extends Inventory<Machine> {

    public MachineInventory() {
    }

    public MachineInventory(GlobalInventory globalInventory, Machine agent) {
        super(globalInventory, agent);
    }
    
}
