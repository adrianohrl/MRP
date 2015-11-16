/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory;

import dao.CodeableDAO;
import inventory.GrossRequirement;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class GrossRequirementDAO extends CodeableDAO<GrossRequirement> {

    public GrossRequirementDAO(EntityManager em) {
        super(em, GrossRequirement.class);
    }
    
}
