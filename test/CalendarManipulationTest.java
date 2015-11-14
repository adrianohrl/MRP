
import inventory.util.CalendarManipulator;
import inventory.util.Unit;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adrianohrl
 */
public class CalendarManipulationTest {
    
    public static void main(String[] args) {
        double quantity; 
        Unit unit;
        String str;
        Calendar date;
        
        /************* Adding ************/
        date = new GregorianCalendar();
        str = "Before: " + CalendarManipulator.formatHourAndDate(date);
        quantity = 28.5;
        unit = new Unit("dia", "dia", true);
        date = CalendarManipulator.add(date, quantity, unit);
        str += " + " + quantity + " [" + unit + "] = " + CalendarManipulator.formatHourAndDate(date);
        System.out.println(str);
        
        date = new GregorianCalendar();
        str = "Before: " + CalendarManipulator.formatHourAndDate(date);
        quantity = 1.5 + 1 / 3600f;
        unit = new Unit("h", "h", true);
        date = CalendarManipulator.add(date, quantity, unit);
        str += " + " + quantity + " [" + unit + "] = " + CalendarManipulator.formatHourAndDate(date);
        System.out.println(str);
        
        /************* Subtracting ************/
        date = new GregorianCalendar();
        str = "Before: " + CalendarManipulator.formatHourAndDate(date);
        quantity = 1;
        unit = new Unit("sem", "sem", true);
        date = CalendarManipulator.subtract(date, quantity, unit);
        str += " - " + quantity + " [" + unit + "] = " + CalendarManipulator.formatHourAndDate(date);
        System.out.println(str);
        
        date = new GregorianCalendar();
        str = "Before: " + CalendarManipulator.formatHourAndDate(date);
        quantity = 1.5 + 1 / 3600f;
        unit = new Unit("h", "h", true);
        date = CalendarManipulator.subtract(date, quantity, unit);
        str += " - " + quantity + " [" + unit + "] = " + CalendarManipulator.formatHourAndDate(date);
        System.out.println(str);
    }
    
}
