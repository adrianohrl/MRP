/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory;

import dao.CodeableDAO;
import inventory.ScheduledReceipt;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class ScheduledReceiptDAO extends CodeableDAO<ScheduledReceipt> {

    public ScheduledReceiptDAO(EntityManager em) {
        super(em, ScheduledReceipt.class);
    }
    
}
