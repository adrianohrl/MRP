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
public class UnitConverter {
    
    private Unit from;
    private Unit to;

    public UnitConverter(Unit from, Unit to) {
        this.from = from;
        this.to = to;
    }
    
    public static double convert(double quantity, Unit from, Unit to) {
        return getConvertionFactor(from, to) * quantity;
    }
    
    public double convert(double quantity) {
        return getConvertionFactor() * quantity;
    }
    
    private static double getConvertionFactor(Unit from, Unit to) {
        return 1;
    }
    
    private double getConvertionFactor() {
        return 1;
    }

    public Unit getFrom() {
        return from;
    }

    public void setFrom(Unit from) {
        this.from = from;
    }

    public Unit getTo() {
        return to;
    }

    public void setTo(Unit to) {
        this.to = to;
    }
    
}
