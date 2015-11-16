/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.agents;

import inventory.agents.Client;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class ClientDAO extends AgentDAO<Client> {

    public ClientDAO(EntityManager em) {
        super(em, Client.class);
    }
    
}
