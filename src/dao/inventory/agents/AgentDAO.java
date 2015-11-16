/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.agents;

import dao.DAO;
import inventory.agents.Agent;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <A>
 */
public abstract class AgentDAO<A extends Agent> extends DAO<A, String> {

    protected AgentDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }

    @Override
    public boolean isRegistered(A agent) {
        return super.find(agent.getName()) != null;
    }
    
}
