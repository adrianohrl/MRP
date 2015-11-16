/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.movements;

import inventory.movements.FromClientToSector;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class FromClientToSectorDAO extends ToSectorDAO<FromClientToSector> {

    public FromClientToSectorDAO(EntityManager em) {
        super(em, FromClientToSector.class);
    }
    
}
