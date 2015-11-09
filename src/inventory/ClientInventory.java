/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import inventory.agents.Client;
import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class ClientInventory extends Inventory<Client> {

    public ClientInventory() {
    }

    public ClientInventory(GlobalInventory globalInventory, Client agent) {
        super(globalInventory, agent);
    }
    
}
