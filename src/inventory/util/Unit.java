/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.util;

import java.io.Serializable;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Unit implements Serializable {
    
    @Id
    private String abbreviation;
    private String name;
    private boolean fractionary = true;

    public Unit() {
    }

    public Unit(String abbreviation, String name, boolean fractionary) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.fractionary = fractionary;
    }
    
    protected Unit(String abbreviation) {
        this.abbreviation = abbreviation;
        this.name = abbreviation;
    }
    
    public boolean canBeCompared(Unit to) {
        return Unit.canBeCompared(this, to);
    }
    
    public static boolean canBeCompared(Unit from, Unit to) {
        return (from.measuresQuantity() && to.measuresQuantity()) || 
                (from.measuresLength() && to.measuresLength()) ||
                (from.measuresTime() && to.measuresTime()) || 
                (from.measuresWeight() && to.measuresWeight());
    }
    
    protected boolean measuresLength() {
        HashMap<String, Unit> units = Unit.getLengthUnits();
        return units.containsKey(abbreviation);
    }
    
    protected boolean measuresQuantity() {
        HashMap<String, Unit> units = Unit.getQuantityUnits();
        return units.containsKey(abbreviation);
    }
    
    protected boolean measuresTime() {
        HashMap<String, Unit> units = Unit.getTemporalUnits();
        return units.containsKey(abbreviation);
    }
    
    protected boolean measuresWeight() {
        HashMap<String, Unit> units = Unit.getWeightUnits();
        return units.containsKey(abbreviation);
    }
    
    public static HashMap<String, Unit> getUnits() {
        HashMap<String, Unit> units = new HashMap<>();
        units.putAll(Unit.getWeightUnits());
        units.putAll(Unit.getLengthUnits());
        units.putAll(Unit.getQuantityUnits());
        units.putAll(Unit.getTemporalUnits());
        return units;
    }
    
    public static HashMap<String, Unit> getLengthUnits() {
        HashMap<String, Unit> units = new HashMap<>();
        units.put("mm", new Unit("mm", "Milímetro(s)", true));
        units.put("cm", new Unit("cm", "Centímetro(s)", true));
        units.put("m", new Unit("m", "Metro(s)", true));
        return units;
    }
    
    public static HashMap<String, Unit> getQuantityUnits() {
        HashMap<String, Unit> units = new HashMap<>();
        units.put("un", new Unit("un", "Unidade(s)", false));
        units.put("dez", new Unit("dez", "Dezena(s)", false));
        units.put("duz", new Unit("duz", "Dúzia(s)", false));
        units.put("cen", new Unit("cen", "Centena(s)", false));
        units.put("mil", new Unit("mil", "Milhar(es)", false));
        return units;
    }
    
    public static HashMap<String, Unit> getTemporalUnits() {
        HashMap<String, Unit> units = new HashMap<>();
        units.put("s", new Unit("s", "Segundo(s)", true));
        units.put("min", new Unit("min", "Minuto(s)", true));
        units.put("h", new Unit("h", "Hor(s)", true));
        units.put("dia", new Unit("dia", "Dia(s)", true));
        units.put("sem", new Unit("sem", "Semana(s)", true));
        units.put("mes", new Unit("mes", "Mês(es)", true));
        units.put("ano", new Unit("ano", "Ano(s)", true));
        return units;
    }
    
    public static HashMap<String, Unit> getWeightUnits() {
        HashMap<String, Unit> units = new HashMap<>();
        units.put("mg", new Unit("mg", "Miligrama(s)", true));
        units.put("g", new Unit("g", "Grama(s)", true));
        units.put("kg", new Unit("kg", "Kilograma(s)", true));
        units.put("ton", new Unit("ton", "Tonelada(s)", true));
        return units;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Unit && equals((Unit) obj);
    }
    
    public boolean equals(Unit unit) {
        return unit != null && abbreviation.equalsIgnoreCase(unit.abbreviation);
    }
    
    public boolean equals(String abbreviation) {
        return abbreviation != null && equals(new Unit(abbreviation, abbreviation, true));
    }

    @Override
    public String toString() {
        return abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFractionary() {
        return fractionary;
    }

    public void setFractionary(boolean fractionary) {
        this.fractionary = fractionary;
    }
    
}
