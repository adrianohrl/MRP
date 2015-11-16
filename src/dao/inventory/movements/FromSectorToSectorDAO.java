/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.movements;

import inventory.movements.FromSectorToSector;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <M>
 */
public class FromSectorToSectorDAO<M extends FromSectorToSector> extends MovementDAO<M> {
    
    public FromSectorToSectorDAO(EntityManager em) {
        super(em, FromSectorToSector.class);
    }

    protected FromSectorToSectorDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }
    
}
