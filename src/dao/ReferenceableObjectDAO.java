/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 * @param <R>
 */
public class ReferenceableObjectDAO<R extends Referenceable> extends DAO<R, String> {

    protected ReferenceableObjectDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }

    @Override
    public boolean isRegistered(R referenceable) {
        return super.find(referenceable.getReference()) != null;
    }
    
}
