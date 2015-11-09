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
public class Supplier extends Agent {

    public Supplier() {
    }

    public Supplier(String name) {
        super(name);
    }
    
}
