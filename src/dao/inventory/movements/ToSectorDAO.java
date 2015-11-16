/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.movements;

import inventory.movements.ToSector;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <M>
 */
public abstract class ToSectorDAO<M extends ToSector> extends MovementDAO<M> {

    protected ToSectorDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }
    
}
