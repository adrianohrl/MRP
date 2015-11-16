/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inventory.orders;

import dao.CodeableDAO;
import inventory.orders.OrderItem;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class OrderItemDAO extends CodeableDAO<OrderItem> {

    public OrderItemDAO(EntityManager em) {
        super(em, OrderItem.class);
    }
    
}
