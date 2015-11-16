/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.movements;

import inventory.movements.FromSupplierToSector;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class FromSupplierToSectorDAO extends ToSectorDAO<FromSupplierToSector> {

    public FromSupplierToSectorDAO(EntityManager em) {
        super(em, FromSupplierToSector.class);
    }
    
}
