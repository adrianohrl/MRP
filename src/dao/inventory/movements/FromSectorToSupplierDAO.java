/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.movements;

import inventory.movements.FromSectorToSupplier;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class FromSectorToSupplierDAO extends FromSectorDAO<FromSectorToSupplier> {

    public FromSectorToSupplierDAO(EntityManager em) {
        super(em, FromSectorToSupplier.class);
    }
    
}
