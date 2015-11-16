/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.movements;

import inventory.movements.FromSector;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <M>
 */
public abstract class FromSectorDAO<M extends FromSector> extends MovementDAO<M> {

    protected FromSectorDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }
    
}
