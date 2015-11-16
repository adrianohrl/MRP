/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.agents;

import inventory.agents.Responsible;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class ResponsibleDAO extends AgentDAO<Responsible> {

    public ResponsibleDAO(EntityManager em) {
        super(em, Responsible.class);
    }
    
}
