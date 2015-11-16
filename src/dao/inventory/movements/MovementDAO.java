/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.movements;

import dao.CodeableDAO;
import inventory.movements.Movement;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <M>
 */
public abstract class MovementDAO<M extends Movement> extends CodeableDAO<M> {

    protected MovementDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }
    
}
