/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.movements;

import inventory.movements.InsideTheSector;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class InsideTheSectorDAO extends FromSectorToSectorDAO<InsideTheSector> {

    public InsideTheSectorDAO(EntityManager em) {
        super(em, InsideTheSector.class);
    }
    
}
