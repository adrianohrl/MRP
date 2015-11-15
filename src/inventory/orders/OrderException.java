/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.orders;

/**
 *
 * @author adrianohrl
 */
public class OrderException extends RuntimeException {

    public OrderException() {
        super("Invalid order!!!");
    }

    public OrderException(String message) {
        super(message);
    }
    
}
