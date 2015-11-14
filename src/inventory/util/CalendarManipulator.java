/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author adrianohrl
 */
public class CalendarManipulator {
    
    public static Calendar add(Calendar calendar, double quantity, Unit temporalUnit) throws CalendarManipulationException {
        if (!temporalUnit.measuresTime()) {
            throw new CalendarManipulationException("Unit must be a temporal unit!!!");
        }
        UnitConverter converter;
        if (temporalUnit.equals("s")) {
            calendar.add(Calendar.SECOND, (int) Math.round(quantity));
        } else if (temporalUnit.equals("min")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.MINUTE, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("min", "s");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else if (temporalUnit.equals("h")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.HOUR, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("h", "min");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else if (temporalUnit.equals("dia")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.DAY_OF_MONTH, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("dia", "h");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else if (temporalUnit.equals("sem")) {
            converter = new UnitConverter("sem", "dia");
            CalendarManipulator.add(calendar, converter.convert(quantity), converter.getTo());
        } else if (temporalUnit.equals("mes")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.MONTH, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("mes", "dia");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else if (temporalUnit.equals("ano")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.YEAR, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("ano", "mes");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else {
            throw new CalendarManipulationException("Unknown temporal unit!!!");
        } 
        return calendar;
    }
    
    public static Calendar subtract(Calendar calendar, double quantity, Unit temporalUnit) throws CalendarManipulationException {
        if (!temporalUnit.measuresTime()) {
            throw new CalendarManipulationException("Unit must be a temporal unit!!!");
        }
        UnitConverter converter;
        quantity = -quantity;
        if (temporalUnit.equals("s")) {
            calendar.add(Calendar.SECOND, (int) Math.round(quantity));
        } else if (temporalUnit.equals("min")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.MINUTE, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("min", "s");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else if (temporalUnit.equals("h")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.HOUR, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("h", "min");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else if (temporalUnit.equals("dia")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.DAY_OF_MONTH, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("dia", "h");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else if (temporalUnit.equals("sem")) {
            converter = new UnitConverter("sem", "dia");
            CalendarManipulator.add(calendar, converter.convert(quantity), converter.getTo());
        } else if (temporalUnit.equals("mes")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.MONTH, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("mes", "dia");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else if (temporalUnit.equals("ano")) {
            int integerPart = (int) Math.floor(quantity);
            calendar.add(Calendar.YEAR, integerPart);
            double fraction = quantity - integerPart;
            converter = new UnitConverter("ano", "mes");
            CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        } else {
            throw new CalendarManipulationException("Unknown temporal unit!!!");
        } 
        return calendar;
    }
    
    public static String formatAll(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        return CalendarManipulator.format(calendar, formatter);
    }
    
    public static String formatDate(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy"); 
        return CalendarManipulator.format(calendar, formatter);
    }
    
    public static String formatHour(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss"); 
        return CalendarManipulator.format(calendar, formatter);
    }
    
    public static String formatHourAndDate(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy HH:mm:ss"); 
        return CalendarManipulator.format(calendar, formatter);
    }
    
    private static String format(Calendar calendar, SimpleDateFormat formatter) {
        return formatter.format(calendar.getTime());
    }
    
}
