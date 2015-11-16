/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.agents;

import inventory.agents.Machine;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class MachineDAO extends AgentDAO<Machine> {

    public MachineDAO(EntityManager em) {
        super(em, Machine.class);
    }
    
}
