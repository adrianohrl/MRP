/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.util;

import dao.DAO;
import inventory.util.Unit;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class UnitDAO extends DAO<Unit, String> {

    public UnitDAO(EntityManager em) {
        super(em, Unit.class);
    }

    @Override
    public boolean isRegistered(Unit unit) {
        return super.find(unit.getAbbreviation()) != null;
    }
    
}
