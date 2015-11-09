/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.agents;

import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Machine extends Agent {

    public Machine() {
    }

    public Machine(String name) {
        super(name);
    }
    
}
