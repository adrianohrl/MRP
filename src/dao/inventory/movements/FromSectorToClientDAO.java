/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.movements;

import inventory.movements.FromSectorToClient;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class FromSectorToClientDAO extends FromSectorDAO<FromSectorToClient> {

    public FromSectorToClientDAO(EntityManager em) {
        super(em, FromSectorToClient.class);
    }
    
}
