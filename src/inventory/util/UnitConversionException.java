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
public class UnitConversionException extends RuntimeException {

    public UnitConversionException() {
        super("Unregistered convertion factor!!!");
    }

    public UnitConversionException(String message) {
        super(message);
    }
    
}
