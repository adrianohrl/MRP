/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author adrianohrl
 */
public class CalendarManipulator {
    
    public static Calendar add(Calendar calendar, double quantity, Unit temporalUnit) throws CalendarManipulationException {
        if (!temporalUnit.measuresTime()) {
            throw new CalendarManipulationException("Unit must be a temporal unit!!!");
        }
        calendar = CalendarManipulator.clone(calendar);
        UnitConverter converter;
        int integerPart = (int) Math.floor(quantity);
        if (temporalUnit.equals("s")) {
            calendar.add(Calendar.SECOND, (int) Math.round(quantity));
            return calendar;
        } else if (temporalUnit.equals("min")) {
            calendar.add(Calendar.MINUTE, integerPart);
            converter = new UnitConverter("min", "s");
        } else if (temporalUnit.equals("h")) {
            calendar.add(Calendar.HOUR, integerPart);
            converter = new UnitConverter("h", "min");
        } else if (temporalUnit.equals("dia")) {
            calendar.add(Calendar.DAY_OF_MONTH, integerPart);
            converter = new UnitConverter("dia", "h");
        } else if (temporalUnit.equals("sem")) {
            converter = new UnitConverter("sem", "dia");
            calendar.add(Calendar.DAY_OF_MONTH, 7 * integerPart);
        } else if (temporalUnit.equals("mes")) {
            converter = new UnitConverter("mes", "dia");
        } else if (temporalUnit.equals("ano")) {
            calendar.add(Calendar.YEAR, integerPart);
            converter = new UnitConverter("ano", "mes");
        } else {
            throw new CalendarManipulationException("Unknown temporal unit!!!");
        } 
        double fraction = quantity - integerPart;
        if (fraction != 0) {
            return CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        }
        return calendar;
    }
    
    public static Calendar subtract(Calendar calendar, double quantity, Unit temporalUnit) throws CalendarManipulationException {
        if (!temporalUnit.measuresTime()) {
            throw new CalendarManipulationException("Unit must be a temporal unit!!!");
        }
        calendar = CalendarManipulator.clone(calendar);
        UnitConverter converter = null;
        quantity = -quantity;
        int integerPart = (int) Math.floor(quantity);
        if (temporalUnit.equals("s")) {
            calendar.add(Calendar.SECOND, (int) Math.round(quantity));
            return calendar;
        } else if (temporalUnit.equals("min")) {
            calendar.add(Calendar.MINUTE, integerPart);
            converter = new UnitConverter("min", "s");
        } else if (temporalUnit.equals("h")) {
            calendar.add(Calendar.HOUR, integerPart);
            converter = new UnitConverter("h", "min");
        } else if (temporalUnit.equals("dia")) {
            calendar.add(Calendar.DAY_OF_MONTH, integerPart);
            converter = new UnitConverter("dia", "h");
        } else if (temporalUnit.equals("sem")) {
            converter = new UnitConverter("sem", "dia");
            calendar.add(Calendar.DAY_OF_MONTH, 7 * integerPart);
        } else if (temporalUnit.equals("mes")) {
            calendar.add(Calendar.MONTH, integerPart);
            converter = new UnitConverter("mes", "dia");
        } else if (temporalUnit.equals("ano")) {
            calendar.add(Calendar.YEAR, integerPart);
            converter = new UnitConverter("ano", "mes");
        } else {
            throw new CalendarManipulationException("Unknown temporal unit!!!");
        } 
        double fraction = quantity - integerPart;
        if (fraction != 0) {
            return CalendarManipulator.add(calendar, converter.convert(fraction), converter.getTo());
        }
        return calendar;
    }
    
    public static boolean happenOnTheSameDay(Calendar date1, Calendar date2) {
        return date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) && 
                date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) && 
                date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH);
    }
    
    public static Calendar getDate(Calendar calendar) {
        return new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }
    
    public static Calendar today() {
        return CalendarManipulator.getDate(new GregorianCalendar());
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
    
    private static Calendar clone(Calendar calendar) {
        return (Calendar) calendar.clone();
    }
    
}
