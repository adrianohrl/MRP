/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.agents;

import inventory.agents.Sector;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class SectorDAO extends AgentDAO<Sector> {

    public SectorDAO(EntityManager em) {
        super(em, Sector.class);
    }
    
}
