/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.util;

/**
 *
 * @author adrianohrl
 */
public class CalendarManipulationException extends RuntimeException {

    public CalendarManipulationException() {
        super("Invalid operation!!!");
    }

    public CalendarManipulationException(String message) {
        super(message);
    }
    
}
