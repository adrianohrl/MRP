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
 * @param <C>
 */
public class CodeableDAO<C extends Codeable> extends DAO<C, Long> {

    protected CodeableDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }

    @Override
    public boolean isRegistered(C codeable) {
        return super.find(codeable.getCode()) != null;
    }
    
}
