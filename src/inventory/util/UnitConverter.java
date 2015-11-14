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
    
    /** Temporal Units */
    private static final double SECONDS_PER_MINUTE = 60;
    private static final double MINUTES_PER_HOUR = 60;
    private static final double HOURS_PER_DAY = 24;
    private static final double DAYS_PER_WEEK = 7;
    private static final double DAYS_PER_MONTH = 30;
    private static final double MONTHES_PER_YEAR = 12;
    
    /** Weight Units */
    private static final double THOUSANDS = 1000;
    private static final double HUNDREDS = 100;
    private static final double DOZENS = 12;
    private static final double TENS = 10;

    public UnitConverter(Unit from, Unit to) {
        this.from = from;
        this.to = to;
    }
    
    protected UnitConverter(String from, String to) {
        this.from = new Unit(from);
        this.to = new Unit(to);
    }
    
    public static double convert(double quantity, Unit from, Unit to) throws UnitConversionException {
        double convertedQuantity = getConversionFactor(from, to) * quantity;
        if (!to.isFractionary()) {
            convertedQuantity = Math.round(convertedQuantity);
        }
        return convertedQuantity;
    }
    
    public double convert(double quantity) throws UnitConversionException {
        return UnitConverter.convert(quantity, from, to);
    }
    
    public static boolean isValid(Unit from, Unit to) {
        return Unit.canBeCompared(from, to);
    }
    
    private boolean isValid() {
        return from.canBeCompared(to);
    }
    
    private static double getConversionFactor(Unit from, Unit to) throws UnitConversionException {
        double conversionFactor = 0;
        if (from.measuresLength()) {
            if (to.equals("mm")) {
                conversionFactor = THOUSANDS;
            } else if (to.equals("cm")){
                conversionFactor = HUNDREDS;
            } else if (to.equals("m")) {
                conversionFactor = 1;
            }
            if (from.equals("mm")) {
                conversionFactor /= THOUSANDS;
            } else if (from.equals("cm")){
                conversionFactor /= HUNDREDS;
            } else if (from.equals("m")) {
                conversionFactor /= 1;
            } else {
                throw new UnitConversionException("Unable to convert from [" + from + "] to [" + to + "]!!!");
            }
        } else if (from.measuresQuantity()) {
            if (to.equals("un")) {
                conversionFactor = 1;
            } else if (to.equals("dez")){
                conversionFactor = 1 / TENS;
            } else if (to.equals("duz")) {
                conversionFactor = 1 / DOZENS;
            } else if (to.equals("cen")){
                conversionFactor = 1 / HUNDREDS;
            } else if (to.equals("mil")) {
                conversionFactor = 1 / THOUSANDS;
            }
            if (from.equals("un")) {
                conversionFactor *= 1;
            } else if (from.equals("dez")){
                conversionFactor *= TENS;
            } else if (from.equals("duz")) {
                conversionFactor *= DOZENS;
            } else if (from.equals("cen")){
                conversionFactor *= HUNDREDS;
            } else if (from.equals("mil")) {
                conversionFactor *= THOUSANDS;
            } else {
                throw new UnitConversionException("Unable to convert from [" + from + "] to [" + to + "]!!!");
            } 
        } else if (from.measuresTime()) {
            if (to.equals("s")) {
                conversionFactor = MINUTES_PER_HOUR * MINUTES_PER_HOUR;
            } else if (to.equals("min")){
                conversionFactor = MINUTES_PER_HOUR;
            } else if (to.equals("h")) {
                conversionFactor = 1;
            } else if (to.equals("dia")){
                conversionFactor = 1 / HOURS_PER_DAY;
            } else if (to.equals("sem")) {
                conversionFactor = 1 / DAYS_PER_WEEK / HOURS_PER_DAY;
            } else if (to.equals("mes")) {
                conversionFactor = 1 / DAYS_PER_MONTH / HOURS_PER_DAY;
            } else if (to.equals("ano")) {
                conversionFactor = 1 / MONTHES_PER_YEAR / DAYS_PER_MONTH / HOURS_PER_DAY;
            }
            if (from.equals("s")) {
                conversionFactor /= MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
            } else if (from.equals("min")){
                conversionFactor /= MINUTES_PER_HOUR;
            } else if (from.equals("h")) {
                conversionFactor /= 1;
            } else if (from.equals("dia")){
                conversionFactor *= HOURS_PER_DAY;
            } else if (from.equals("sem")) {
                conversionFactor *= DAYS_PER_WEEK * HOURS_PER_DAY;
            } else if (from.equals("mes")) {
                conversionFactor *= DAYS_PER_MONTH * HOURS_PER_DAY;
            } else if (from.equals("ano")) {
                conversionFactor *= MONTHES_PER_YEAR * DAYS_PER_MONTH * HOURS_PER_DAY;
            } else {
                throw new UnitConversionException("Unable to convert from [" + from + "] to [" + to + "]!!!");
            }
        } else if (from.measuresWeight()) {
            if (to.equals("mg")) {
                conversionFactor = THOUSANDS;
            } else if (to.equals("g")){
                conversionFactor = 1;
            } else if (to.equals("kg")) {
                conversionFactor = 1 / THOUSANDS;
            } else if (to.equals("ton")){
                conversionFactor = 1 / THOUSANDS / THOUSANDS;
            } 
            if (from.equals("mg")) {
                conversionFactor /= THOUSANDS;
            } else if (from.equals("g")){
                conversionFactor /= 1;
            } else if (from.equals("kg")) {
                conversionFactor *= THOUSANDS;
            } else if (from.equals("ton")){
                conversionFactor *= THOUSANDS * THOUSANDS;
            } 
        }
        if (conversionFactor == 0) {
            throw new UnitConversionException("Unable to convert from [" + from + "] to [" + to + "]!!!");
        }
        return conversionFactor;
    }
    
    private double getConversionFactor() throws UnitConversionException {
        return UnitConverter.getConversionFactor(from, to);
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
