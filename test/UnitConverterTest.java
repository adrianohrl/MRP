
import inventory.util.Unit;
import inventory.util.UnitConverter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adrianohrl
 */
public class UnitConverterTest {
    
    public static void main(String[] args) {
        double quantity;
        UnitConverter converter;
        Unit mm = new Unit("mm", "mm", true);
        Unit cm = new Unit("cm", "cm", true);
        Unit m = new Unit("m", "m", true);
        System.out.println("**************************************** Length Units ****************************************");
        quantity = 1e0; converter = new UnitConverter(mm, mm);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e1; converter = new UnitConverter(mm, cm);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e3; converter = new UnitConverter(mm, m);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e-1; converter = new UnitConverter(cm, mm);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e0; converter = new UnitConverter(cm, cm);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e2; converter = new UnitConverter(cm, m);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e-3; converter = new UnitConverter(m, mm);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e-2; converter = new UnitConverter(m, cm);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e0; converter = new UnitConverter(m, m);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        
        Unit un = new Unit("un", "un", false);
        Unit dez = new Unit("dez", "dez", false);
        Unit duz = new Unit("duz", "duz", false);
        Unit cen = new Unit("cen", "cen", false);
        Unit mil = new Unit("mil", "mil", false);
        System.out.println("**************************************** Quantity Units ****************************************");
        quantity = 1e0; converter = new UnitConverter(un, un);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e1; converter = new UnitConverter(un, dez);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12e0; converter = new UnitConverter(un, duz);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e2; converter = new UnitConverter(un, cen);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e3; converter = new UnitConverter(un, mil);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e0; converter = new UnitConverter(dez, un);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e1; converter = new UnitConverter(dez, dez);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12e0; converter = new UnitConverter(dez, duz);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e2; converter = new UnitConverter(dez, cen);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e3; converter = new UnitConverter(dez, mil);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e0; converter = new UnitConverter(duz, un);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e1; converter = new UnitConverter(duz, dez);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12e0; converter = new UnitConverter(duz, duz);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e2; converter = new UnitConverter(duz, cen);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e3; converter = new UnitConverter(duz, mil);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e0; converter = new UnitConverter(cen, un);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e1; converter = new UnitConverter(cen, dez);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12e1; converter = new UnitConverter(cen, duz);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e2; converter = new UnitConverter(cen, cen);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e3; converter = new UnitConverter(cen, mil);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e0; converter = new UnitConverter(mil, un);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e1; converter = new UnitConverter(mil, dez);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12e0; converter = new UnitConverter(mil, duz);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e2; converter = new UnitConverter(mil, cen);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e3; converter = new UnitConverter(mil, mil);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        
        Unit s = new Unit("s", "s", true);
        Unit min = new Unit("min", "min", true);
        Unit h = new Unit("h", "h", true);
        Unit dia = new Unit("dia", "dia", true);
        Unit sem = new Unit("sem", "sem", true);
        Unit mes = new Unit("mes", "mes", true);
        Unit ano = new Unit("ano", "ano", true);
        System.out.println("**************************************** Temporal Units ****************************************");
        quantity = 1; converter = new UnitConverter(s, s);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 60; converter = new UnitConverter(s, min);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 3600; converter = new UnitConverter(s, h);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 24*3600; converter = new UnitConverter(s, dia);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 7*24*3600; converter = new UnitConverter(s, sem);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 30*24*3600; converter = new UnitConverter(s, mes);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12*30*24*3600; converter = new UnitConverter(s, ano);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/60f; converter = new UnitConverter(min, s);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1; converter = new UnitConverter(min, min);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 60; converter = new UnitConverter(min, h);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 24*60; converter = new UnitConverter(min, dia);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 7*24*60; converter = new UnitConverter(min, sem);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 30*24*60; converter = new UnitConverter(min, mes);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12*30*24*60; converter = new UnitConverter(min, ano);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/3600f; converter = new UnitConverter(h, s);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/60f; converter = new UnitConverter(h, min);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1; converter = new UnitConverter(h, h);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 24; converter = new UnitConverter(h, dia);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 7*24; converter = new UnitConverter(h, sem);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 30*24; converter = new UnitConverter(h, mes);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12*30*24; converter = new UnitConverter(h, ano);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/24f/3600f; converter = new UnitConverter(dia, s);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/24f/60f; converter = new UnitConverter(dia, min);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/24f; converter = new UnitConverter(dia, h);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1; converter = new UnitConverter(dia, dia);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 7; converter = new UnitConverter(dia, sem);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 30; converter = new UnitConverter(dia, mes);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12*30; converter = new UnitConverter(dia, ano);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/7f/24f/3600f; converter = new UnitConverter(sem, s);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/7f/24f/60f; converter = new UnitConverter(sem, min);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/7f/24f; converter = new UnitConverter(sem, h);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/7f; converter = new UnitConverter(sem, dia);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1; converter = new UnitConverter(sem, sem);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 30/7f; converter = new UnitConverter(sem, mes);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12*30/7f; converter = new UnitConverter(sem, ano);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/30f/24f/3600f; converter = new UnitConverter(mes, s);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/30f/24f/60f; converter = new UnitConverter(mes, min);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/30f/24f; converter = new UnitConverter(mes, h);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/30f; converter = new UnitConverter(mes, dia);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 7/30f; converter = new UnitConverter(mes, sem);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1; converter = new UnitConverter(mes, mes);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 12; converter = new UnitConverter(mes, ano);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/12f/30f/24f/3600f; converter = new UnitConverter(ano, s);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/12f/30f/24f/60f; converter = new UnitConverter(ano, min);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/12f/30f/24f; converter = new UnitConverter(ano, h);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/12f/30f; converter = new UnitConverter(ano, dia);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 7/12f/30f; converter = new UnitConverter(ano, sem);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1/12f; converter = new UnitConverter(ano, mes);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1; converter = new UnitConverter(ano, ano);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        
        Unit mg = new Unit("mg", "mg", true);
        Unit g = new Unit("g", "g", true);
        Unit kg = new Unit("kg", "kg", true);
        Unit ton = new Unit("ton", "ton", true);
        System.out.println("**************************************** Weight Units ****************************************");
        quantity = 1e0; converter = new UnitConverter(mg, mg); 
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e3; converter = new UnitConverter(mg, g);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e6; converter = new UnitConverter(mg, kg);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e9; converter = new UnitConverter(mg, ton);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e-3; converter = new UnitConverter(g, mg);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e0; converter = new UnitConverter(g, g);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e3; converter = new UnitConverter(g, kg);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e6; converter = new UnitConverter(g, ton);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e-6; converter = new UnitConverter(kg, mg);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e-3; converter = new UnitConverter(kg, g);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e0; converter = new UnitConverter(kg, kg);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e3; converter = new UnitConverter(kg, ton);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e-9; converter = new UnitConverter(ton, mg);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e-6; converter = new UnitConverter(ton, g);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e-3; converter = new UnitConverter(ton, kg);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
        quantity = 1e0; converter = new UnitConverter(ton, ton);
        System.out.println(quantity + " [" + converter.getFrom() + "] = " + converter.convert(quantity) + "[" + converter.getTo() + "]");
    }
    
}
