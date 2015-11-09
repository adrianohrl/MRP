/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.orders;

import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Devolution extends AbstractTransaction {

    public Devolution() {
    }

    public Devolution(String reason) {
        super(reason);
    }
    
}
