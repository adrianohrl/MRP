/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.agents;

import inventory.agents.Filial;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class FilialDAO extends AgentDAO<Filial> {

    public FilialDAO(EntityManager em) {
        super(em, Filial.class);
    }
    
}
